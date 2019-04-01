$(document).ready(function () {




    $("#btnSelectElements").click(function (e) {

        setTimeout(function () {
            let getClasses = document.getElementById("classes_id");
            let oneClasses = $("select[name='classes']").val();
            console.log(getClasses);
            console.log(oneClasses);

            // $("select[id='elementsOne']").val();
            for (let i = 0; i < getClasses.options.length; i++) {
                if (getClasses.options[i].value === oneClasses) {
                    oneClasses[i] = getClasses.options[i].selected = true;
                }
            }

            console.log(oneClasses);

        }, 1000);


        // e.preventDefault();
       // let getClasses = $("select[name='classes']");
      //   let getClasses = document.getElementById("classes_id");
      // let oneClasses = $("select[name='classes']").val();
      //   console.log(getClasses);
      //   console.log(oneClasses);
      //
      //   // $("select[id='elementsOne']").val();
      //   for (let i = 0; i < getClasses.options.length; i++) {
      //       if (getClasses.options[i].value === oneClasses) {
      //           oneClasses[i] = getClasses.options[i].selected = true;
      //       }
      //   }
      //
      //     console.log(oneClasses);


    })

    
    
});