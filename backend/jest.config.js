/** @type {import('jest').Config} */
const config = {
  verbose: true,
  testEnvironment: 'node',
  testMatch: ['**/?(*.)+(spec.ts)'],
  transform: {
    '^.+\\.(ts)$': 'ts-jest',
  },
  collectCoverage: true,
  collectCoverageFrom: ['src/**/*.{js,ts}', '!**/node_modules/**', '!**/services/**', '!src/*.{js,ts}'],
  coverageDirectory: 'coverage',
  coverageReporters: ['text', 'lcov'],
  watchPathIgnorePatterns: ['globalConfig'],
  roots: ['<rootDir>'],
  modulePaths: ['src'],
  moduleDirectories: ['node_modules'],
  setupFilesAfterEnv: ['<rootDir>/src/singleton.ts'],
  testTimeout: 10000,
};

module.exports = config;
