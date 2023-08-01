let replyObject = {
    init: function () {
        $("#btn-save-reply").on("click", () => {
            this.insertReply();
        });
    },
    insertReply: function () {
        alert("댓글 등록 요청!!!");

        let id = $("#postId").val();

        let reply = {
            content: $("#reply-content").val()
        }

        $.ajax({
           type:"post",
           url:"/reply/"+id,
           data:JSON.stringify(reply),
           contentType: "application/json; charset=utf-8"
        }).done(function(response){
            let msg = response["data"];
            alert(msg);
            location = "/post/"+id;
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

    // 댓글 삭제 후 다시 상세화면으로 이동
    deleteReply: function (postId, replyId) {
        alert("댓글 삭제 요청!!");

        $.ajax({
            type: "DELETE",
            url: "/reply/" + replyId
        }).done(function (response) {
            location = "/post/"+postId;
        });
    }
}

replyObject.init();