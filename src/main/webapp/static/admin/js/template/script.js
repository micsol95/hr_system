/**
 * Created by Владимир on 22.05.2016.
 */
var templates;
var templateIndex;
$(document).ready(function () {
    init();
    var validateForm = $(".validate").validate({
        rules: {
            description: "required",
            template: "required"
        }
    });
    $(".btn-update").on("click", function () {
        var textTemplate = $(".email_text");
        textTemplate.empty();
        textTemplate.append('<h4>Template Text</h4> <textarea class = "form-control col-md-7 col-xs-12 template" name ="template" row="3">' + templates[templateIndex].template + '</textarea>');
        autosize($('textarea'));
        var textSubject = $(".email_subject");
        textSubject.empty();
        textSubject.append('<h4>Subject</h4> <input type="text" class = "form-control col-md-7 col-xs-12 description" name = "description" value="' + templates[templateIndex].description + '"></p>');
        $(".btn-update").css({'display': 'none'});
        $(".btn-save").css({'display': 'inline-block'});
        $(".btn-cancel").css({'display': 'inline-block'});
    });
    $(".btn-save").on("click", function () {
        if (validateForm.form()) {
            $(".btn-update").css({'display': 'inline-block'});
            $(".btn-save").css({'display': 'none'});
            $(".btn-cancel").css({'display': 'none'});
            templates[templateIndex].description = $(".description").val();
            templates[templateIndex].template = $(".template").val();
            ajax("service/updateEmailTemplate", function () {
            }, function () {
            }, templates[templateIndex]);
            showModal();
            generateEmailTemplateList(templates);
        }
    });
    $(".btn-cancel").on("click", function () {
        $(".btn-update").css({'display': 'inline-block'});
        $(".btn-save").css({'display': 'none'});
        $(".btn-cancel").css({'display': 'none'});
        showModal();
    });
});

function init() {
    ajax("service/getEmailTemplates", function (data) {
        templates = data;
        generateEmailTemplateList(templates);
    });
}


function generateEmailTemplateList(data) {
    var template = $(".template_form");
    template.empty();
    for (var index in data) {
        template.append('<div class="col-md-6 col-sm-4 col-xs-12 animated fadeInDown">' +
        '<button type="button" class="btn btn-dark btn-lg show_button" style = "white-space: normal;" ' +
        'data-toggle = "modal" data-target = ".bs-example-modal-lg" ' +
        'value = "' + index + '">' +
        data[index].description + '</button> </div>');
    }
    $(".show_button").on("click", function () {
        templateIndex = $(this).val();
        showModal();
    });
}

function showModal() {
    var textEmailTemplate = $(".email_text");
    textEmailTemplate.empty();
    textEmailTemplate.append('<h4> Template Text: ' + templates[templateIndex].template + '</h4>');
    var textSubject = $(".email_subject");
    textSubject.empty();
    textSubject.append('<h4> Subject: ' + templates[templateIndex].description + '</h4>');
    $(".btn-cancel").css({'display': 'none'});
    $(".btn-save").css({'display': 'none'});
    $(".btn-update").css({'display': 'inline-block'});
}

function ajax(url, success, error, data) {
    $.ajax({
/*<<<<<<< HEAD
        url: "http://31.131.25.206:8080/hr_system-1.0-SNAPSHOT/admin/" + url,
=======*/
        url: baseUrl+"/admin/" + url,
        type: "GET",
        dataType: "json",
        contentType: 'application/json',
        mimeType: 'application/json',
        data: data,
        success: success,
        error: error
    });
}