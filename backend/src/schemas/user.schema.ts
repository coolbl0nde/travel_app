import { z } from 'zod';

export const userSchema = z.object({
  id: z.number(),
  name: z.string(),
  email: z.string(),
  updatedOn: z.date(),
  createdOn: z.date(),
  passwordHash: z.string(),
});
