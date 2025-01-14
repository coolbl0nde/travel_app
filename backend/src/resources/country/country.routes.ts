import { routeUtil } from 'utils';

import create from './actions/create';
import list from './actions/list';

const privateRoutes = routeUtil.getRoutes([create, list]);

export default {
  privateRoutes,
};
