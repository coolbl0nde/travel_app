import compose from 'koa-compose';
import mount from 'koa-mount';

import { accountRoutes } from 'resources/account';
import { countryRoutes } from 'resources/country';
import { messageRoutes } from 'resources/message';
import { userRoutes } from 'resources/user';

import { AppKoa } from 'types';

import auth from './middlewares/auth.middleware';

export default (app: AppKoa) => {
  app.use(mount('/account', compose([auth, accountRoutes.privateRoutes])));
  app.use(mount('/users', compose([auth, userRoutes.privateRoutes])));
  app.use(mount('/countries', compose([auth, countryRoutes.privateRoutes])));
  app.use(mount('/messages', compose([auth, messageRoutes.privateRoutes])));
};
