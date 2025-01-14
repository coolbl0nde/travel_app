import { prismaMock } from 'singleton';

import database from 'db';

import { User } from 'types';

const userService = prismaMock.user;

describe('User service', () => {
  const mockUser: User = {
    id: 1,
    name: 'John Smith',
    email: 'smith@example.com',
    passwordHash: 'dkdsksjsfs',
    lastRequest: new Date(100_000),
    updatedOn: new Date(100_000),
    createdOn: new Date(100_000),
  };

  it('should create user', async () => {
    prismaMock.user.create.mockResolvedValue(mockUser);

    await expect(
      database.user.create({
        data: mockUser,
      }),
    ).resolves.toEqual(mockUser);
  });

  it('should find user by id', async () => {
    prismaMock.user.findUnique.mockResolvedValue(mockUser);

    await expect(
      database.user.findUnique({
        where: { id: mockUser.id },
      }),
    ).resolves.toEqual(mockUser);
  });

  it('should update user', async () => {
    const updatedUser = { ...mockUser, name: 'Jane Doe' };
    prismaMock.user.update.mockResolvedValue(updatedUser);

    await expect(
      database.user.update({
        where: { id: mockUser.id },
        data: { name: 'Jane Doe' },
      }),
    ).resolves.toEqual(updatedUser);
  });

  it('should delete user', async () => {
    prismaMock.user.delete.mockResolvedValue(mockUser);

    await expect(
      database.user.delete({
        where: { id: mockUser.id },
      }),
    ).resolves.toEqual(mockUser);
  });
});
