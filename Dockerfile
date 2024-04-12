FROM maven:3.9.0

RUN mkdir -p /home/unixuser/ui_tests

WORKDIR /home/unixuser/ui_tests

COPY . /home/unixuser/ui_tests

RUN mvn build

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]