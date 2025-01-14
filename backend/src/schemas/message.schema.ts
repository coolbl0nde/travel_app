import { z } from 'zod';

import { OpenAIRole } from '../types';

export const messageSchema = z.object({
  id: z.number(),
  userId: z.number(),

  content: z.string(),
  role: z.nativeEnum(OpenAIRole),

  isSaved: z.boolean(),

  createdOn: z.date(),
  updatedOn: z.date(),
});
