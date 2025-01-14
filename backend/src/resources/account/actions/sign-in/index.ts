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
  const { email, password } = ctx.validatedData;

  const user = await userService.findFirst({ where: { email } });

  ctx.assertClientError(user && user.passwordHash, {
    global: 'The email or password you have entered is invalid',
  });

  const isPasswordMatch = await securityUtil.compareTextWithHash(password, user.passwordHash);

  ctx.assertClientError(isPasswordMatch, {
    global: 'The email or password you have entered is invalid',
  });

  ctx.validatedData.user = user;
  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData>) {
  const { user } = ctx.validatedData;

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

  router.post('/sign-in', validateMiddleware(schema), validator, handler);
};
