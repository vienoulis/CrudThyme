$(document).ready(function createTable () {
    $.ajax("http://localhost:8080/GET/user_list", {
        success: function (data) {
            let parent = document.querySelector('#testtable');
            data.forEach(el => {
                let tr = document.createElement('tr');
                let roles = "";
                el.roleSet.forEach(obj => {
                    roles = roles + " " + obj.role;
                });
                tr.innerHTML = '<td> ' + el.id + '</td>' +
                    '<td> ' + el.name + '</td>' +
                    '<td> ' + el.lastName + '</td>' +
                    '<td> ' + el.age + '</td>' +
                    '<td> ' + el.email + '</td>' +
                    '<td> ' + roles + '</td>' +
                    '<td> <button id ="edit' + el.id + '" class="btn edit-button btn-info" data-toggle="modal" data-target="#editModal' + el.id + '"> Edit </button></td>' +
                    '<td> <button id ="delete' + el.id + '" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal' + el.id + '"> Delete </button></td>';
                parent.appendChild(tr);
            });
        }
    });
});