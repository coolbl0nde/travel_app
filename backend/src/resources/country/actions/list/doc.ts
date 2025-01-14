import { resourceName } from 'resources/country/country.constants';

import { emptySchema } from 'services/docs/docs.schema';
import { RouteExtendedConfig } from 'services/docs/docs.service';

import { countrySchema } from 'schemas';

const config: RouteExtendedConfig = {
  private: true,
  tags: [resourceName],
  method: 'get',
  path: `/${resourceName}/`,
  summary: 'Get countries list',
  request: {
    params: emptySchema,
  },
  responses: {
    200: {
      description: 'Countries list',
      content: {
        'application/json': {
          schema: countrySchema.array(),
        },
      },
    },
  },
};

export default config;
