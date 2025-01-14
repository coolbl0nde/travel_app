import { faker } from '@faker-js/faker';

import { resourceName } from 'resources/message/message.constants';

import { emptySchema } from 'services/docs/docs.schema';
import { RouteExtendedConfig } from 'services/docs/docs.service';

import { schema } from './schema';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'put',
  path: `/${resourceName}/:messageId`,
  summary: 'Update message',
  request: {
    body: {
      content: {
        'application/json': {
          schema,
          example: schema.parse({
            isSaved: true,
            content: faker.lorem.sentence(),
          }),
        },
      },
    },
  },
  responses: {
    200: {
      description: 'Empty content',
      content: {
        'application/json': {
          schema: emptySchema,
        },
      },
    },
  },
};

export default config;
