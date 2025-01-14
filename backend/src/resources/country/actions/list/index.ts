import { countryService } from 'resources/country';

import { docsService } from 'services';

import { AppKoaContext, AppRouter } from 'types';

import docConfig from './doc';

async function handler(ctx: AppKoaContext) {
  const { id: userId } = ctx.state.user;

  ctx.body = await countryService.findMany({
    where: {
      userId,
    },
  });
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.get('/', handler);
};
