
$(function(){
    var client_id = "db8fe6f7c371877b821d";
    var client_secret="813e80e48c8a086088e7215ef3c974fab6afc088";
    var callback_url = "http://127.0.0.1:8080/dyglxt/user/GitLogin.action";

    $("#gitLoginId").click(function(){
        console.log("ddd");
        gitHubLogin(client_id,callback_url);
    });



});
//gitHUb快速登录
function gitHubLogin(client_id,callback_url){
//https://github.com/login/oauth/authorize?client_id=xxxxx&state=xxx&redirect_uri=xxxx;
    var url = "https://github.com/login/oauth/authorize";
    url +="?client_id="+client_id;
    url +="&state=200";
    url +="&redirect_uri="+callback_url;
    window.location.href=url;
}
