import { faker } from '@faker-js/faker';

import { resourceName } from 'resources/account/account.constants';

import { RouteExtendedConfig } from 'services/docs/docs.service';

import { userSchema } from 'schemas';

import { schema } from './schema';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'put',
  path: `/${resourceName}/`,
  summary: 'Update account',
  request: {
    body: {
      content: {
        'application/json': {
          example: schema.parse({
            name: faker.person.fullName(),
          }),
          schema,
        },
      },
    },
  },
  responses: {
    200: {
      description: 'Updated Account',
      content: {
        'application/json': {
          schema: userSchema,
        },
      },
    },
  },
};

export default config;
