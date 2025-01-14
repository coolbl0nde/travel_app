import { z } from 'zod';

export const countrySchema = z.object({
  id: z.number(),

  name: z.string(),
  longitude: z.number(),
  latitude: z.number(),

  userId: z.number(),

  createdOn: z.date(),
  updatedOn: z.date(),
});
