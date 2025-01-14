import { z } from 'zod';

import { messageService } from 'resources/message';

import { validateMiddleware } from 'middlewares';
import { openaiService } from 'services';
import docsService from 'services/docs/docs.service';
import { getRoutePrompt } from 'services/openai/openai.constants';

import { AppKoaContext, AppRouter, OpenAIRole } from 'types';

import docConfig from './doc';
import { schema } from './schema';

type ValidatedData = z.infer<typeof schema>;

const PREVIOUS_MESSAGES_LIMIT = 50;

async function handler(ctx: AppKoaContext<ValidatedData>) {
  const { id: userId, name } = ctx.state.user;
  const { content } = ctx.validatedData;

  const previousMessages = await messageService.findMany({
    where: { userId },
    orderBy: { id: 'asc' },
    select: {
      content: true,
      role: true,
    },
    take: PREVIOUS_MESSAGES_LIMIT,
  });

  const [gptResponse] = await Promise.all([
    openaiService.sendMessage({
      prompt: getRoutePrompt(name),
      userInput: content,
      previousMessages,
    }),
    messageService.create({
      data: {
        userId,
        role: OpenAIRole.USER,
        content,
      },
    }),
  ]);

  ctx.body = await messageService.create({
    data: {
      userId,
      role: OpenAIRole.ASSISTANT,
      content: gptResponse,
    },
  });
}

export default (router: AppRouter) => {
  docsService.registerDocs(docConfig);

  router.post('/', validateMiddleware(schema), handler);
};
