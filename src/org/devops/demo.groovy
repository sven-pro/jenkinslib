package org.devops

def GetUserNameByID(id){
    users = [
                ["id":1, "name":"jenkins1"],
                ["id":2, "name":"jenkins2"],
                ["id":3, "name":"jenkins3"],
    ]

     for(i in users) {
        if (i["id"] == id) {
            return i["name"]			
        }
    }
    return "null"
}