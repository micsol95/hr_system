/**
 * Created by Серый on 02.05.2016.
 */
$(document).ready(function() {



    $(document).on("click",".getModalStudent",function(){

        id  = parseInt(($(this).attr("candidate_id")));

        $.ajax({
            url: "http://localhost:8080/hr_system-1.0-SNAPSHOT/admin/answer_candidate",
            type: "GET",
            //   contentType : "application/json",
            //beforeSend: funcbefor,
            dataType: "json",
            data:{'id':id},
            contentType: 'application/json',
            mimeType: 'application/json',
            success: funcForAnketOfStudents,
            error: function (data) {
                console.log(data);
            }
        });

    });




    $.ajax({
        url: "http://localhost:8080/hr_system-1.0-SNAPSHOT/getStudents",
        type: "GET",
        dataType: "json",
        contentType: 'application/json',
        mimeType: 'application/json',
        success: funcForStudents,
        error: function (data) {
            console.log(data);
        }
    });




});

$("button#filter").on("click", function () {
    $('.loading').attr('style', 'display: flex');
    $.ajax({
        url: baseUrl + "/getStudents/filter",
        type: "GET",
        data: {'answersJsonString': JSON.stringify($('.candidate-profile form').serializeObject()), 'status': $('#status_select option:selected').text(), 'status2': $('#status_select_2 option:selected').text()},
        dataType: "json",
        success: showStudents,
        error: function (data) {
            setTimeout(function () { $('.loading').hide(); }, 1000);
            new PNotify({
                title: 'Some Problem',
                text: 'There have been some problems!',
                type: 'error'
            });
        }
    });
});

function showStudents(data) {

    dataNewStudents = data;

    $('#StudentTable').empty();
    
    $('#StudentTable').append('<thead>' +
        '<tr>' +
        '<th>Name</th>' +
        '<th>Surname</th>' +
        '<th>Patronymic</th>' +
        '<th>email</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody id="TableStudents">' +
        '</tbody>');

    $('#TableStudents').empty();

    // $('#StudentTable').dataTable.fnClearTable();
    
    for(var index_student in data)
    {
        studentIndex = data[index_student];
        $("#TableStudents").append('<tr class="win getModalStudent" candidate_id="'+ studentIndex.id +'">' +
            '<td>'+studentIndex.user.name+'</td>' +
            '<td>'+studentIndex.user.surname+'</td>' +
            '<td>'+studentIndex.user.patronymic+'</td>' +
            '<td>'+studentIndex.user.email+'</td>' +
            '</tr>');

    }
    
    $("#hider").click(function(){
        $(".ModelViewStudent").css('display','none');
        $('#hider').css('display','none');
    });
    $(".getModalStudent").click(function(){
        if( $(".ModelViewStudent").css('display') == 'none' ){
            $(".ModelViewStudent").css('display','block');
            $('#hider').css('display','block');
        } else{
            $(".ModelViewStudent").css('display','none');
            $('#hider').css('display','none');
        }
    });
}

function funcForStudents (data){
    dataNewStudents = data;
    for(var index_student in data)
    {
        studentIndex = data[index_student];
        $("#TableStudents").append('<tr class="win getModalStudent" candidate_id="'+ studentIndex.id +'">' +
        '<td>'+studentIndex.user.name+'</td>' +
        '<td>'+studentIndex.user.surname+'</td>' +
        '<td>'+studentIndex.user.patronymic+'</td>' +
        '<td>'+studentIndex.user.email+'</td>' +
        '</tr>');

    }

    $('#StudentTable').dataTable({
        "oLanguage": {
            "sSearch": "_INPUT_" //search
        }
    });



    $("#hider").click(function(){
        $(".ModelViewStudent").css('display','none');
        $('#hider').css('display','none');
    });
    $(".getModalStudent").click(function(){
        if( $(".ModelViewStudent").css('display') == 'none' ){
            $(".ModelViewStudent").css('display','block');
            $('#hider').css('display','block');
        } else{
            $(".ModelViewStudent").css('display','none');
            $('#hider').css('display','none');
        }
    });
}

function funcForAnketOfStudents (data){


    $("#hider").css('display','block');
    $(".ModelViewStudent").css('display','block');

    dataInNewAll = data;
    alert("U get Anket");
    $(".ModelViewStudent").empty();


    $(".ModelViewStudent").append('<h2 style="text-align: center!important;">Anketa</h2>');

    for (var index in data) {

        index_first = data[[index]];


        $(".ModelViewStudent").css('text-align','left');

        for(var index_in in index_first)
        {
            index_second = index_first[index_in];



            $(".ModelViewStudent").append('<div class="form-group">'+
                                            '<h5>'+ index_second[2]+ " " +'<br>'+"Answer :" +index_second[1] +' <h5>'+
                                            '<div style="margin-top:20px;"></div>'+
                                          '</div>');

        }


    }

}