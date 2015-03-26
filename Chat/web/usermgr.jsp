<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import ="java.util.*"%>
<%@ page import ="chat.User"%>
<%@ page import="chat.DB_user" %>

<%
  request.setCharacterEncoding("UTF-8");
%>

<%
  Object ses  = session.getAttribute("s_id");
  String sessionUserId=null;
  boolean login=false;
  if(ses!=null){
    sessionUserId=ses.toString();
    login=true;
  }
%>
<html>
<head>
  <title><%=login?"회원수정":"회원가입"%></title>
</head>
<body>

<%


  User user=null;
  if(login)
    user = DB_user.getuser(sessionUserId);
%>
<H4><%=login?"수정할 부분 수정해 주세요":"회원가입해!"%></H4>
<form action="/user/usermgr" method="post">

  <%
    if(login)
    {
  %>
  아이디:<input type="text" name = userid size=15 value = "<%=user.userid%>" readonly/><br>
  <%
  }
  else
  {
  %>
  아이디:<input type="text" name = userid size=15 value = ><br>
  <%
    }
  %>

  암호:<input type="text" name = pwd size=15 value = "<%=login?user.pwd:""%>"><br>

  이메일:<input type="text" name = email size=15 value = "<%=login?user.email:""%>"><br>

  <input type="submit" value=<%=login?"수정":"가입"%>>
</form>
</body>
</html>