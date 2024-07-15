FROM eclipse-temurin:17
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN echo "backend admin image $(date)" > /unique_layer.txt
# Add unique layer with a build argument
ARG ADMIN_VERSION
RUN echo "Admin version: $ADMIN_VERSION" > /admin_version.txt
CMD ["java","-jar","/app.jar"]
