
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

    var userCookie =getCookie("usernameCookie");
    console.log("the username cookie is "+userCookie);
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
        FeedbackDataArray =JSON.parse(jsonObject);
        console.log("all data loaded");
        console.log(FeedbackDataArray);
    });
}

function updateSelector(){
    const feedbackView = document.getElementById("feedback-view-selector").value;
    console.log(feedbackView);
    if(feedbackView =="ViewAll"){
        populateViewAllTable();
    }
    else if(feedbackView =="DateWise"){
        createSelectorDiv('DateWise');
    }
    else if(feedbackView =='FacultyWise'){
        createSelectorDiv('FacultyWise');
    }
    else if(feedbackView =='StudentWise'){
        createSelectorDiv('StudentWise');
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
            var datesArray =[];
            FeedbackDataArray.forEach((element)=>{
                if(element.hasOwnProperty("date")){
                    if(datesArray.includes(element.date)==false){
                        content +='<option value="'+element.date+'">'+element.date+'</option>';
                        datesArray.push(element.date);
                    }
                    
                }
            });
            
            content+= '    </select>'
            +'</div>'
            +'<div class="col-auto">'
            +'    <button class="btn btn-success" onclick="populateDateWiseTable()">Select</button>'
            +'</div>'
        +'</div>';
        }else if(view=='FacultyWise'){
            var content = '<div class="row g-3 mb-2">'
            +'<div class="col-auto">'
            +'    <label class="form-label mt-2">Select Faculty</label>'
            +'</div>'
            +'<div class="col-auto">'
            +'    <select class="form-select" id="faculty-view-selector">';
            var facultyArray =[];
            FeedbackDataArray.forEach((element)=>{
                if(element.hasOwnProperty("facultyName")){
                    if(facultyArray.includes(element.facultyName)==false){
                        content +='<option value="'+element.facultyName+'">'+element.facultyName+'</option>';
                        facultyArray.push(element.facultyName);
                    }
                }
            });
            
            content+= '    </select>'
            +'</div>'
            +'<div class="col-auto">'
            +'    <button class="btn btn-success" onclick="populateFacultyWiseTable()">Select</button>'
            +'</div>'
        +'</div>';
        }else if(view=='StudentWise'){
            var content = '<div class="row g-3 mb-2">'
            +'<div class="col-auto">'
            +'    <label class="form-label mt-2">Select Student</label>'
            +'</div>'
            +'<div class="col-auto">'
            +'    <select class="form-select" id="date-view-selector">';
            FeedbackDataArray.forEach((element)=>{
                if(element.hasOwnProperty("studentName")){
                    content +='<option value="'+element.studentName+'">'+element.studentName+'</option>';
                }
            });
            
            content+= '    </select>'
            +'</div>'
            +'<div class="col-auto">'
            +'    <button class="btn btn-success" onclick="populateDateWiseTable()">Select</button>'
            +'</div>'
        +'</div>';
        }
        var selectorContainer = document.getElementById("feedback-selector-container");
        var totalChildren = document.getElementById("feedback-selector-container").childNodes.length;
        console.log('total children = '+totalChildren);
        if(totalChildren>3){
            selectorContainer.removeChild(selectorContainer.lastChild);
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

function populateFacultyWiseTable(){
    var facultySelected = document.getElementById("faculty-view-selector").value;
    if(facultySelected!=null || facultySelected!=''){
        //if faculty is valid
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
        FeedbackDataArray.forEach((element)=>{
            if(element.facultyName==facultySelected){
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
            }
           
        });
        table +='</tbody>';
        table +='</table>';

        document.getElementById("feedback-table-container").innerHTML = table;
    }
}

function populateDateWiseTable(){
    var dateSelected = document.getElementById("date-view-selector").value;
    if(dateSelected!=null ||dateSelected!=''){
        //if date is valid
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
        FeedbackDataArray.forEach((element)=>{
            if(element.date==dateSelected){
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
            }
           
        });
        table +='</tbody>';
        table +='</table>';

        document.getElementById("feedback-table-container").innerHTML = table;

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