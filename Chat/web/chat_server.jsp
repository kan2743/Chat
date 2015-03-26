<%--
  Created by IntelliJ IDEA.
  User: RaysMac
  Date: 2015. 2. 17.
  Time: 오전 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="chat.dataServlet" %>
<%@ page import="chat.DB" %>
<%@ page import="chat.chat" %>
<%@ page import="chat.userid" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<%
  for(chat cha: DB.db){
%>
<h1><%=cha.roomname%></h1>
<%
  for(userid user: cha.connectuser){
%>
<h1>\t<%=user.id%></h1>

<%
    }
  }
%>


<form method="post" action="/dataServlet/clear">


  <select name="roomname">

    <option value="">삭제할 채팅방 선택</option>
    <%
      for(chat chat: DB.db){
    %>
    <option value="<%=chat.roomname%>"><%=chat.roomname%></option>
    <%=chat.chatlog%>
    <%
      }
    %>
  </select>

  <input type="radio" name="type" value="drop">채팅방 삭제
  <input type="radio" name="type" value="delete">내용만 삭제

  <input type="submit" value="채팅창 초기화">

</form>


</body>
</html>
