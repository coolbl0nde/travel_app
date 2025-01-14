import { routeUtil } from 'utils';

import create from './actions/create';
import list from './actions/list';
import update from './actions/update';

const privateRoutes = routeUtil.getRoutes([update, create, list]);

export default {
  privateRoutes,
};
