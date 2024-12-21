import { z } from 'zod';

import { tokenService } from 'resources/token';
import { userService } from 'resources/user';

import { validateMiddleware } from 'middlewares';
import { securityUtil } from 'utils';

import { EMAIL_REGEX, PASSWORD_REGEX } from 'app-constants';
import { AppKoaContext, AppRouter, Next, User } from 'types';

const schema = z.object({
  email: z.string().regex(EMAIL_REGEX, 'Email format is incorrect.'),
  password: z
    .string()
    .regex(
      PASSWORD_REGEX,
      'The password must contain 8 or more characters with at least one letter (a-z), one capital letter (A-Z) and one number (0-9).',
    ),
});

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
  const { email, password } = ctx.validatedData;

  const hash = await securityUtil.getHash(password);

  const user = await userService.create({
    data: {
      email,
      passwordHash: hash.toString(),
    },
  });

  const [{ accessToken }] = await Promise.all([
    tokenService.createAuthTokens({ userId: user.id }),
    userService.updateLastRequest(user.id)
  ]);

  ctx.body = {
    user: userService.getPublic(user),
    accessToken
  };
}

export default (router: AppRouter) => {
  router.post('/sign-up', validateMiddleware(schema), validator, handler);
};
