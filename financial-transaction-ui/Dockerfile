# 使用Node.js镜像来构建前端
FROM node:21 AS build

# 设置工作目录
WORKDIR /app

# 复制package.json和package-lock.json
COPY package*.json ./

# 安装依赖
RUN npm install

# 复制源代码
COPY . .

# 构建前端项目
RUN npm run build

# 使用Nginx镜像来托管静态文件
FROM nginx:alpine

# 复制构建输出到Nginx的静态文件目录
COPY --from=build ./dist /usr/share/nginx/html

# 暴露Nginx的端口
EXPOSE 80

# 启动Nginx
CMD ["nginx", "-g", "daemon off;"]