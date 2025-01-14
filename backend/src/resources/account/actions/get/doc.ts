import { resourceName } from 'resources/account/account.constants';

import { RouteExtendedConfig } from 'services/docs/docs.service';

import { userSchema } from 'schemas';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'get',
  path: `/${resourceName}/`,
  summary: 'Get account',
  responses: {
    200: {
      description: 'Account data',
      content: {
        'application/json': {
          schema: userSchema.omit({ passwordHash: true }),
        },
      },
    },
  },
};

export default config;
