import { z } from 'zod';

import { countryService } from 'resources/country';

import { validateMiddleware } from 'middlewares';
import { docsService } from 'services';

import { AppKoaContext, AppRouter, Country, Next } from 'types';

import docConfig from './doc';
import { schema } from './schema';

type ValidatedData = z.infer<typeof schema>;

async function validator(ctx: AppKoaContext<ValidatedData>, next: Next) {
  const { id: userId } = ctx.state.user;
  const { name } = ctx.validatedData;

  const isCountryExists = await countryService.count({
    where: { name, userId },
  });

  ctx.assertClientError(!isCountryExists, { global: "You've already added this country before." });

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData>) {
  const { id: userId } = ctx.state.user;

  ctx.body = await countryService.create({
    data: {
      userId,
      ...ctx.validatedData,
    } as any,
  });
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.post('/', validateMiddleware(schema), validator, handler);
};
