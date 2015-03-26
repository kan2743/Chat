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
  for(String str: cha.chatlog){
%>
<h1>\t<%=str%></h1>

<%
    }
  }
%>




<select name="roomname">

  <option value="">삭제할 채팅방 선택</option>
  <%
    for(chat chat: DB.db){
  %>
  <option value="<%=chat.roomname%>"><%=chat.roomname%></option>
  <%
    }
  %>
</select>

<form method="post" action="/dataServlet/clear">
  <input type="submit" value="채팅창 초기화">
</form>


</body>
</html>
