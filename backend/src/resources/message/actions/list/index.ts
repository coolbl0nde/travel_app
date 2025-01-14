import { isNil } from 'lodash';
import { z } from 'zod';

import { messageService } from 'resources/message';

import { validateMiddleware } from 'middlewares';
import { docsService } from 'services';

import { AppKoaContext, AppRouter } from 'types';

import docConfig from './doc';
import { schema } from './schema';

type ValidatedData = z.infer<typeof schema>;

async function handler(ctx: AppKoaContext<ValidatedData>) {
  const { id: userId } = ctx.state.user;
  const { isSaved } = ctx.validatedData;

  ctx.body = await messageService.findMany({
    where: {
      userId,
      ...(!isNil(isSaved) ? { isSaved } : {}),
    },
    orderBy: {
      id: 'asc',
    },
  });
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.get('/', validateMiddleware(schema), handler);
};
