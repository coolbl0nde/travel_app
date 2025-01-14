import { faker } from '@faker-js/faker';

import { resourceName } from 'resources/message/message.constants';

import { RouteExtendedConfig } from 'services/docs/docs.service';

import { messageSchema } from 'schemas';

import { schema } from './schema';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'post',
  path: `/${resourceName}/`,
  summary: 'Send message to AI consultant',
  request: {
    body: {
      content: {
        'application/json': {
          schema,
          example: schema.parse({
            content: faker.lorem.sentence(),
          }),
        },
      },
    },
  },
  responses: {
    200: {
      description: 'AI Consultant answer',
      content: {
        'application/json': {
          schema: messageSchema,
        },
      },
    },
  },
};

export default config;
