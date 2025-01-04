import { countryService } from 'resources/country';

import { AppKoaContext, AppRouter, Next } from 'types';

type Request = {
  params: {
    id: number;
  };
}; 

async function validator(ctx: AppKoaContext<never, Request>, next: Next) {
  const { id } = ctx.request.params;
  const { id: userId } = ctx.state.user;

  const isCountryExists = await countryService.count({
    where: { id, userId },
  });

  ctx.assertClientError(isCountryExists, { global: 'Country not found.' });

  await next();
}

async function handler(ctx: AppKoaContext<never, Request>) {
  const { id } = ctx.request.params;

  await countryService.delete({ where: { id } });

  ctx.body = 200;
}

export default (router: AppRouter) => {
  router.delete('/:id', validator, handler);
};
