let userObject = {
    // init() 함수선언
    init: function () {
        let _this = this;

        $("#btn-save").on("click", () => {
            _this.insertUser();
        });
    },
    insertUser: function () {
        alert("회원 가입 요청!!!");

        let user = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        $.ajax({
           type:"post",
           url:"/auth/register",
           data:JSON.stringify(user),
           contentType: "application/json; charset=utf-8"
        }).done(function(response){
            // console.log(response);
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
                if(errors.username !=null) warn = warn + errors.username + "\n";
                if(errors.email !=null) warn = warn + errors.email + "\n";
                alert(warn);
            }

        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
}

// userObject의 init함수 호출하기
userObject.init();