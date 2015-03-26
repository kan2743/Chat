<%--
  Created by IntelliJ IDEA.
  User: RaysMac
  Date: 2015. 2. 26.
  Time: 오전 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title></title>

  <style>
    body
    {
      margin: 0;
      padding: 0;
    }

    ul{
      padding: 0;
    }

    #wrap
    {
      width : 972px;
      margin : 0 auto;
      border: 1px solid black;
    }

    header
    {
      display: inline;
      float:left;
      width: inherit;
      height: 70px;
    }

    header a
    {
      float: left;
      display: inline-block;
    }

    #banner
    {
      margin : 10px;
      display: inline;
    }

    #login
    {
      float:right;
      margin: 20px 5px 10px 5px ;
      display: inline;

    }

    footer
    {
      display: block;
      clear:both;

      margin:auto;
    }

    article
    {
      align-self: center;
      margin: 0px 5px 0px 5px ;
      float:left;
      display: block;
      width: 80%;
      height: 100%;
    }

    #nvdiv{
      float:left;
      display: block;
      width: 100%;

    }
    .navi
    {
      width: inherit;
      height: auto;
      float: left;
      display: block;
      background: black;
    }

    .navi li
    {
      float:left;
      font-size: medium;
      display: block;

    }

    .navi li a
    {
      float:left;
      text-align: center;
      padding: 10px 20px 10px 20px;
      background-color: #000000;
      color:#ddd;
      text-decoration: none;
      display: block;
    }

    .navi li a:hover
    {
      background-color: #555555;
    }

    .navi li a:active
    {
      font-weight: bold;
      background-color: #999;
      border-top: 1px solid #999;
      border-left: 1px solid #999;
      color: #555 !important;
    }

    aside
    {
      float: left;
      width: 18%;
      height:100%;
      display: inline;
      text-align: center;
    }

    #ifra
    {
      width: 100%;
      height: 100%;
      text-align: center;
      float:left;
    }

    #frame
    {
      clear: both;
      width: 100%;
      height: 600px;
      display: block;
    }

    #commercial{
      padding: 0px 15px 0px 15px ;
      height: 60px;
    }

    #copy{
      float: right;
      text-align: right;
      width:100%;
      background-color: #444444;

    }



  </style>
</head>
<body>

<%
  int pagenum=0;
  // 0 intro
  // 1 notice
  // 2 랜덤 채팅
  // 3 채팅방 관리
  // 4 게시판
  // 5 Q&A

  String att= request.getParameter("menu");
  int menu=0;
  if(att!=null)
  {
    menu=Integer.parseInt(att);
  }


  switch(menu){
    case 0:
      att="/intro.jsp";
      break;
    case 1:
      att="/into.html";
      break;
    case 2:
      att="/into.html";
      break;
    case 3:
      att="/into.html";
      break;
    case 4:
      att="/board_list.jsp";
      break;
    case 5:
      att="/into.html";
      break;
  }
%>

<div id="wrap">
  <header>
    <a href="/main.jsp"><img id="banner" src="/img/배너용.png" width="288" height="34"></a>
    </form>

  </header>
  <aside>
    <iframe src="/login.jsp" frameborder="0"style="height:auto; width:100%" ></iframe>
    <script>document.write("<IFR"+"AME src=http://ad2share.com/banner5.php?member=kan2743 frameBorder=0 width=151 height=30 display=block scrolling=no></IFR"+"AME>");</script>
    <iframe src="/room/chat" style="height:500px; width:100%" ></iframe>

  </aside>




  <article>
    <div id="nvdiv">
      <ul class="navi">
        <li><a href="/main.jsp?menu=0">사용방법</a></li>
        <li><a href="#">공지사항</a></li>
        <li><a href="#">랜덤채팅</a></li>
        <li><a href="#">채팅방 관리</a></li>
        <li><a href="/main.jsp?menu=4">게시판</a></li>
        <li><a href="#">Q&A</a></li>
      </ul>
    </div>
    <div id="frame">
      <iframe id="ifra" src=<%=att%> frameborder="0"></iframe>

    </div>
  </article>

  <footer>
    <div id="commercial">
      <script>
        document.write("<IFR"+"AME class=comer src=http://ad2share.com/banner2.php?member=kan2743 margin: 0 auto frameBorder=0 width=470 height=60 ></IFR"+"AME>");
        document.write("<IFR"+"AME class=comer src=http://ad2share.com/banner2.php?member=kan2743 margin: 0 auto frameBorder=0 width=470 height=60 ></IFR"+"AME>");
      </script>
    </div>
    <div id="copy">Copyright © 2015 ChatRoom</div>
    <br/>
  </footer>

</div>
</body>
</html>