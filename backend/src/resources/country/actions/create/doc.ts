import { faker } from '@faker-js/faker';

import { resourceName } from 'resources/country/country.constants';

import { RouteExtendedConfig } from 'services/docs/docs.service';

import { countrySchema } from 'schemas';

import { schema } from './schema';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'post',
  path: `/${resourceName}/`,
  summary: 'Create country',
  request: {
    body: {
      content: {
        'application/json': {
          schema,
          example: {
            example: schema.parse({
              name: 'Poland',
              longitude: 1234534.123455,
              latitude: 1234534.123455,
            }),
          },
        },
      },
    },
  },
  responses: {
    200: {
      description: 'Created county',
      content: {
        'application/json': {
          schema: countrySchema,
        },
      },
    },
  },
};

export default config;
