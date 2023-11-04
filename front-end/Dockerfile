FROM node:21-alpine
WORKDIR /front-end

COPY public/ /front-end/public
COPY src/ /front-end/src
COPY package.json /front-end/

RUN npm install

EXPOSE 3000

CMD ["npm", "start"]
