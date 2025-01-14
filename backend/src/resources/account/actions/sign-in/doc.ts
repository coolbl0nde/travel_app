import { faker } from '@faker-js/faker';
import z from 'zod';

import { resourceName } from 'resources/account/account.constants';

import { RouteExtendedConfig } from 'services/docs/docs.service';

import { userSchema } from 'schemas';

import { schema } from './schema';

const config: RouteExtendedConfig = {
  private: false,
  tags: [resourceName],
  method: 'post',
  path: `/${resourceName}/sign-in`,
  summary: 'Sign In',
  request: {
    body: {
      content: {
        'application/json': {
          example: schema.parse({
            email: faker.internet.email(),
            password: 'Qwerty*123',
          }),
          schema,
        },
      },
    },
  },
  responses: {
    200: {
      description: 'Account data with Access Token',
      content: {
        'application/json': {
          schema: z.object({
            user: userSchema,
            accessToken: z.string(),
          }),
        },
      },
    },
  },
};

export default config;
