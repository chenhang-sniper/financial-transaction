# 使用官方的Java 21基础镜像
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 将构建好的jar文件复制到镜像中
COPY target/financial-transaction-api-1.0-SNAPSHOT.jar /app/financial-transaction-manager.jar

# 暴露应用程序的端口
EXPOSE 8080

# 启动应用程序
ENTRYPOINT ["java", "-jar", "financial-transaction-manager.jar"]