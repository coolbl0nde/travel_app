import { securityUtil } from 'utils';

import database from 'db';

import { TOKEN_SECURITY_LENGTH } from 'app-constants';
import { TokenType } from 'types';

const createToken = async (userId: number, type: TokenType) => {
  const value = await securityUtil.generateSecureToken(TOKEN_SECURITY_LENGTH);

  return database.token.create({
    data: {
      type,
      value,
      userId,
    },
  });
};

const createAuthTokens = async ({
  userId,
}: { userId: number }) => {
  const accessTokenEntity = await createToken(userId, TokenType.ACCESS);

  return {
    accessToken: accessTokenEntity.value,
  };
};

const findTokenByValue = async (token: string) => {
  const tokenEntity = await database.token.findFirst({
    where: { value: token },
  });

  return tokenEntity && {
    userId: tokenEntity.userId,
  };
};

const removeAuthTokens = async (accessToken: string) => database.token.deleteMany({
    where: {
      value: accessToken,
    },
  });

export default Object.assign(database.token, {
  createAuthTokens,
  findTokenByValue,
  removeAuthTokens,
});
