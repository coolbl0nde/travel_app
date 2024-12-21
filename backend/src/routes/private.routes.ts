import compose from 'koa-compose';
import mount from 'koa-mount';

import { accountRoutes } from 'resources/account';

import { AppKoa } from 'types';

import auth from './middlewares/auth.middleware';

export default (app: AppKoa) => {
  app.use(mount('/account', compose([auth, accountRoutes.privateRoutes])));
};
