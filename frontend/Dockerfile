FROM node:20.12.2-alpine as build
WORKDIR /home/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
CMD ["npm", "start"]
EXPOSE 3000