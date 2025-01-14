import { z } from 'zod';

import { tokenService } from 'resources/token';
import { userService } from 'resources/user';

import { validateMiddleware } from 'middlewares';
import { docsService } from 'services';
import { securityUtil } from 'utils';

import { AppKoaContext, AppRouter, Next, User } from 'types';

import docConfig from './doc';
import { schema } from './schema';

interface ValidatedData extends z.infer<typeof schema> {
  user: User;
}

async function validator(ctx: AppKoaContext<ValidatedData>, next: Next) {
  const { email } = ctx.validatedData;

  const isUserExists = await userService.count({
    where: { email },
  });

  ctx.assertClientError(!isUserExists, {
    email: 'User with this email is already registered',
  });

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData>) {
  const { email, name, password } = ctx.validatedData;

  const hash = await securityUtil.getHash(password);

  const user = await userService.create({
    data: {
      email,
      name,
      passwordHash: hash.toString(),
    },
  });

  const [{ accessToken }] = await Promise.all([
    tokenService.createAuthTokens({ userId: user.id }),
    userService.updateLastRequest(user.id),
  ]);

  ctx.body = {
    user: userService.getPublic(user),
    accessToken,
  };
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.post('/sign-up', validateMiddleware(schema), validator, handler);
};
