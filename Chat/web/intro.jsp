<%--
  Created by IntelliJ IDEA.
  User: RaysMac
  Date: 2015. 3. 23.
  Time: 오후 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>ChatRoom 서비스</title>
</head>

<script>
  function createR(){
    var room="/room/";
    var name = document.getElementById("room").value;

    window.open(room+name);
  }

</script>

<body>
<h1>ChatRoom 서비스 사용법 안내</h1>
<br/>
<fieldset>
  <legend>안내</legend>
  <br/>
  ChatRoom　서비스는 별도의 프로그램을 필요로 하지 않는<br/>
  웹 어플리케이션 형태의 채팅 서비스로서
  <br/><br/>
  사이트주소/room/채팅방 이름
  <br/><br/>
  으로 간단히 채팅방을 개설. 자유로이 채팅방을 이용 가능합니다.<br/>
  또한 ChatRoom 서비스는 태그를 허용하고 있어<br/>
  각종 이미지, 미디어, 동영상을 태그로 불여넣을 수 있습니다.<br/>
  <br/>
  현재 채팅방 이름은 영문으로 시작하는 영문 숫자 조합으로만 가능합니다.
  <br/>
  <br/>
  <form action="#" name="frmroom">
    채팅방 이름 : <input type="text" id="room"/>

    <input type="button" value="채팅방 생성" onclick="createR()"/>
  </form>


</fieldset>




</body>
</html>
