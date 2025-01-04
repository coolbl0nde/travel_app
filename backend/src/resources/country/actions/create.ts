import { z } from 'zod';

import { countryService } from 'resources/country';

import { validateMiddleware } from 'middlewares';

import { AppKoaContext, AppRouter, Next } from 'types';

const schema = z.object({
  name: z.string().min(1, 'Please, enter country name.'),
});
type ValidatedData = z.infer<typeof schema>;

async function validator(ctx: AppKoaContext<ValidatedData>, next: Next) {
  const { id: userId } = ctx.state.user;
  const { name } = ctx.validatedData;

  const isCountryExists = await countryService.count({
    where: { name, userId  },
  });

  ctx.assertClientError(!isCountryExists, { global: 'You\'ve already added this country before.' });

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData>) {
  const { id: userId } = ctx.state.user;
  const { name } = ctx.validatedData;

  ctx.body = await countryService.create({
    data: {
      userId,
      name
    }
  });
}

export default (router: AppRouter) => {
  router.post('/', validator, validateMiddleware(schema), handler);
};
