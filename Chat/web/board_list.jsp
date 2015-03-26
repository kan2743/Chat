
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="static java.sql.DriverManager.*" %>
<%@ page import="chat.DB" %>


<%--
  Created by IntelliJ IDEA.
  User: RaysMac
  Date: 2015. 3. 19.
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <style>
    #boardwrap{
      float:left;
      display: inline-block;
    }
    #write{
      float:right;
    }

  </style>


  <title></title>
</head>
<body>
<%
  int pagenum=0;
  String att= request.getParameter("page");
  if(att!=null)
  {
    pagenum=Integer.parseInt(att);
    System.out.println("asd"+pagenum);
  }


  Class.forName("com.mysql.jdbc.Driver");
  java.sql.Connection con = getConnection("jdbc:mysql://localhost:3306/chat", "root", "root");
  Statement st= con.createStatement();
  ResultSet rs;


  rs=st.executeQuery("SELECT COUNT(*) FROM board");
  rs.first();
  int count=rs.getInt(1);

  StringBuffer sql=new StringBuffer("  select seq,title,writer,ts,acces from board limit ");
  sql.append(pagenum*10);
  sql.append(",10");
  //sql.append();
  System.out.println(sql);
  rs=st.executeQuery(sql.toString());


%>
<div id="boardwrap" >


  <table border="1">
    <thead>
    <tr>
      <td>글번호</td>
      <td>제목</td>
      <td>작성자</td>
      <td>작성일</td>
      <td>조회수</td>
    </tr>
    </thead>

    <%
      if(rs.getRow()==1)
      {
    %>
    <tr>
      <td colspan="5">게시물이 존재하지 않습니다
    </tr>
    <%
      }
      while(rs.next())
      {
    %>
    <tr>
      <td><%=rs.getInt("seq")%></td>
      <td><%=rs.getString("title")%></td>
      <td><%=rs.getString("writer")%></td>
      <td><%=rs.getDate("ts")%></td>
      <td><%=rs.getInt("acces")%></td>
    </tr>
    <%
      }
    %>
  </table>
  <br/>
  <br/>
  <form action="#" method="post">
    <%
      for(int i=0; i<(count-1)/10+1; i++)
      {
    %>
    <a href="/board_list.jsp?page=<%=i%>"> <%=i+1%> </a>
    <%
      }
    %>
    <a href="board_write.html" id="write"><input type="button" value="글쓰기"/></a>
  </form>
</div>
</body>
</html>
