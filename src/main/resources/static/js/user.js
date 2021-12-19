let index = {
    init: function (){
        $("#btn-save").on("click",()=> {
            this.save();
        });
        $("#btn-login").on("click",()=> {
            this.login();
        });
    },

    save:function(){
        let data = {
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

        $.ajax({
            type:"POST",
            url:"/blog/api/user",
            data: JSON.stringify(data), //http boidy 데이터
            contentType:"application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json) 이라면=> javascript object로 변경시켜준다.
        }).done(function(res) {
            location.href="/blog";
        }).fail(function(err){
            alert(JSON.stringify(err));
        }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청.
    },


    login:function(){
        let data = {
            username:$("#username").val(),
            password:$("#password").val(),
        };

        $.ajax({
            type:"POST",
            url:"/blog/api/user/login",
            data: JSON.stringify(data), //http boidy 데이터
            contentType:"application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json) 이라면=> javascript object로 변경시켜준다.
        }).done(function(res) {
            location.href="/blog";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    }
}

index.init();