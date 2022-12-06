
window.onload = (loadEvent) =>{
    fetch ("../Feedback?" + new URLSearchParams({
        method : 'getAllFaculty'
    })).then(response => response.json()).then(jsonData => {
            var jsonObject = JSON.stringify(jsonData);
            //console.log(jsonObject);
            var facultyObject =JSON.parse(jsonObject);
            //console.log(facultyObject);
            const map = new Map(Object.entries(facultyObject));
            //console.log(map);
            
            const keyIterator = map.keys();
            const valueIterator = map.values();
            for( var i = 0;i<map.size; i++){
                var value = valueIterator.next().value;
                var key = keyIterator.next().value;
                document.getElementById("feedback-faculty-select").options.add(new Option(value,key));
            }
        });
}