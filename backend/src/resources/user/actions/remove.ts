import { userService } from 'resources/user';

import { AppKoaContext, AppRouter, Next } from 'types';

type ValidatedData = never;
type Request = {
  params: {
    id: number;
  };
}; 

async function validator(ctx: AppKoaContext<ValidatedData, Request>, next: Next) {
  const isUserExists = await userService.count({
    where: { id: ctx.request.params.id },
  });

  ctx.assertClientError(isUserExists, { global: 'User not found' });

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData, Request>) {
  await userService.delete({
    where: { id: ctx.request.params.id },
  });

  ctx.body = {};
}

export default (router: AppRouter) => {
  router.delete('/:id', validator, handler);
};
