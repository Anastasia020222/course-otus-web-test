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
        }

        try {
            stage("Run UI tests") {
                sh("rm -rf /root/allure/*")
                sh "docker run --rm --env-file ./.env -v m2:/root/.m2 -v allure:/home/unixuser/ui_tests/allure-results -t ui_tests:1.0.0"
            }
        }
        finally {
            stage("Allure") {
                sh("mkdir ./allure-results")
                sh("cp /root/allure/* ./allure-results/")
                //generateAllure()
            }
        }
    }
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: './allure-results']]
    ])
}
