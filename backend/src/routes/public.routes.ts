import mount from 'koa-mount';

import { accountRoutes } from 'resources/account';
import { docRoutes } from 'resources/doc';

import { swaggerService } from 'services';

import { AppKoa, AppRouter } from 'types';

const healthCheckRouter = new AppRouter();
healthCheckRouter.get('/health', (ctx) => {
  ctx.status = 200;
});

export default (app: AppKoa) => {
  app.use(mount('/docs', swaggerService.swaggerInitFn));

  app.use(healthCheckRouter.routes());
  app.use(mount('/account', accountRoutes.publicRoutes));
  app.use(mount('/docs', docRoutes.publicRoutes));
};
