function getData(){
    const url = document.getElementById('url').dataset.url;
    setInterval(() => {
        fetch(url)
            .then(response => {
                if (response.status !== 200) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                document.getElementById("sequence").innerHTML = text;
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation: ', error);
            });
    }, 1000);
}


function getTime(){
    const url = "http://localhost:8081/openFeign0A/time";
    setInterval(() => {
        fetch(url)
            .then(response => {
                if (response.status !== 200) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                document.getElementById("atime").innerHTML = text;
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation: ', error);
            });
}, 2000);
}

