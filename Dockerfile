FROM eclipse-temurin:17
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
# Add a unique layer for backend admin
ARG BUILD_DATE
RUN echo "backend admin image $BUILD_DATE" > /unique_layer_backend_admin.txt
CMD ["java","-jar","/app.jar"]
