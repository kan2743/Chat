
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML public "-//w3c//dtd html 4.0 transitional//en"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>AJAX Chat Client</title>

  <script type="text/javascript" src="../chat.js"></script>

  <style type="text/css">


    #view{
      height:360px;
    }
    #chat_msg {
      width:70%; height:99%; overflow: auto;
      background-color: #eeeeee; text-align: left;
      font-size: 9pt;
      margin-bottom: 10px;
      float:left;

    }

    #chat_users {
      width:29%; height:99%; overflow: auto;
      background-color: #eeeedd; text-align: left;
      font-size: 9pt;
      margin-bottom: 10px;
      float:left;
    }

    .chat{

      display: inline;

    }

    #send_msg {
      float: left;
      width: 78%;
    }

    #userid{
      width:20%;
      float:left;

    }

    #userid:focus
    {
      width:30%;
      float:left;

    }
    #userid:focus + #send_msg
    {
      float:left;
      width:68%;

    }

    #submit{
      width:10%;
    }

    form{
      width: 100%;
    }


    div.outer {
      margin-top: 0;
      background-color:#dddddd;
      border: 1px solid black;
      width:100%; height:450px;
      text-align: center;
      margin-bottom: 5px;
    }
    input.chatbox {
      width:100%; }

  </style>




</head>
<body onLoad="onLoadHandler();" >
<div class="outer">
  <div id="chat_msg" ></div>
  <div id="chat_users"></div>

  <form name="form1" style="float:left;"  >
    <input type="text" name="userid" id="userid"  onchange="idchange()"/>
    <input class="chatbox" type="text"  id="send_msg" name="send_msg" onKeyDown="keyDownHandler(event);"/>
  </form>
</div>

</body>
</html>