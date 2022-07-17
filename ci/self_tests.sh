echo "Starting unit tests"
./gradlew clean
./gradlew self_tests:test
./gradlew self_tests:allureReport