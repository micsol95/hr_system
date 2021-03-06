function drawQuestionForm(questionsList) {
    var questionType;
    var questionInput;
    var questionsAnswers;
    console.log("questions build");
    for (var index in questionsList) {
        switch (questionsList[index].type) {
            case "Text":
                questionType = "text";
                break
            case "Number":
                questionType = "int";
                break
            case "Select":
                questionType = "select";
                break
            case "Checkboxes":
                questionType = "check";
                break
            case "Select or text":
                questionType = "textANDselect";
                break

        }
        questionInput = $(".hidden ." + questionType + "-input-type").clone();
        questionInput.attr('q-type', questionType + "-input-type");
        questionInput.find("label.caption").html(questionsList[index].caption);
        questionInput.find("label.caption").attr("for", "question-" + questionsList[index].id);
        questionInput.find("input").attr("name", "question-" + questionsList[index].id);
        questionInput.find("select").attr("name", "question-" + questionsList[index].id);
        questionInput.find("input").attr("id", "question-" + questionsList[index].id);

        questionsAnswers = questionsList[index].answerVariants;

        if ((questionType == "select") || (questionType == "textANDselect")) {
            questionInput.find("select").append("<option value=''>" + "</option>");
            for (var indexAnswer in questionsAnswers) {
                questionInput.find("select").append("<option value='" + questionsAnswers[indexAnswer] + "'>"
                    + questionsAnswers[indexAnswer] + "</option>");
            }
        }
        if (questionType == "check") {
            for (var indexAnswer in questionsAnswers) {

                questionInput.find(".checkbox .column-" + (indexAnswer % 3 + 1)).append(
                    "<label class='' for='question-" + questionsList[index].id + "-" + indexAnswer + "'>" +
                    "<input id='question-" + questionsList[index].id + "-" + indexAnswer +
                    "' name='question-" + questionsList[index].id +
                    "' value = '" + questionsAnswers[indexAnswer] + "' type=\"checkbox\" class=\"flat\" >" +
                    questionsAnswers[indexAnswer] + "</label></br>");
            }
        }
        $(".candidate-profile form .questions").append(questionInput);
    }
    setTimeout(function () {
        $('.loading').css('display', 'none');
        $('.loading .questions_text').hide();
        $('.loading .answers_text').removeClass('hidden');
    }, 1000);
}