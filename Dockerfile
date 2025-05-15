FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean package
CMD ["java", "-cp", "target/classes:~/.m2/repository/nz/ac/waikato/cms/weka/weka-stable/3.8.6/weka-stable-3.8.6.jar", "workshop.Main"]