import { Decimal } from '@prisma/client/runtime/binary';
import app from 'app';
import { prismaMock } from 'singleton';
import request from 'supertest';

import { securityUtil } from 'utils';

import { TOKEN_SECURITY_LENGTH } from 'app-constants';

const user = {
  id: 1,
  name: 'Test User',
  email: 'user@example.com',
  passwordHash: 'hashedpassword',
  createdOn: new Date(100_000),
  updatedOn: new Date(100_000),
  lastRequest: new Date(100_000),
};

let token;

beforeAll(() => {
  token = securityUtil.generateSecureToken(TOKEN_SECURITY_LENGTH);
});

describe('Account Endpoints', () => {
  test('POST /account/sign-up - Sign Up', async () => {
    const signUpData = {
      email: 'newuser@example.com',
      name: 'New User',
      password: 'StrongP@ssw0rd',
    };
    const createdUser = { ...user, ...signUpData };

    prismaMock.user.create.mockResolvedValue(createdUser);

    const res = await request(app.callback()).post('/account/sign-up').send(signUpData).expect(200);

    expect(res.body).toHaveProperty('user');
    expect(res.body).toHaveProperty('accessToken');
    expect(res.body.user.email).toBe(signUpData.email);
  });

  test('POST /account/sign-in - Sign In', async () => {
    const signInData = {
      email: 'user@example.com',
      password: 'StrongP@ssw0rd',
    };

    prismaMock.user.findUnique.mockResolvedValue(user);

    const res = await request(app.callback()).post('/account/sign-in').send(signInData).expect(200);

    expect(res.body).toHaveProperty('user');
    expect(res.body).toHaveProperty('accessToken');
  });

  test('GET /account/ - Get account', async () => {
    prismaMock.user.findUnique.mockResolvedValue(user);

    const res = await request(app.callback()).get('/account/').set('Authorization', `Bearer ${token}`).expect(200);

    expect(res.body.email).toBe(user.email);
  });

  test('PUT /account/ - Update account', async () => {
    const updatedData = { name: 'Updated Name' };
    const updatedUser = { ...user, ...updatedData };

    prismaMock.user.update.mockResolvedValue(updatedUser);

    const res = await request(app.callback())
      .put('/account/')
      .set('Authorization', `Bearer ${token}`)
      .send(updatedData)
      .expect(200);

    expect(res.body.name).toBe(updatedData.name);
  });

  test('POST /account/sign-out - Sign Out', async () => {
    const res = await request(app.callback())
      .post('/account/sign-out')
      .set('Authorization', `Bearer ${token}`)
      .expect(200);

    expect(res.body).toEqual({});
  });
});

describe('Countries Endpoints', () => {
  test('POST /countries/ - Create country', async () => {
    const countryData = {
      name: 'Poland',
      longitude: 1234534.123455,
      latitude: 1234534.123455,
    };
    const createdCountry = {
      ...countryData,
      id: 1,
      userId: user.id,
      createdOn: new Date(),
      updatedOn: new Date(),
      longitude: new Decimal(countryData.longitude),
      latitude: new Decimal(countryData.latitude),
    };

    prismaMock.country.create.mockResolvedValue(createdCountry);

    const res = await request(app.callback())
      .post('/countries/')
      .set('Authorization', `Bearer ${token}`)
      .send(countryData)
      .expect(200);

    expect(res.body.name).toBe(countryData.name);
    expect(res.body).toHaveProperty('id');
  });

  test('GET /countries/ - Get countries list', async () => {
    const countriesList = [
      {
        id: 1,
        name: 'Poland',
        longitude: new Decimal(123.45),
        latitude: new Decimal(67.89),
        userId: user.id,
        createdOn: new Date(),
        updatedOn: new Date(),
      },
    ];

    prismaMock.country.findMany.mockResolvedValue(countriesList);

    const res = await request(app.callback()).get('/countries/').set('Authorization', `Bearer ${token}`).expect(200);

    expect(Array.isArray(res.body)).toBe(true);
    expect(res.body.length).toBeGreaterThan(0);
  });
});

describe('Messages Endpoints', () => {
  test('PUT /messages/:messageId - Update message', async () => {
    const messageId = 1;
    const updateData = { content: 'Updated message content', isSaved: true };
    const updatedMessage = {
      id: messageId,
      userId: user.id,
      content: updateData.content,
      role: 'assistant' as any,
      isSaved: updateData.isSaved,
      createdOn: new Date(),
      updatedOn: new Date(),
    };

    prismaMock.message.update.mockResolvedValue(updatedMessage);

    const res = await request(app.callback())
      .put(`/messages/${messageId}`)
      .set('Authorization', `Bearer ${token}`)
      .send(updateData)
      .expect(200);

    expect(res.body).toEqual({});
  });

  test('POST /messages/ - Send message to AI consultant', async () => {
    const messageContent = { content: 'Hello AI consultant!' };
    const aiResponse = {
      id: 1,
      userId: user.id,
      content: 'AI response',
      role: 'assistant' as any,
      isSaved: false,
      createdOn: new Date(),
      updatedOn: new Date(),
    };

    prismaMock.message.create.mockResolvedValue(aiResponse);

    const res = await request(app.callback())
      .post('/messages/')
      .set('Authorization', `Bearer ${token}`)
      .send(messageContent)
      .expect(200);

    expect(res.body.id).toBe(aiResponse.id);
    expect(res.body.content).toBe(aiResponse.content);
    expect(res.body.role).toBe(aiResponse.role);
  });

  test('GET /messages/ - Get messages list', async () => {
    const messagesList = [
      {
        id: 1,
        userId: user.id,
        content: 'Message 1',
        role: 'user' as any,
        isSaved: true,
        createdOn: new Date(),
        updatedOn: new Date(),
      },
    ];

    prismaMock.message.findMany.mockResolvedValue(messagesList);

    const res = await request(app.callback()).get('/messages/').set('Authorization', `Bearer ${token}`).expect(200);

    expect(Array.isArray(res.body)).toBe(true);
    expect(res.body[0]).toHaveProperty('id');
  });
});
