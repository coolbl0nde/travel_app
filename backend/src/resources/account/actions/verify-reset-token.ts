import { z } from 'zod';

import { userService } from 'resources/user';

import { validateMiddleware } from 'middlewares';

import config from 'config';

import { EMAIL_REGEX } from 'app-constants';
import { AppKoaContext, AppRouter, User } from 'types';

const schema = z.object({
  email: z.string().regex(EMAIL_REGEX, 'Email format is incorrect.'),
  token: z.string().min(1, 'Token is required'),
});

interface ValidatedData extends z.infer<typeof schema> {
  user: User;
}

async function validator(ctx: AppKoaContext<ValidatedData>) {
  const { email, token } = ctx.validatedData;

  const user = await userService.findFirst({
    where: { resetPasswordToken: token },
  });

  let redirectUrl = `${config.WEB_URL}/reset-password?token=${token}`;

  if (!user || !token.split('_').length || Number(token.split('_')[1]) < new Date().getTime()) {
    redirectUrl = `${config.WEB_URL}/expire-token?email=${email}`;

    if (user) {
      await userService.update({
        where: { id: user.id },
        data: {
          resetPasswordToken: '',
        },
      });
    }
  }

  ctx.redirect(redirectUrl);
}

export default (router: AppRouter) => {
  router.get('/verify-reset-token', validateMiddleware(schema), validator);
};
