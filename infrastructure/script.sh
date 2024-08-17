# Step 1: Clone the repository from the provided environment variable
git clone "$GIT_REPOSITORY_URL" /app/git

# Step 2: Navigate into the cloned repository's directory
cd /app/git

# Step 3: Compile the Java application using Maven
mvn clean package

# Step 4: Move the generated JAR file to the /app directory
mv -f target/*.jar /app/artifacts/app.jar
rm -rf /app/git
