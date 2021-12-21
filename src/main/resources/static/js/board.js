let index = {
    init: function (){
        $("#btn-save").on("click",()=> {
            this.save();
        });

        $("#btn-delete").on("click",()=> {
            this.deleteById();
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
        var id = $("#id").text();

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
    }
}

index.init();