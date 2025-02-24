FROM node:20

WORKDIR /app
COPY . /app

RUN npm install

RUN npx expo export -p web

RUN npm install -g serve

EXPOSE 8081

CMD ["npx", "expo", "serve", "--port", "5000"]