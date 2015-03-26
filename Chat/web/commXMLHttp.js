/**
 * Created by RaysMac on 2015. 2. 17..
 */

var timerId = 0;
nowlog=1;
var xmlHttp = new XMLHttpRequest();
var gid;
var idch=false;
var url="";


function state(stat){

    if(stat==0)
    { //메세지 로그
        var sendData="state="+encodeURIComponent(0)+"&log="+encodeURIComponent(nowlog);
        setTimeout("state(0)",500);
    }

    else if (stat==1)
    { //메세지 전송
        var msg = form1.send_msg.value;
        var sendData="state="+encodeURIComponent(1)+"&log="+encodeURIComponent(nowlog)+"&msg="+encodeURIComponent(msg);
        document.form1.send_msg.value = "";
    }

    else if (stat==2)
    { //메세지 전송 & 아이디 변경
        var msg = form1.send_msg.value;
        var sendData="state="+encodeURIComponent(2)+"&log="+encodeURIComponent(nowlog)+"&msg="+encodeURIComponent(msg)+"&id="+encodeURIComponent(id);
        document.form1.send_msg.value = "";
        idch=false;
    }

    else if (stat==8)
    { // 접속
        var sendData="state="+encodeURIComponent(8)+"&id="+encodeURIComponent(id);
        setTimeout("state(0)",2500);
    }

    else if (stat==9)
    { // 접속종료
        var sendData="state="+encodeURIComponent(9)+"&id="+encodeURIComponent(gid);
    }

    openSendStatus("POST","../../dataServlet"+url,true,sendData);


}

function openSendStatus(getPost, urlFile, trueFalse, sendData) {
    xmlHttp.open(getPost, urlFile, trueFalse, sendData);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {

            alert("get");
            if (xmlHttp.status == 200)
                mainControl(xmlHttp);
        }

        var conType = "application/x-www-form-urlencoded; charset=UTF-8;"
        xmlHttp.setRequestHeader("Content-Type", conType);
        xmlHttp.send(sendData);
        alert("send");

    }
}




function mainControl(xmlHttp) {
    var ReTxt = decodeURIComponent(xmlHttp.responseText);
    var chat_msg = document.getElementById("chat_msg");
    var chat_users = document.getElementById("chat_users");
    var strArray = ReTxt.split("--");

    //chat_msg.innerHTML +=strArray[0];
    creatediv(strArray[0]);
    chat_users.innerHTML = strArray[1];
    chat_msg.scrollTop = chat_msg.scrollHeight;
    nowlog=strArray[2].substring(0,strArray[2].length-1);
}



function idchange(){
    id=form1.userid.value;
    if(id.length>20) {
        alert("아이디는 20자 이하만 가능합니다.");
        form1.userid.value="";
        form1.userid.focus();
        return;
    }
    idch=true;
}

function send(){
    var msg = form1.send_msg.value;
    if(msg==''){
        alert("입력한 메시지가 없습니다");
        form1.send_msg.focus();
        return;
    }
    idch?state(2):state(1);
}


/* 메시지입력후 엔터키를 친 경우 폼의 전송을 막고 send()호출 */
function keyDownHandler(e){
    if(window.event.keyCode==13) {
        if(window.event) event.returnValue = false;
        else e.preventDefault();
        send();
    }
    if(window.event.keyCode==9) {
        if(window.event) event.returnValue = false;
        form1.send_msg.value+="/r";
    }

}

/* 프로그램 시작시 2초후에 getChatText()호출하여 서버상의 최근 메시지 ID를 가져옴*/
function onLoadHandler(){
    userid=randNum();
    loadid();
    form1.send_msg.focus();
    id=form1.userid.value;
    //   getChatText();
    url =  document.location.pathname;
    state(8);
    alert(url);
}


function randNum(){
    var ALPHA = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'];
    var rN='';
    for(var i=0; i<4; i++){
        var randTnum = Math.floor(Math.random()*ALPHA.length);
        rN += ALPHA[randTnum];
    }
    return rN;
}

function loadid(){
    document.forms["form1"].userid.value=userid;
}


function creatediv(value){
    var parentDiv = document.getElementById("chat_msg");
    var addDiv = document.createElement("div");
    addDiv.className = "chat";
    addDiv.innerHTML=value;
    parentDiv.appendChild(addDiv); // 부모에 종속시킴
}

window.addEventListener("beforeunload", function (e) {
    state(9);
});