# 베이스 이미지
FROM adoptopenjdk:8-jre-hotspot
# 작업 디렉토리 설정
WORKDIR /app
# Gradle 빌드 수행 (빌드 도구에 따라 다르게 수정 필요)
COPY build/libs/*.jar app.jar
# 빌드 환경 설정
RUN ["./gradlew", "clean", "build"]
# 포트 노출 및 환경 변수 설정
EXPOSE 8282
# 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]