# Build stage
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Criar usuário não-root por segurança
RUN addgroup -S soci && adduser -S soci -G soci
# Copiar JAR do stage anterior
COPY --from=builder /app/target/soci-*.jar app.jar
# Alterar proprietário do arquivo
RUN chown -R soci:soci /app
# Usar usuário não-root
USER soci
# Expor porta
EXPOSE 8080
# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD java -cp app.jar -Dloader.main=com.proyecto.soci.SociApplication \
  org.springframework.boot.loader.PropertiesLauncher check || exit 1
# Variáveis de ambiente padrão
ENV JAVA_OPTS=\"-Xmx512m -Xms256m\" \
    SPRING_PROFILES_ACTIVE=\"prod\" \
    SERVER_PORT=\"8080\"
# Executar aplicação
CMD [\"sh\", \"-c\", \"java \ -jar app.jar\"]
