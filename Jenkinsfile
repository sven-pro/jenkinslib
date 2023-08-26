@Library("mylib") _

//实例化demo.groovy这个方法； 把demo.groovy中的函数实例化给mydemo
def mydemo = new org.devops.demo() 
def myTool = new org.devops.Tools()
def sonar  = new org.devops.Sonar()

pipeline{
	agent { label 'build01'}
    options {
        skipDefaultCheckout true
    } 
    stages{
        stage("CheckOut"){
            steps{
                script{
                    currentBuild.description = "branchName: ${env.branchName}"
                    myTool.GetCode("${env.branchName}", "${env.srcUrl}")
                }
            }
        }
		stage("Build"){
			steps {
				script{
					sh "mvn clean package -DskipTests"
				}
			}
		}
		stage("Test"){
			steps{
				script{
					sh "mvn test"
				}
			}
		}
        stage("CodeScan"){
            steps{
                script{
                    sonar.CodeScan()
                }
            }
        }
    }
}
