@Library("mylib") _

//实例化demo.groovy这个方法； 把demo.groovy中的函数实例化给mydemo
def mydemo = new org.devops.demo() 

pipeline{
    agent any

    options {
        skipDefaultCheckout true
    }
        
    stages{
        stage("run"){
            steps{
                script{

                    //user sharedlibrary function
                    id = 1
                    name = mydemo.GetUserNameByID(id)
                    println(name)

                    //use resource config
                    data = libraryResource 'config/data.json'
                    println(data)

                }
            }
        }
    }
}
