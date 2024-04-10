timeout(60) {
    node('maven-slave') {

        wrap([$class: 'BuildUser']) {
            currentBuild.description = """
                build user : $BUILD_USER
                branch : $BRANCH
            """

            params = readYaml text: env.YAML_CONFIG ?: null
            echo params
            if (params != null) {
                echo env.getProperty('BASE_URL')
                for (param in params.entrySet()) {
                    echo env.getProperty('BASE_URL')
                    env.setProperty(param.getKey(), param.getValue)
                }
            }
        }

        stage("Checkout") {
            checkout scm
        }

        stage("Create configurations") {
            sh "echo BASE_URL=${env.getProperty('BASE_URL')} > ./.env"
            sh "echo BROWSER=${env.getProperty('BROWSER')} > ./.env"
            sh "echo VERSION_BROWSER=${env.getProperty('VERSION_BROWSER')} >> ./.env"
            sh "echo REMOTE=${env.getProperty('REMOTE')} >> ./.env"
        }

        stage("Run UI tests") {
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