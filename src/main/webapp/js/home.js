const rootUrl ="http://localhost:8080/OnlineFacultySystem";

var jsonObject= '';
function fetchAllUsers(){
    fetch ("../AdminHome?" + new URLSearchParams({
        method : 'getAllUsers'
    })).then(response => response.json()).then(jsonData => {
            jsonObject = JSON.stringify(jsonData);
        });

    console.log(jsonObject);
    
}

function logMessage(){
    console.log("msg");
}