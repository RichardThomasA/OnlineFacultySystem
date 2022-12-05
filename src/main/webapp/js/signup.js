
window.onload = (loadEvent) =>{
    fetch ("../Signup?" + new URLSearchParams({
        method : 'getAllCourses'
    })).then(response => response.json()).then(jsonData => {
            var jsonObject = JSON.stringify(jsonData);
            var userArray =JSON.parse(jsonObject);
            console.log(userArray);
            
        });
}