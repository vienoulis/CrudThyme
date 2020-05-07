$(document).ready(function () {
    // $.ajax('https://repetitora.net/api/JS/Images',{
    //     success: function (data) {
    //         data.forEach(el =>{
    //             const img = document.createElement('img');
    //             img.src = el.thumbnail;
    //             document.querySelector('body').appendChild(img);
    //         });
    //     }
    // });

    $.ajax("http://localhost:8080/GET/user_list",{
        success: function (data) {

            data.forEach(el =>{
                document.append(document.createElement("tr"));
                document.body.append(document.createElement('td').innerHTML = el.id);
                document.body.append(document.createElement('td').innerHTML = el.name);
                // testElement.innerHTML = el.name;
                // document.body.append(testElement);
            });

        }
    });
});