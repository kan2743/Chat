package chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by RaysMac on 2015. 2. 17..
 */




@WebServlet(name = "dataServlet")
public class dataServlet extends HttpServlet {
    static StringBuffer returnText;
    static int checklog;
    static String userID;
    static chat room;
    String gid;
    String nic;

    public void init() throws ServletException {
        System.out.println("서버 초기화 작업");
        try {
            DB.accessDB();
            System.out.println("DB 연결 완료");
            DB.loadDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // System.out.println("post 시작");

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UFT-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        boolean isEnd = false;

        // System.out.println(uri);

        if (uri.equals("/dataServlet/clear")) {
            System.out.println("클리어 받음");
            String Rname = request.getParameter("roomname");
            String type = request.getParameter("type");

            try
            {
                if(type.equals("drop"))
                    DB.droproom(Rname);
                else
                    DB.clearroom(Rname);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            isEnd = true;
            response.sendRedirect("/chat_server.jsp");
            return;
        }

        uri = uri.substring(uri.lastIndexOf("/") + 1);

        if((room=chat.findroom(uri))==null)
        {
            System.out.println("메이크 룸 ");
            room = new chat(uri, 0);
            DB.db.add(room);
            try {
                DB.makeroom(room.roomname);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        returnText = new StringBuffer();
        HttpSession session = request.getSession();
        gid = session.getId();
        for (userid user : room.connectuser) {
            if (user.gid.equals(gid))
                nic = user.id;

        }

        //System.out.println("SSSTart!");
        //response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        boolean isError = false;
        String nowlog;
        //String stateS = new String();
        int state = -1;

        try {

            state = Integer.parseInt(request.getParameter("state"));
            nowlog = request.getParameter("log");
            userID = request.getParameter("id");

            if (state != 0)
                System.out.println("스타트 스테이트" + state);
            switch (state) {
                case 0: { //메세지 로드
                    nowlog = request.getParameter("log");
                    int log = Integer.parseInt(nowlog);
                    if (room.chatlog.size() == log) {
                        // System.out.println("no update");
                        isEnd = true;
                        return;
                    }
                    makelogs(log);
                    break;
                }
                case 1: {//메세지 전송
                    nowlog = request.getParameter("log");
                    int log = Integer.parseInt(nowlog);
                    inputchat(request);
                    //System.out.print(gid);
                    returnText = new StringBuffer();
                    makelogs(log);
                    break;
                }
                case 2: {//메세지 전송 아이디 변환
                    nowlog = request.getParameter("log");
                    int log = Integer.parseInt(nowlog);
                    //String gid = request.getParameter("gid");
                    room.checkdupli(userID, gid);
                    nic = userID;
                    inputchat(request);
                    returnText = new StringBuffer();
                    makelogs(log);
                    break;
                }
                case 8: {//접속 아이디 등록, 메세지 로드, 세션 작업
                    // HttpSession session = request.getSession();
                    // String gid=session.getId();
                    String id = request.getParameter("id");
                    System.out.println(id + "-" + gid);
                    room.checkdupli(id, gid);
                    nic = id;
                    //    System.out.println("asd");
                    makelogs(0);
                    //  System.out.println("asdas");
                    System.out.println("현재 유저 테스트" + room.connectuser.toString());
                    break;
                }
                case 9: {// 접속 종료, 아이디 삭제
                    // HttpSession session = request.getSession();
                    room.deluser(gid);
                    isEnd = true;
                    return;
                }
                default:
                    return;

            }


        } catch (Exception e) {
            isError = true;
            System.out.println(e.getStackTrace().toString());
            returnText.append(e.toString());
        } finally {

            if (isError) {
                System.out.println("에러");
                out.println("Server Error" + " " + checklog + returnText.toString());
            } else if (isEnd) {
                //System.out.println("노업");
                response.setStatus(201);
                return;
            } else {
                inputids(); //전송전에 현재 접속 아이디와 메세지 로그를 추가
                out.println(returnText.toString());
                System.out.println("종료체크" + returnText.toString());


            }
            out.close();

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UFT-8");
        response.getWriter().println("잘못된 접속 입니다");

    }

    protected void inputchat(HttpServletRequest request) throws ServletException, IOException, SQLException {
        String msg = request.getParameter("msg");
        System.out.println("메세지 체크" + msg);
        returnText.append(nic);
        returnText.append(" : ");   //메세지 한줄을 메세지 로그 벡터에 삽입.
        returnText.append(msg);
        room.chatlog.add(returnText.toString()); //챗로그 어레이 리스트에 채팅로그 추가.
        DB.inputchat(room.roomname,returnText.toString());
        returnText.append("<br/>");
        //returnText=new StringBuffer();
    }

    protected void inputids() throws ServletException, IOException {
        StringBuffer returnid = new StringBuffer();
        for (userid user : room.connectuser) {  //id 어레이 리스트의 내용을 모아서 출력. 맨뒤에 현재 로그 추가
            returnid.append(user.id);
            returnid.append("<br/>");
        }
        returnText.append("--");
        returnText.append(returnid.toString());
        returnText.append("--");
        returnText.append(room.chatlog.size());
    }

    protected void makelogs(int now) throws ServletException, IOException { //입력받은 now 이후 순서의 채트로그를 리턴텍스트에 삽입
        System.out.println("case3 " + now);

        for (int i = now - 1 == -1 ? 0 : now; i < room.chatlog.size(); i++) {
            returnText.append(room.chatlog.get(i));
            returnText.append("<br/>");
            //   System.out.print(i);
        }
    }



}



