import { z } from 'zod';

export const schema = z.object({
  content: z.string().min(1, 'Please, enter message text.'),
});
