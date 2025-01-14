import { z } from 'zod';

export const schema = z.object({
  name: z.string().min(1, 'Please, enter country name.'),
  longitude: z.number().min(0, 'Please, provide longitude.'),
  latitude: z.number().min(0, 'Please, provide latitude.'),
});
