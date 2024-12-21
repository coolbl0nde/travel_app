# INSTALLER - Stage 1
FROM node:20-bullseye-slim AS installer
WORKDIR /app

RUN apt-get update && apt-get install -y libssl1.1
RUN npm install --global --no-update-notifier --no-fund pnpm@^8.0.0

COPY . .

RUN pnpm fetch
RUN pnpm install

RUN pnpm prisma:migrate-dev && pnpm prisma:generate

WORKDIR /app

# DEVELOPMENT - Stage 2
FROM installer AS development
WORKDIR /app

CMD ["pnpm", "run", "dev"]

# RUNNER - Stage 3
FROM node:20-bullseye-slim AS runner
WORKDIR /app

RUN apt-get update && apt-get install -y libssl1.1
RUN npm install --global --no-update-notifier --no-fund ts-node@10.9.1

RUN addgroup --system --gid 1001 app && adduser --system --uid 1001 app
USER app

COPY --from=installer /app .

EXPOSE 3001

CMD ["ts-node", "backend/src/app.ts"]
