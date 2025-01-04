import { countryService } from 'resources/country';

import { AppKoaContext, AppRouter } from 'types';

async function handler(ctx: AppKoaContext) {
  const { id: userId } = ctx.state.user;

  ctx.body = await countryService.findMany({
    where: {
      userId,
    }
  });
}

export default (router: AppRouter) => {
  router.get('/', handler);
};
