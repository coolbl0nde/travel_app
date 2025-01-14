import { userService } from 'resources/user';

import { docsService } from 'services';

import { AppKoaContext, AppRouter } from 'types';

import docConfig from './doc';

async function handler(ctx: AppKoaContext) {
  ctx.body = userService.getPublic(ctx.state.user);
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.get('/', handler);
};
