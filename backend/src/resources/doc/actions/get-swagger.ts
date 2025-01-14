import { swaggerService } from 'services';

import { AppRouter } from 'types';

export default (router: AppRouter) => {
  router.get('/swagger', swaggerService.setup);
};
