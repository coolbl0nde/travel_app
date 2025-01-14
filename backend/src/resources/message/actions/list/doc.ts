import { resourceName } from 'resources/message/message.constants';

import { RouteExtendedConfig } from 'services/docs/docs.service';

import { messageSchema } from 'schemas';

import { schema } from './schema';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'get',
  path: `/${resourceName}/`,
  summary: 'Get messages list',
  request: {
    params: schema,
  },
  responses: {
    200: {
      description: 'Messages list',
      content: {
        'application/json': {
          schema: messageSchema.array(),
        },
      },
    },
  },
};

export default config;
