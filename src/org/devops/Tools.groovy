package org.devops

//Download the code
def GetCode(branchName,srcUrl){
    checkout([$class: 'GitSCM', 
                branches: [[name: branchName]], 
                extensions: [], 
                userRemoteConfigs: [[
                    credentialsId: '9d02cccf-5f45-46d6-b4e5-81ff97809c12', 
                    url: srcUrl]]])
}
