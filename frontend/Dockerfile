# 1단계: 빌드
FROM node:20.12.2-alpine as build
WORKDIR /home/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 2단계: Nginx에서 정적 파일 서빙
FROM nginx:alpine
COPY --from=build /home/app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]