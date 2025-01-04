import { z } from 'zod';

import { countryService } from 'resources/country';

import { validateMiddleware } from 'middlewares';

import { AppKoaContext, AppRouter, Next } from 'types';

const schema = z.object({
  name: z.string().min(1, 'Please, enter country name.'),
});

type ValidatedData = z.infer<typeof schema>;
type Request = {
  params: {
    id: number;
  };
};

async function validator(ctx: AppKoaContext<ValidatedData, Request>, next: Next) {
  const { id } = ctx.request.params;

  const isCountryExists = await countryService.count({ where: { id } });

  ctx.assertClientError(isCountryExists, { global: 'Country not found.' });

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData, Request>) {
  const { id } = ctx.request.params;
  const { name } = ctx.validatedData;

  ctx.body = await countryService.update({
    where: { id },
    data: { name },
  });
}

export default (router: AppRouter) => {
  router.put('/:id', validator, validateMiddleware(schema), handler);
};
