FROM node
WORKDIR /admin-frontend
COPY . .
RUN npm install --force && npm run build
EXPOSE 3000
CMD ["npm", "start"]