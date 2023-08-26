@Library("mylib") _
//实例化demo.groovy这个方法； 把demo.groovy中的函数实例化给mydemo
def mydemo = new org.devops.demo() 
def myTool = new org.devops.Tools()
def sonar  = new org.devops.Sonar()
def art    = new org.devops.artifact()

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
		stage("PushArtifact"){
			steps{
				script{
				    //手动指定仓库ID，把代码放到通用型仓库devops
				    repoID = "devops"
				    targetDir = "devops3/${JOB_NAME}"
				    pkgPath = "target"
				    POM = readMavenPom file: 'pom.xml'
                    env.groupid = "${POM.getGroupId()}"
                    env.artifactID = "${POM.getArtifactId()}"
                    env.artifact_version = "${POM.getVersion()}"
                    env.packing = "${POM.getPackaging()}"
                    pkgName = "${env.artifactID }-${env.artifact_version}.${env.packing}"                   
                    PushNexusArtifact(repoID,targetDir,pkgPath,pkgName)	               
				}
			}
		}
    }
}
