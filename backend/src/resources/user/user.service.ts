import _ from 'lodash';

import database from 'db';

import { User } from 'types';

const updateLastRequest = (id: number) => database.user.update({
  where: { id },
  data: {
    lastRequest: new Date(),
  },
});

const privateFields = [
  'passwordHash',
  'signupToken',
  'resetPasswordToken',
];

const getPublic = (user: User | null) => _.omit(user, privateFields);

export default Object.assign(database.user, {
  updateLastRequest,
  getPublic,
});
