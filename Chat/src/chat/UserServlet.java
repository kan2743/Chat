package chat;

import chat.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Administrator on 2015-02-25.
 */
@WebServlet(name = "UserServlet")


public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //요청이 들어온 URI에 따라 loginServlet,logout.register,modify 함수를 호출
        String uri = request.getRequestURI();

        System.out.println(uri);
        try {
            if(uri.equals("/user/login"))
                login(request, response);
            else if (uri.equals("/user/logout")) {
                System.out.println("로그아웃");
                logout(request, response);
            }
            else if (uri.equals("/user/usermgr"))
                usermgr(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/login.jsp");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request, response);
        response.sendRedirect("/login.jsp");
    }
    //loginServlet 을 처리하는 함수
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        // id 또는 pw 값이 넘어오지 않았다면 SC_BAD_REQUEST 오류를 전송 후 리턴
        // TODO: autoLogin 값도 넘어오지 않았다면 SC_BAD_REQUEST 오류를 전송 후 리턴
        if (id == null || pw == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        // user 클래스를 꺼내오지 못 했다면 아이디가 존재하지 않는다고 오류 메시지를 보낸 후 return
        if (id == null) {
            response.getWriter().println("아이디가 존재하지 않습니다.");
            return;
        }
        User user =  DB_user.getuser(id);
        // 꺼내온 user 클래스의 암호와 pw가 일치하지 않는다면 비밀번호가 틀리다고 오류 메시지를 보낸 후 return
        if (!user.pwd.equals(pw)) {
            response.getWriter().println("비밀번호가 틀립니다.");
            return;
        }
        request.getSession().setAttribute("s_id",id);

    }


    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //세션을 없애고 "/index.jsp" 페이지로 이동시킴
        request.getSession().invalidate();

    }



    protected void usermgr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Object ses  = request.getSession().getAttribute("s_id");
        String sessionUserId=null;
        boolean login=false;
        if(ses!=null)
        {
            sessionUserId=ses.toString();
            login=true;
        }
        String userid = request.getParameter("userid");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        String room = request.getParameter("room");

        User user = new User(userid,pwd,email,room);
        User suser;
        if(!login)
            if((suser= DB_user.getuser(userid))==null)
            {
                System.out.print("회원가입");
                DB_user.insert(user);
                return;
            }
        DB_user.update(user);
        request.getSession().setAttribute("s_id",userid);
        return;
    }
}
