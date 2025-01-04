import { routeUtil } from 'utils';

import create from './actions/create';
import list from './actions/list';
import remove from './actions/remove';
import update from './actions/update';

const privateRoutes = routeUtil.getRoutes([
  remove,
  update,
  create,
  list
]);

export default {
  privateRoutes,
};
