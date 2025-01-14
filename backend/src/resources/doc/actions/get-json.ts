import { docsService } from 'services';

import { AppKoaContext, AppRouter } from 'types';

async function handler(ctx: AppKoaContext) {
  ctx.body = docsService.getDocs();
}

export default (router: AppRouter) => {
  router.get('/json', handler);
};
