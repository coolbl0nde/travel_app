import { PrismaClient } from '@prisma/client';

import 'dotenv/config';

const database = new PrismaClient();

export default database;
