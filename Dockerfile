FROM maven:3.9-calipse-temurin-8

RUN mkdir -p /home/unixuser/ui_tests

WORKDIR /home/unixuser/ui_tests

COPY . /home/unixuser/ui_tests

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]