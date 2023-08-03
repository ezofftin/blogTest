let postObject = {
    // init() 함수선언
    init: function () {
        $("#btn-save").on("click", () => {
            this.insertPost();
        });
        $("#btn-delete").on("click", () => {
            this.deletePost();
        });
        $("#btn-update").on("click", () => {
            this.updatePost();
        });
    },
    insertPost: function () {
        alert("포스트 등록 요청!!!");

        let post = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
           type:"post",
           url:"/post",
           data:JSON.stringify(post),
           contentType: "application/json; charset=utf-8"
        }).done(function(response){
            // let msg = response["data"];
            // alert(msg);
            // location = "/";

            // ########## validation 후
            let status = response["status"]; // 응답 상태코드
            if (status == 200) {
                let message = response["data"];
                alert(message);
                location = "/";
            }else{
                let warn = "";
                let errors = response["data"]; // 리턴된 Map데이터
                if(errors.title !=null) warn = warn + errors.title + "\n";
                if(errors.content !=null) warn = warn + errors.content + "\n";
                alert(warn);
            }

        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

    deletePost: function () {
        alert("포스트 삭제 요청!!!");
        let id = $("#id").text();

        $.ajax({
            type:"DELETE",
            url:"/post/"+ id,
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            location = "/";
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

    updatePost: function () {
        alert("포스트 수정 요청!!!");
        let post = {
            id: $("#id").val(),
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type:"PUT",
            url:"/post/",
            data: JSON.stringify(post),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            location = "/";
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    }
}

// userObject의 init함수 호출하기
postObject.init();