package org.devops

//scan the code
def CodeScan(){
    withSonarQubeEnv(credentialsId: 'b8a196d0-e69a-4728-abf5-402ecea4139a') {						
        sh """
            sonar-scanner \
            -Dsonar.login=${SONAR_AUTH_TOKEN} \
            -Dsonar.host.url=${SONAR_HOST_URL} \
            -Dsonar.branch.name=${params.branchName}							
        """
    }
}
