FROM node
WORKDIR /store-frontend
COPY . .
RUN npm install && npm run build
EXPOSE 3002
CMD ["npm", "start"]