import { ChatCompletionMessageParam } from 'openai/resources/index.mjs';

import { OpenAIModel } from 'types';

export type SendAIMessageParams = {
  prompt: string;
  userInput: string;
  userId?: string;
  temperature?: number;
  model?: OpenAIModel;
  previousMessages?: ChatCompletionMessageParam[];
};
