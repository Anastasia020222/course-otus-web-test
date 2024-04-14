timeout(60) {
    node('maven-slave') {

        wrap([$class: 'BuildUser']) {
            currentBuild.description = """
                build user : $BUILD_USER
                branch : $BRANCH
            """

            params = readYaml text: env.YAML_CONFIG ?: null
            if (params != null) {
                for (param in params.entrySet()) {
                    env.setProperty(param.getKey(), param.getValue())
                }
            }
        }

        stage("Checkout") {
            checkout scm
        }

        stage("Create configurations") {
            sh "echo BASE_URL=${env.getProperty('BASE_URL')} > ./.env"
            sh "echo BROWSER=${env.getProperty('BROWSER')} >> ./.env"
            sh "echo VERSION_BROWSER=${env.getProperty('VERSION_BROWSER')} >> ./.env"
            sh "echo REMOTE=${env.getProperty('REMOTE')} >> ./.env"
        }

        stage("Build Docker image") {
            // Сборка Docker-образа
            sh "pwd"
            sh "docker build -t ui_tests:1.0.0 ."
            sh "pwd"
        }

        stage("Run UI tests") {
            sh("mkdir ./reports")
            sh "docker run --rm --env-file ./.env -v /home/jenkins/workspace/ui-tests/reports:/home/unixuser/ui_tests/target/allure-result -t ui_tests:1.0.0"
        }

        post {
            always {
                // Этап публикации отчетов Allure
                allure([
                        includeProperties: false,
                        jdk: '',
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}