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
            sh "docker build -t ui_tests:1.0.0 ."
            sh "pwd"
        }

        try {
            stage("Run UI tests") {
                sh("mkdir ./allure-report")
                sh "docker run --rm --env-file ./.env -v /home/jenkins/workspace/ui-tests/allure-report:/home/jenkins/workspace/ui-tests/allure-report -t ui_tests:1.0.0"
            }
        }
        finally {
            stage("Allure") {
                sh "pwd"
                generateAllure()
            }
        }
    }
}

def generateAllure() {
    sh "pwd"
    sh "ls -a"
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'allure-report']]
    ])
}
