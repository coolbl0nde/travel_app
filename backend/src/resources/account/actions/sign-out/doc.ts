import { resourceName } from 'resources/account/account.constants';

import { emptySchema } from 'services/docs/docs.schema';
import { RouteExtendedConfig } from 'services/docs/docs.service';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'post',
  path: `/${resourceName}/sign-out`,
  summary: 'Sign Out',
  responses: {
    200: {
      description: 'Empty data',
      content: {
        'application/json': {
          schema: emptySchema,
        },
      },
    },
  },
};

export default config;
