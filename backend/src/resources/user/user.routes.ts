import { routeUtil } from 'utils';

import remove from './actions/remove';
import update from './actions/update';

const privateRoutes = routeUtil.getRoutes([
  remove,
  update
]);

export default {
  privateRoutes,
};
