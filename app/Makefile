install: # install
	./gradlew clean install

run-dist: # run
	./build/install/app/bin/app

check-updates: #check updates
	./gradlew dependencyUpdates

report:
	./gradlew jacocoTestReport

lint: # checkstyle
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

.PHONY: build

build: # build
	./gradlew clean build
