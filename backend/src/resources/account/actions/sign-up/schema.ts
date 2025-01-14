import { z } from 'zod';

import { EMAIL_REGEX, PASSWORD_REGEX } from 'app-constants';

export const schema = z.object({
  email: z.string().regex(EMAIL_REGEX, 'Email format is incorrect.'),
  name: z.string().min(1, 'Please enter your full name.'),
  password: z
    .string()
    .regex(
      PASSWORD_REGEX,
      'The password must contain 8 or more characters with at least one letter (a-z), one capital letter (A-Z) and one number (0-9).',
    ),
});
