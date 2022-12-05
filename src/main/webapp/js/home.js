const rootUrl ="http://localhost:8080/OnlineFacultySystem";

function fetchAllUsers(){
    var jsonObject = fetch ("OnlineFacultySystem/AdminHome?" + new URLSearchParams({
        method : 'getAllUsers'
    })).then(response => response.json());

    //console.log(jsonObject);
    
}

function logMessage(){
    console.log("msg");
}