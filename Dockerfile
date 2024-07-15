FROM eclipse-temurin:17
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN echo "backend admin image $(date)" > /unique_layer.txt
CMD ["java","-jar","/app.jar"]
