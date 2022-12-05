
//var jsonObject= '';

window.onload = (loadEvent) =>{
    fetch ("../AdminHome?" + new URLSearchParams({
        method : 'getAllUsers'
    })).then(response => response.json()).then(jsonData => {
            var jsonObject = JSON.stringify(jsonData);
            displayUsersAsTable(jsonObject);
        });
}

function displayUsersAsTable(jsonObject){
    var table = '';
    table = '<table class="table">'+
    '<thead>'+
      '<tr>'+
        '<th scope="col">User ID</th>'+
        '<th scope="col">UserName</th>'+
        '<th scope="col">Password</th>'+
        '<th scope="col">Course</th>'+
        '<th scope="col">User Type</th>'+
      '</tr>'+
    '</thead>'+
    '<tbody>';
    jsonObject.array.forEach(element => {
        table +='<tr>';
        table +='<th>'+element.id+'</th>';
        table +='<th>'+element.userName+'</th>';
        table +='<th>'+element.password+'</th>';
        table +='<th>'+element.course+'</th>';
        table +='<th>'+element.userType+'</th>';
        table +='</tr>';
    });
    table +='</tbody>';
    table +='</table>';

    document.getElementById("user-table-container").innerHTML = table;
}

