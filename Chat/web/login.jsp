<%--
  Created by IntelliJ IDEA.
  User: RaysMac
  Date: 2015. 3. 24.
  Time: 오후 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <style>
    .list_user {
      overflow: hidden;
      width: 258px;
      padding: 8px 0 0 22px;
      font-size: 11px;
      line-height: 14px;
      letter-spacing: -1px;
    }

    .screen_out {
      overflow: hidden;
      position: absolute;
      width: 0;
      height: 0;
      line-height: 0;
      text-indent: -9999px;
    }
  </style>

</head>
<body>
<div id="loginWrap">
  <%
    Object ses  = session.getAttribute("s_id");
    String sessionUserId=null;
    boolean login=false;
    if(ses!=null)
    {
      sessionUserId=ses.toString();
      login=true;
    }

    if(!login)
    {
  %>
  <form id="loginForm" name="loginform" accept-charset="utf-8" method="post" action="/user/login" autocomplete="off">
    <fieldset class="#logoff">
      <legend class="screen_out">로그인 및 관련 설정</legend>
      <div class="login_inp">
        <label id="idLabel" class="lab_login lab_id" for="id">아이디 <span class="txt_labid">or</span> 이메일</label>
        <input type="text" id="id" name="id" class="tf_login" value="" maxlength="50" tabindex="2">
        <label id="pwdLabel" class="lab_login lab_pw" for="inputPwd">비밀번호</label>
        <input type="password" id="inputPwd" name="pw" class="tf_login tf_pw" value="" maxlength="32" tabindex="3">
      </div>
      <div class="login_set check_security">
        <input type="checkbox" id="stln" name="stln" class="inp_steady" tabindex="5">
        <label id="lbStln" class="img_vert lab_steady" for="stln">로그인 유지</label>
        <div id="lbStlnNoti" class="bg_daum layer_steady hide">
        </div>
      </div>
      <button type="submit" id="loginSubmit" class="btn_daum btn_login #loginbtn" tabindex="4"><span class="ir_wa">로그인</span></button>
      <a href="/usermgr.jsp" class="link_join #registration" tabindex="8"><strong>회원가입</strong></a>
    </fieldset>
  </form>
  <%
  }
  else
  {
  %>
  <a href="/user/logout"><input type="button" value="로그아웃"/></a>
  <a href="/usermgr.jsp"><input type="button" value="정보수정"/></a>
  <%
    }
  %>
</div>




</body>
</html>
