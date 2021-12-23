let index = {
    init: function (){
        $("#btn-save").on("click",()=> {
            this.save();
        });

        $("#btn-delete").on("click",()=> {
            this.deleteById();
        });

        $("#btn-update").on("click",()=> {
            this.update();
        });

        $("#btn-reply-save").on("click",()=> {
            this.replySave();
        });
    },

    save: function(){
        let data = {
            title:$("#title").val(),
            content:$("#content").val(),
        };

        $.ajax({
            type:"POST",
            url:"/api/board",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(res) {
            location.href="/";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },

    deleteById: function(){
        let id = $("#id").text();

        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(res) {
            alert("삭제완료")
            location.href="/";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },

    update: function(){
        let id = $("#id").val();

        let data = {
            title:$("#title").val(),
            content:$("#content").val(),
        };


        $.ajax({
            type:"PUT",
            url:"/api/board/"+id,
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(res) {
            alert("글 수정 완료.")
            location.href="/";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },

    replySave: function(){
        let data = {
            content:$("#reply-content").val()
        };
        let boardId = $("#boardId").val();
        $.ajax({
            type:"POST",
            url:`/api/board/${boardId}/reply`,
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(res) {
            console.log(boardId);
            location.href=`/board/${boardId}`;
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },
}

index.init();