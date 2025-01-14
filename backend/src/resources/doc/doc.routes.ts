import { routeUtil } from 'utils';

import getJson from './actions/get-json';
import getSwagger from './actions/get-swagger';

const publicRoutes = routeUtil.getRoutes([getJson, getSwagger]);

export default {
  publicRoutes,
};
