timeout(60) {
    node("maven-slave") {

        wrap([$class: 'BuildUser']) {
            currentBuild.description = """
                build user : $BUILD_USER
                branch : $REFSPEC
            """

            params = readYaml text: env.YAML_CONFIG ?: null
            if (params != null) {
                for (param in params.entrySet()) {
                    env.setProperty(param.getKey(), param.getValue)
                }
            }
        }

        stage("Checkout") {
            checkout scm
        }

        stage("") {
            sh "echo BROWSER=${env.getProperty('BROWSER')} > ./.env"
            sh "echo BROWSER_VESION=${env.getProperty('BROWSER_VESION')} >> ./.env"
            sh "echo REMORE_URL=${env.getProperty('REMORE_URL')} >> ./.env"
        }

        stage("Run UI tests") {
            //sh ("mvn test -Dbrowser=$BOWSER -Dbrowser_version=$BROWSER_VESION -Dremote=$REMORE_URL")
            sh("mkdir ./reports")
            sh "docker run --rm --env-file -v ./report:/root/ui_tests/allure-result ./ ./.env -t ui_tests:1.0.0"
        }

        stage("Publish allure results") {
            allure([
                    reportBuildPolicy: 'ALWAYS',
                    results          : ['./reports']
            ])
        }
    }
}