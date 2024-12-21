import { tokenService } from 'resources/token';

import { AppKoaContext } from 'types';

const unsetTokens = async (ctx: AppKoaContext) =>
  tokenService.removeAuthTokens(ctx.state.accessToken);

export default {
  unsetTokens,
};
