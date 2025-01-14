import OpenAI from 'openai';
import { ChatCompletionMessageParam } from 'openai/resources/index.mjs';

import config from 'config';

import { OpenAIModel, OpenAIRole } from 'types';

import { SendAIMessageParams } from './openai.types';

const openai = new OpenAI({
  apiKey: config.OPENAI_API_KEY,
});

const sendMessage = async ({
  prompt,
  userInput,
  temperature = 0.3,
  model = OpenAIModel.GPT_3_5_TURBO,
  previousMessages = [],
}: SendAIMessageParams) => {
  const messages: ChatCompletionMessageParam[] = [
    { role: OpenAIRole.SYSTEM, content: prompt },
    ...previousMessages,
    { role: OpenAIRole.USER, content: userInput },
  ];

  const { choices } = await openai.chat.completions.create({
    model,
    messages,
    temperature,
  });

  return choices[0].message.content;
};

export default { sendMessage };
