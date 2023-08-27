package org.devops

//Push artifact
def PushNexusArtifact(repoID,targetDir,pkgPath,pkgName,newPkgName){
	withCredentials([usernamePassword(credentialsId: '5405172c-442d-46ed-b5d8-dc74f992ea4f', 
									  passwordVariable: 'PASSWD', 
									  usernameVariable: 'USERNAME')]) {
				sh """
				   curl -X 'POST' \
				  "http://10.1.1.140:8081/service/rest/v1/components?repository=${repoID}" \
				  -H 'accept: application/json' \
				  -H 'Content-Type: multipart/form-data' \
				  -F "raw.directory=${targetDir}" \
				  -F "raw.asset1=@${pkgPath}/${pkgName}" \
				  -F "raw.asset1.filename=${newPkgName}" \
				  -u ${USERNAME}:${PASSWD}
			  """
  	}
}
