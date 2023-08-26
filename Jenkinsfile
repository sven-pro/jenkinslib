@Library("mylib") _
//实例化demo.groovy这个方法； 把demo.groovy中的函数实例化给mydemo
def mydemo = new org.devops.demo() 
def myTool = new org.devops.Tools()
def sonar  = new org.devops.Sonar()
def art    = new org.devops.Artifact()
def build  = new org.devops.Build()

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
					build.Build()
				}
			}
		}
		stage("Test"){
			when {
			  environment name: 'skipUnitTest', value: 'false'
			}			
			steps{
				script{
					//调用共享库中的test方法
					build.UnitTest()
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
