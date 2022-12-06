
function updateSelector(){
    const feedbackView = document.getElementById("feedback-view-selector").value;
    console.log(feedbackView);
    if(feedbackView =="ViewAll"){
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
        });
    }
    else{
        console.log("invalid input");
    }
}