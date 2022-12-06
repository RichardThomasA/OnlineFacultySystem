
//array of all the feedbacks
let FeedbackDataArray;

window.onload = (loadEvent) =>{
    const queryString =window.location.search;
    console.log(queryString);
    if(queryString == '?user=Faculty'){
        var feedbackViewSelector = document.getElementById("feedback-view-selector");
        feedbackViewSelector.removeChild(getOptionByValue(feedbackViewSelector,'ViewAll'));
        feedbackViewSelector.removeChild(getOptionByValue(feedbackViewSelector,'FacultyWise'));
    }

    //we load all feedback data
    loadAllFeedback();
};

function getOptionByValue (select, value) {
    var options = select.options;
    for (var i = 0; i < options.length; i++) {
        if (options[i].value === value) {
            return options[i]  
        }
    }
    return null
}



function loadAllFeedback(){
    fetch ("../Feedback",{
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          method: "POST",
          body: JSON.stringify({method: 'getAllFeedbackWithAnswers'})
    }).then(response => response.json())
    .then(jsonData => {
        //
        var jsonObject = JSON.stringify(jsonData);
        var FeedbackDataArray =JSON.parse(jsonObject);
        console.log("all data loaded");
    });
}

function updateSelector(){
    const feedbackView = document.getElementById("feedback-view-selector").value;
    console.log(feedbackView);
    if(feedbackView =="ViewAll"){
        populateViewAllTable();
    }
    else if(feedbackView =="DateWise"){
        createSelectorDiv('DateWise')
    }
    else{
        console.log("invalid input");
    }
}

function createSelectorDiv(view){
    var userCookie =getCookie("username");
    if(userCookie!=null ||userCookie!= ""){
        //if user cookie is set,poll with username
        if(view=='DateWise'){
            var content = '<div class="row g-3 mb-2">'
            +'<div class="col-auto">'
            +'    <label class="form-label mt-2">Select Date</label>'
            +'</div>'
            +'<div class="col-auto">'
            +'    <select class="form-select" id="date-view-selector">';
            FeedbackDataArray.forEach((element)=>{
                if(element.hasOwnProperty("date")){
                    content +='<option value="'+element.date+'">'+element.date+'</option>';
                }
            });
            
            content+= '    </select>'
            +'</div>'
            +'<div class="col-auto">'
            +'    <button class="btn btn-success" onclick="updateSelector()">Select</button>'
            +'</div>'
        +'</div>';
        }
        document.getElementById("feedback-selector-container").innerHTML +=content;
    }
    else{
        console.log("no cookie set");
    }
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
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