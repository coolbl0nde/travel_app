import { faker } from '@faker-js/faker';
import z from 'zod';

import { resourceName } from 'resources/account/account.constants';

import { emptySchema } from 'services/docs/docs.schema';
import { RouteExtendedConfig } from 'services/docs/docs.service';

import { userSchema } from 'schemas';

import { schema } from './schema';

const config: RouteExtendedConfig = {
  private: false,
  tags: [resourceName],
  method: 'post',
  path: `/${resourceName}/sign-up`,
  summary: 'Sign Up',
  request: {
    body: {
      content: {
        'application/json': {
          example: {
            example: schema.parse({
              name: faker.person.fullName(),
              email: faker.internet.email(),
              password: 'Qwerty*123',
            }),
          },
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
