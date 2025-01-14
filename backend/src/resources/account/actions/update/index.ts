import _ from 'lodash';
import { z } from 'zod';

import { userService } from 'resources/user';

import { validateMiddleware } from 'middlewares';
import { docsService } from 'services';

import { AppKoaContext, AppRouter, Next } from 'types';

import docConfig from './doc';
import { schema } from './schema';

type ValidatedData = z.infer<typeof schema>;

async function validator(ctx: AppKoaContext<ValidatedData>, next: Next) {
  const { user } = ctx.state;

  if (_.isEmpty(ctx.validatedData)) {
    ctx.body = userService.getPublic(user);

    return;
  }

  await next();
}

async function handler(ctx: AppKoaContext<ValidatedData>) {
  const { user } = ctx.state;

  const updatedUser = await userService.update({
    where: { id: user.id },
    data: ctx.validatedData,
  });

  ctx.body = userService.getPublic(updatedUser);
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.put('/', validateMiddleware(schema), validator, handler);
};
