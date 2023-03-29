// fetch('http://localhost:8088/api/region')
//     .then(response => response.json())
//     .then(data => data)
//     .catch(error => console.log(error))
let baseUrl = "http://localhost:8088/api/";
let table = null;
$(document).ready(function() {
    table = $('#myTable').DataTable({
        "ajax": {
        "url": baseUrl + "region",
        "type": "GET",
        "dataSrc": "data"
    },
        "columnDefs": [
            {
                "targets": [0],
                "width": "2%"
            },
            {
                "targets": [1],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [2],
                "width": "60%"
            },
            {
                "targets": [3],
                "width": "30%",
                "searchable": false,
                "orderable": false
            }
        ],
        "columns": [
            {
                "data": null,
                "name": "no",
                "autoWidth": true,
                "render": function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }},
            {"data": "id", "name": "id", "autoWidth": true},
            {"data": "name", "name": "name", "autoWidth": true},
            {
                "render": function (data, type, full, meta) {

            return '<button class="btn btn-primary edit" id="edit" data-id="'+full.id+'">Edit</button>' +
                    '<button class="btn btn-danger delete" id="delete" data-id="'+full.id+'">Delete</button>';
                }}   
        ]
});
$('#submit').on("click", function(e){
   e.preventDefault();
   Submit(); 
});
});

$(document).on('click', '.edit', function() {
    var id = $(this).data('id');
    Getbyid(id);
  });

$(document).on('click', '.delete', function() {
    var id = $(this).data('id');
    Delete(id);
});
  

function Getbyid(id){
    $.ajax({
        url: baseUrl + 'region/' + id,
        type: 'GET',
        dataType: 'json',
        success: function(result) {
          console.log(result.data);
          $('#id').val(result.data.id);
          $('#name').val(result.data.name);
          $('#exampleModal').modal('show');
        }
      });
}
function Submit(){
    var id = $("#id").val();
    if (id == "" || id == " "){
        Post();
    } else {
        Put(id);
    }
}
function Post(){
    var region = new Object();
    region.name = $("#name").val();
    $.ajax({
        url:  baseUrl + "region",
        type: "POST",
        data: JSON.stringify(region),
        headers: {
            'Content-Type': 'application/json'
        }
    }).done((result) =>{
        if(result.status == 200){
            Swal.fire(
                'Good job!',
                'Your data has been saved!',
                'success'
            )
            $("#exampleModal").modal("toggle");
            table.ajax.reload();
        } else if(result.status == 400){
            Swal.fire(
                'Watch out!',
                'Duplicate Data',
                'error'
            )
        }
        Reset();
    }).fail((error) =>{
        Swal.fire(
            'Warning!',
            'Check Your Connection Internet',
            'warning'
        )
        Reset();
    });
}
function Put(id){
    var region = new Object();
    region.name = $("#name").val();
    $.ajax({
        url: baseUrl + "region/" + id,
        type: "PUT",
        data: JSON.stringify(region),
        headers: {
            'Content-Type': 'application/json'
        }
    }).done((result) =>{
        if(result.status == 200){
            Swal.fire(
                'Good job!',
                'Your data has been updated!',
                'success'
            )
            $("#exampleModal").modal("toggle");
            table.ajax.reload();
        } else if(result.status == 400){
            Swal.fire(
                'Watch out!',
                'Duplicate Data',
                'error'
            )
        }
        Reset();
    }).fail((error) =>{
        Swal.fire(
            'Warning!',
            'Check Your Connection Internet',
            'warning'
        )
        Reset();
    });
}

function Delete(id){
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: baseUrl + "region/" + id,
                type: "DELETE",
                success: function(result){
                    Swal.fire(
                        'Deleted!',
                        'Your data has been deleted.',
                        'success'
                    )
                    table.ajax.reload();
                },
                error: function(error){
                    Swal.fire(
                        'Error!',
                        'Something went wrong',
                        'error'
                    )
                }
            });
        }
    })
}


function Reset(){
    $("#name").val("");
    $("#id").val("");
}
