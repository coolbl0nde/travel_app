import { z } from 'zod';

export const schema = z.object({
  isSaved: z
    .enum(['true', 'false'])
    .transform((value) => value === 'true')
    .optional(),
});
