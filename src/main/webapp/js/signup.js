
window.onload = (loadEvent) =>{
    fetch ("../Signup?" + new URLSearchParams({
        method : 'getAllCourses'
    })).then(response => response.json()).then(jsonData => {
            var jsonObject = JSON.stringify(jsonData);
            //console.log(jsonObject);
            var courseObject =JSON.parse(jsonObject);
            console.log(courseObject);
            const map = new Map(Object.entries(courseObject));
            console.log(map);
            
            var options = '';
            var selectedKey = '';
            const keyIterator = map.keys();
            const valueIterator = map.values();
            for( var i = 0;i<map.size; i++){
                var value = valueIterator.next().value;
                var key = keyIterator.next().value;
                if(value=='All Courses'){
                    selectedKey = key;
                } 
                console.log(key);
                console.log(value);
                console.log(document.getElementById("course-select").options.length);
                document.getElementById("course-select").options.add(new Option(value,key));
                //options+='<option value='+key+'>'+value+'</option>';
            }
            console.log(options);
            //document.getElementById("course-select").innerHTML = options;
            document.getElementById("course-select").value=selectedKey;
        });
}