import { Context, Next } from 'koa';

import docsService from 'services/docs/docs.service';

import { htmlTplString, jsTplString, stringify } from './swagger.helpers';

function trimQuery(q: string) {
  return q && q.split('?')[0];
}

let swaggerInit = '';

function generateHTML() {
  const initOptions = {
    swaggerDoc: docsService.getDocs(),
  };

  swaggerInit = jsTplString.replace('<% swaggerOptions %>', stringify(initOptions));

  return htmlTplString;
}

function swaggerInitFn(ctx: Context, next: Next) {
  if (ctx.req?.url && trimQuery(ctx.req?.url).endsWith('/package.json')) {
    ctx.status = 404;
  } else if (ctx.req?.url && trimQuery(ctx.req.url).endsWith('/swagger-ui-init.js')) {
    ctx.type = 'application/javascript';
    ctx.body = swaggerInit;
  } else {
    next();
  }
}

function setup(ctx: Context) {
  const html = generateHTML();

  ctx.status = 200;
  ctx.body = html;
}

export default {
  setup,
  generateHTML,
  swaggerInitFn,
};
