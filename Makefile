
run:
	./gradlew -x test build
	docker-compose up --build

test:
	./gradlew test