let index = {
    init: function (){
        $("#btn-save").on("click",()=> {
            this.save();
        });
    },

    save:function(){
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
}

index.init();