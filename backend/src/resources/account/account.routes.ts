import { routeUtil } from 'utils';

import get from './actions/get';
import signIn from './actions/sign-in';
import signOut from './actions/sign-out';
import signUp from './actions/sign-up';
import update from './actions/update';

const publicRoutes = routeUtil.getRoutes([signUp, signIn]);

const privateRoutes = routeUtil.getRoutes([get, update, signOut]);

export default {
  publicRoutes,
  privateRoutes,
};
