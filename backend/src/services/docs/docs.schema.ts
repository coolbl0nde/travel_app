import { z } from 'zod';

import { docsService } from 'services';

export const emptySchema = docsService.registerSchema('EmptyObject', z.object({}));
