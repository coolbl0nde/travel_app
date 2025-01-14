import { extendZodWithOpenApi, OpenApiGeneratorV3, OpenAPIRegistry, RouteConfig } from '@asteasolutions/zod-to-openapi';
import { capitalize, omit } from 'lodash';
import { AnyZodObject, z } from 'zod';

import config from 'config';

const DEFAULT_CONFIG = {
  openapi: '3.1.0',
  info: {
    title: 'Travel App API',
    description: 'This is the Travel App API',
    version: '1.0.0',
    license: {
      name: 'MIT',
      url: 'https://opensource.org/license/mit/',
    },
  },
  servers: [
    {
      url: config.API_URL,
      description: `${capitalize(config.APP_ENV)} server`,
    },
  ],
};

let registry: OpenAPIRegistry;
const initClient = () => {
  extendZodWithOpenApi(z);
  registry = new OpenAPIRegistry();
};

type AuthType = 'bearerAuth' | 'cookieAuth';
export interface RouteExtendedConfig extends Omit<RouteConfig, 'security'> {
  private: boolean;
  authType?: AuthType;
}
const registerDocs = (routeConfig: RouteExtendedConfig): void => {
  if (!registry) {
    throw Error('OpenAPIRegistry is not initialized');
  }

  const authStrategy: AuthType = routeConfig.authType || 'bearerAuth';

  if (routeConfig.private) {
    if (authStrategy === 'bearerAuth') {
      registry.registerComponent('securitySchemes', 'bearerAuth', {
        type: 'http',
        scheme: 'bearer',
        bearerFormat: 'JWT',
      });
    }

    if (authStrategy === 'cookieAuth') {
      registry.registerComponent('securitySchemes', 'cookieAuth', {
        type: 'apiKey',
        in: 'cookie',
        name: 'JSESSIONID',
      });
    }
  }

  registry.registerPath({
    ...omit(routeConfig, 'private'),
    ...(routeConfig.private ? { security: [{ [authStrategy]: [] }] } : {}),
  });
};

const registerSchema = (name: string, schema: AnyZodObject): AnyZodObject => {
  if (!registry) {
    throw Error('OpenAPIRegistry is not initialized');
  }

  return registry.register(name, schema);
};

const getDocs = () => {
  if (!registry) {
    throw Error('OpenAPIRegistry is not initialized');
  }

  const generator = new OpenApiGeneratorV3(registry.definitions);

  return generator.generateDocument(DEFAULT_CONFIG);
};

export default {
  initClient,
  registerDocs,
  registerSchema,
  getDocs,
};
