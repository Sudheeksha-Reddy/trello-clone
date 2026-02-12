# ---------- Build stage ----------
FROM node:18-alpine AS build
WORKDIR /app

# 1) Copy only dependency files first (this is the main caching trick)
COPY package*.json ./

# If you have package-lock.json, use npm ci for repeatable + faster installs
RUN npm ci

# 2) Now copy the rest of the source code
COPY . .

# 3) Build
RUN npm run build

# ---------- Nginx stage ----------
FROM nginx:alpine

# If your build output is not exactly /app/dist/frontend, adjust this folder name
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf
