# 베이스 이미지 설정 -> openJDK17 사용한 경량화된 alpine linux 사용
FROM bellsoft/liberica-openjdk-alpine:17

CMD ["./gradlew", "clean", "build"]

# 애플리케이션의 JAR파일의 위치를 설정
ARG JAR_FILE=build/libs/*.jar

# 컨테이너 내의 tmp볼륨 생성
VOLUME /tmp

# 컨테이너에 app.jar라는 이름으로 추가
COPY ${JAR_FILE} app.jar

# 컨테이너가 실행될 때 JAR파일을 실행하도록 설정
ENTRYPOINT ["java", "-jar", "/app.jar"]

# 애플리케이션이 사용할 포트
EXPOSE 9005