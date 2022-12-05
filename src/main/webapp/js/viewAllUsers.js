
//var jsonObject= '';

window.onload = (loadEvent) =>{
    fetch ("../AdminHome?" + new URLSearchParams({
        method : 'getAllUsers'
    })).then(response => response.json()).then(jsonData => {
            var jsonObject = JSON.stringify(jsonData);
            //displayUsersAsTable(jsonObject);
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
            var userArray =JSON.parse(jsonObject);
            console.log(userArray);
            //for(var i =0;i<userArray.lenght())
            userArray.forEach((element) => {
                console.log(element);
                table +='<tr>';
                table +='<td>'+element.id+'</td>';
                table +='<td>'+element.userName+'</td>';
                table +='<td>'+element.password+'</td>';
                table +='<td>'+element.course+'</td>';
                table +='<td>'+element.userType+'</td>';
                table +='</tr>';
            });
            table +='</tbody>';
            table +='</table>';

            document.getElementById("user-table-container").innerHTML = table;
        });
}

// function displayUsersAsTable(jsonObject){
//     var table = '';
//     table = '<table class="table">'+
//     '<thead>'+
//       '<tr>'+
//         '<th scope="col">User ID</th>'+
//         '<th scope="col">UserName</th>'+
//         '<th scope="col">Password</th>'+
//         '<th scope="col">Course</th>'+
//         '<th scope="col">User Type</th>'+
//       '</tr>'+
//     '</thead>'+
//     '<tbody>';
//     var userArray =JSON.parse(jsonObject);
//     userArray.array.forEach(element => {
//         table +='<tr>';
//         table +='<th>'+element.id+'</th>';
//         table +='<th>'+element.userName+'</th>';
//         table +='<th>'+element.password+'</th>';
//         table +='<th>'+element.course+'</th>';
//         table +='<th>'+element.userType+'</th>';
//         table +='</tr>';
//     });
//     table +='</tbody>';
//     table +='</table>';

//     document.getElementById("user-table-container").innerHTML = table;
// }

