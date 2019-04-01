$(document).ready(function () {

    // Do GET all Customers from Back-End with JQUERY AJAX
    $(function () {
        $.ajax({
            type: "GET",
            url: "/regAddElements/all",
            success: function (response) {
                // console.log(response);
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    });

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });


    $('#elementsOne').change(function (e) {
        // e.preventDefault();
        let name = $(this).val();
        // console.log(name);
        let url_page = "/regAddElements/" + name;
        let labels = document.getElementById("labelOne");
        // labels.innerText = "Виберіть список класних керівників";
        let elementsTwo = document.getElementById('elementsTwo');
        if (name === "classteachers") {
            // let elementsTwo = document.getElementById('elementsTwo');
            console.log(elementsTwo);
            elementsTwo.innerHTML = '<option>------</option>' +
                '<option  value="subjects">Предмети</option>' +
                '<option  value="classes">Класи</option>' +
                '<option  value="students">Студенти</option>';

        } else if (name === "parents") {
            elementsTwo.innerHTML = '<option>------</option>' +
                '<option  value="students">Студенти</option>';
        } else if (name === "deputy") {
            elementsTwo.innerHTML = '<option>------</option>' +
                '<option value="subjects">Предмети</option>';
        } else if (name === "students") {
            // let elementsTwo = document.getElementById('elementsTwo');
            console.log(elementsTwo);
            elementsTwo.innerHTML = '<option>------</option>' +
                '<option value="parents">Батьки</option>' +
                '<option value="classes">Класи</option>' +
                '<option value="classteachers">Класний керівник</option>';
        } else if (name === "subjects") {
            console.log(elementsTwo);
            elementsTwo.innerHTML = '<option>------</option>' +
                // '<option value="teachers">Викладачі</option>' +
                '<option value="classes">Класи</option>';
                // '<option value="deputy">Зауч</option>';
        } else if (name === "classes") {
            elementsTwo.innerHTML = '<option>------</option>' +
                '<option value="subjects">Предмети</option>' +
                '<option value="students">Студенти</option>';
                // '<option value="classteachers">Класний керівник</option>';
        } else if (name === "teachers") {
            elementsTwo.innerHTML = '<option>------</option>' +
                '<option value="subjects">Предмети</option>';
        } else {
            elementsTwo.innerHTML = '<option>-------</option>';
            $('#elementsTwo').change();
        }



        $.ajax({
            type: "GET",
            url: url_page,
            data: {'name': name},
            success: function (data) {
                let labels = document.getElementById("label");
                // console.log(data);
                $(labels).innerText = "Виберіть список класних керівників";
                let elements = document.getElementById('listElementsOne');
                $(elements).empty();
                for (let i = 0; i < data.length; i++) {
                    if(name==="classes" || name==="subjects") {
                        $(elements).append('<option value = ' + data[i].id + '>' + data[i].name + '</option>');
                    }else{
                        $(elements).append('<option value = ' + data[i].id + '>' + data[i].loginname + '</option>');
                    }                }


            },
            error: function (e) {

            }
        });

    });


    $('#elementsTwo').change(function (e) {
        // e.preventDefault();
        let name = $(this).val();
        console.log(name);
        let url_page = "/regAddElements/" + name;
        let elementsOne = document.getElementById("elementsOne");
        let elementsTwo = document.getElementById("elementsTwo");
        let listElementsTwo = document.getElementById("listElementsTwo");
        let nameelementsOne =  elementsOne.value;
        console.log(nameelementsOne);
        // labels.innerText = "Виберіть список класних керівників";
        if(nameelementsOne ==="classteachers" && name==="classes") {
            listElementsTwo.outerHTML='<select id="listElementsTwo">' +
                '</select>';
        }else{
            listElementsTwo.outerHTML='<select id="listElementsTwo" multiple="multiple">' +
                '</select>';
        }

        $.ajax({
            type: "GET",
            url: url_page,
            data: {'name': name},
            success: function (data) {
                let labels = document.getElementById("label");
                // console.log($(this).val());
                $(labels).innerText = "Виберіть список класних керівників";
                let elements = document.getElementById('listElementsTwo');
                $(elements).empty();
                for (let i = 0; i < data.length; i++) {
                    if(name==="classes" || name==="subjects") {
                        $(elements).append('<option value = ' + data[i].id + '>' + data[i].name + '</option>');
                    }else{
                        $(elements).append('<option value = ' + data[i].id + '>' + data[i].loginname + '</option>');
                    }
                }


            },
            error: function (e) {

            }
        });

    });




    $("#btnSaveElements").click(function () {
        let elementsOne = $("select[id='elementsOne']").val();
        let elementsTwo = $("select[id='elementsTwo']").val();
        // let listElementsOne = $("select[id='listElementsOne']").val();
        // let listElementsTwo = $("select[id='listElementsTwo']").val();

        // let elementsOne = document.getElementById("elementsOne");
        let listElementsOne = document.getElementById("listElementsOne");
        // let elementsTwo = document.getElementById("elementsTwo");
        let listElementsTwo = document.getElementById("listElementsTwo");

        console.log(listElementsOne);

        let stringlistElementsOne = [];
        let stringlistElementsTwo = [];


        for (let i = 0; i < listElementsOne.options.length; i++) {
            if (listElementsOne.options[i].selected === true) {
                stringlistElementsOne[i] = listElementsOne.options[i].value;
            }
        }



        for (let i = 0; i < listElementsTwo.options.length; i++) {
            if (listElementsTwo.options[i].selected === true) {
                stringlistElementsTwo[i] = listElementsTwo.options[i].value;
            }
        }
//вибір елементів
        let arrElements = [
            {"elementsOne": elementsOne},
            {"elementsTwo": elementsTwo},
            {"stringelementsOne": stringlistElementsOne},
            {"stringelementsTwo": stringlistElementsTwo}
        ];

        console.log(arrElements);

        $.ajax({
            type: "POST",
            url: "/saveElements",
            data: {elementsOne, elementsTwo, stringlistElementsOne, stringlistElementsTwo},
            success: function (data) {
                console.log(data);
            },
            error: function (e) {

                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }

        });


    })

    // // Do DELETE a Customer via JQUERY AJAX
    // $(document).on("click","a",function() {
    //
    // 	var customerId = $(this).parent().find('input').val();
    // 	var workingObject = $(this);
    // 	console.dir(workingObject);
    //
    // 	$.ajax({
    // 		type : "DELETE",
    // 		url : window.location + "api/customer/delete/" + customerId,
    // 		success: function(resultMsg){
    // 			$("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
    // 										"Customer with Id=" + customerId + " is deleted successfully!"  +
    // 									"</p>");
    //
    // 			workingObject.closest("tr").remove();
    //
    // 			// re-css for table
    // 			$( "#customerTable tbody tr:odd" ).addClass("info");
    // 			$( "#customerTable tbody tr:even" ).addClass("success");
    // 		},
    // 		error : function(e) {
    // 			alert("ERROR: ", e);
    // 			console.log("ERROR: ", e);
    // 		}
    // 	});
    // });
});