package chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by RaysMac on 2015. 3. 19..
 */
@WebServlet(name = "BoardServlet")
public class BoardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UFT-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        System.out.println(uri);

        try {
            DB.accessDB();
            if (uri.equals("/Board/input"))
                inputarticle(request, response);
            else if (uri.equals("/Board/update"))
                updatearticle(request, response);
            else if (uri.equals("/Board/delete"))
                deletearticle(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //response.sendRedirect("/main.jsp");

        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void inputarticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        Board board = new Board(
                request.getParameter("title"),
                request.getParameter("writer"),
                request.getParameter("pwd"),
                request.getParameter("article"));
        DB.Board_input(board);

        response.sendRedirect("/board_list.jsp");
    }


    protected void updatearticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void deletearticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }






}