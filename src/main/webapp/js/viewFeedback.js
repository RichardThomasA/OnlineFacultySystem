
function updateSelector(){
    const feedbackView = document.getElementById("feedback-view-selector").value;
    console.log(feedbackView);
    if(feedbackView =="ViewAll"){
        populateViewAllTable();
    }
    else{
        console.log("invalid input");
    }
}

function populateViewAllTable(){
    fetch ("../Feedback",{
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          method: "POST",
          body: JSON.stringify({method: 'getAllFeedbackWithAnswers'})
    }).then(response => response.json())
    .then(jsonData =>{
        console.log("json recieved");
        var jsonObject = JSON.stringify(jsonData);
        console.log(jsonObject);
        var table = '';
        table = '<table class="table">'+
        '<thead>'+
        '<tr>'+
            '<th scope="col">Question ID</th>'+
            '<th scope="col">Faculty</th>'+
            '<th scope="col">Student</th>'+
            '<th scope="col">Question</th>'+
            '<th scope="col">Answer</th>'+
        '</tr>'+
        '</thead>'+
        '<tbody>';
        var userArray =JSON.parse(jsonObject);
        console.log(userArray);
        userArray.forEach((element) => {
            console.log(element);
            table +='<tr>';
            table +='<td>'+element.fbId+'</td>';
            table +='<td>'+element.facultyName+'</td>';
            if(element.hasOwnProperty("studentName")){
                table +='<td>'+element.studentName+'</td>';
            }else{
                table +='<td>'+"-"+'</td>';
            }
            table +='<td>'+element.question+'</td>';
            if(element.hasOwnProperty("studentName")){
                table +='<td>'+element.answer+'</td>';
            }else{
                table +='<td>'+"-"+'</td>';
            }
            
            table +='</tr>';
        });
        table +='</tbody>';
        table +='</table>';

        document.getElementById("feedback-table-container").innerHTML = table;
    });
}