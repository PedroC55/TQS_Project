FROM node
WORKDIR /acp-frontend
COPY . .
RUN npm install && npm run build
EXPOSE 3001
CMD ["npm", "start"]
