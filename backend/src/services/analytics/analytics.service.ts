import Mixpanel from 'mixpanel';

import config from 'config';

import logger from 'logger';

import { User } from 'types';

const mixpanel = config.MIXPANEL_API_KEY
  ? Mixpanel.init(config.MIXPANEL_API_KEY, { debug: config.IS_DEV })
  : null;

const track = (event: string, data = {}) => {
  if (!mixpanel) {
    logger.error('[Mixpanel] The analytics service was not initialized');
    return;
  }

  try {
    const trackingData = {
      ...('userId' in data ? { distinct_id: data.userId } : {}),
      ...data,
    };

    mixpanel.track(event, trackingData);
  } catch (e) {
    logger.error(e);
  }
};

const trackServerError = (message: string, payload: object | unknown, userId?: string) => {
  track('Server error', { userId, message, payload });
};

export const setUser = (user: User) => {
  if (!mixpanel) {
    logger.error('[Mixpanel] The analytics service was not initialized');
    return;
  }

  try {
    mixpanel.people.set(String(user.id), user);
  } catch (e) {
    logger.error(e);
  }
};

export const incrementField = (user: User, property: keyof User, count = 1) => {
  if (!mixpanel) {
    logger.error('[Mixpanel] The analytics service was not initialized');
    return;
  }

  try {
    mixpanel.people.increment(String(user.id), property, count);
  } catch (e) {
    logger.error(e);
  }
};

export default {
  track,
  trackServerError,
  incrementField,
  setUser,
};
