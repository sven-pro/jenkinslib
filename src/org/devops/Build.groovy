packge.org.devops
//Build, 使用switch语句把使用构建工具与指令分组对应起来，执行完任务一种情况后都要退出循环
def Build(){
    switch("${env.buildTools}") {
        case "npm":
            sh "npm install && npm run build"
            break
        case "yarn":
            sh "yarn build"
            break
        case "maven":
            sh "mvn clean package -DskipTests"
            break
        case "gradle":
            sh "gradle clean build -x test"
            break            
        default:
            echo "error"
            break						   							
    }
    //执行完build之后，判断该项目名称是否以-web结尾，把判断结果赋值给变量skipUnitTest
    if ("${JOB_NAME}".endsWith("-web")) {
        env.skipUnitTest = 'true'					
    } else {
        env.skipUnitTest = 'false'						
    }
}
//test 如果项目名以-service结尾则执行UnitTest方法
def UnitTest(){
    if ("${JOB_NAME}".endsWith("-service")) {
        switch("${env.buildTools}") {
            case "maven":
                sh "/mvn test"
                break
            case "gradle":
                sh "gradle test"
                break            
            default:
                echo "error"
                break						   							
        }  
    }    
}

