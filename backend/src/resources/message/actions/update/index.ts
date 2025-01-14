import { Next } from 'koa';
import { z } from 'zod';

import { messageService } from 'resources/message';

import { validateMiddleware } from 'middlewares';
import { docsService } from 'services';

import { AppKoaContext, AppRouter } from 'types';

import docConfig from './doc';
import { schema } from './schema';

type ValidatedData = z.infer<typeof schema>;
type Request = {
  params: {
    messageId: number;
  };
};

async function validator(ctx: AppKoaContext<ValidatedData, Request>, next: Next) {
  const { messageId } = ctx.request.params;
  const { id: userId } = ctx.state.user;

  ctx.assertClientError(!Number.isNaN(messageId), { global: 'Invalid message is provided.' });

  const isMessageExists = await messageService.findFirst({
    where: {
      userId,
      id: +messageId,
    },
  });

  ctx.assertClientError(isMessageExists, { global: 'Message not found.' });

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData, Request>) {
  const { messageId } = ctx.request.params;

  await messageService.update({
    where: { id: +messageId },
    data: ctx.validatedData,
  });

  ctx.status = 200;
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.put('/:messageId', validateMiddleware(schema), validator, handler);
};
