import { z } from 'zod';

import { userService } from 'resources/user';

import { validateMiddleware } from 'middlewares';

import { EMAIL_REGEX } from 'app-constants';
import { AppKoaContext, AppRouter, Next } from 'types';

const schema = z.object({
  email: z.string().regex(EMAIL_REGEX, 'Email format is incorrect.'),
});

type ValidatedData = z.infer<typeof schema>;
type Request = {
  params: {
    id: number;
  };
};

async function validator(ctx: AppKoaContext<ValidatedData, Request>, next: Next) {
  const { id } = ctx.request.params;

  const isUserExists = await userService.count({ where: { id } });

  ctx.assertClientError(isUserExists, { global: 'User not found' });

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData, Request>) {
  const { id } = ctx.request.params;
  const { email } = ctx.validatedData;

  const updatedUser = await userService.update({
    where: { id },
    data: { email },
  });

  ctx.body = userService.getPublic(updatedUser);
}

export default (router: AppRouter) => {
  router.put('/:id', validator, validateMiddleware(schema), handler);
};
