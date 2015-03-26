package chat;

import java.util.ArrayList;
import java.sql.* ;
import java.util.Vector;
import javax.sql.* ;
import static java.sql.DriverManager.*;


/**
 * Created by RaysMac on 2015. 3. 10..
 */

public class DB {

    static Statement st;
    static ResultSet rs;
    public static ArrayList <chat> db= new ArrayList<chat>();

    public static void accessDB () throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection con= getConnection("jdbc:mysql://localhost:3306/chat", "root", "root");
        st= con.createStatement();
    }


    public static void makeroom(String roomname) throws SQLException {
        StringBuffer sql= new StringBuffer();

        sql.append("create table ");
        sql.append(roomname);
        sql.append(" like chatroom");

        st.executeUpdate(sql.toString());
        inputchat("roomlist",roomname);
        sql=null;

    }

    public static void droproom(String roomname) throws SQLException {
        StringBuffer sql= new StringBuffer();

        sql.append("drop table ");
        sql.append(roomname);

        st.executeUpdate(sql.toString());
        chat room=chat.findroom(roomname);
        DB.db.remove(room);

        sql=new StringBuffer();
        sql.append("delete from roomlist where Rname='");
        sql.append(roomname);
        sql.append("'");
        System.out.println(sql.toString());
        st.executeUpdate(sql.toString());


        sql=null;
    }

    public static void inputchat(String roomname , String chatlog) throws SQLException {
        StringBuffer sql= new StringBuffer();

        sql.append("insert into ");
        sql.append(roomname);
        sql.append(" values (");
        if(roomname.equals("roomlist"))
            sql.append("default,");
        sql.append("'");
        sql.append(chatlog);
        sql.append("',default)");

        System.out.println(sql.toString());
        st.executeUpdate(sql.toString());
        sql=null;

    }

    public static void clearroom(String roomname) throws SQLException {
        StringBuffer sql= new StringBuffer();

        sql.append("delete from ");
        sql.append(roomname);

        st.executeUpdate(sql.toString());
        chat room=chat.findroom(roomname);
        DB.db.remove(room);
        sql=null;
    }

    public static void loadDB() throws SQLException {
        //   StringBuffer sql= new StringBuffer();

        rs=st.executeQuery("select * from roomlist");
        System.out.println("셀렉트문 시행");
        System.out.println(getTableRow());
        int size=getTableRow();
        for(int i=1; i<size+1; i++)
        {
            DB.db.add(new chat(rs.getString(2),0));
            System.out.println(rs.getString(2)+"DB 내용 출력");
            rs.next();
        }

        for(chat chat : DB.db){
            rs=st.executeQuery("select * from "+chat.roomname);
            System.out.println(getTableRow());
            size=getTableRow();
            for(int i=1; i<size+1; i++)
            {
                chat.chatlog.add(rs.getString(1));
                rs.next();
            }

        }
        System.out.println("디비 로드 완료");
    }

    protected static int getTableRow() throws SQLException {
        rs.last();
        int size=rs.getRow();
        rs.first();
        return size;
    }


    public static void Board_input(Board board) throws SQLException {
        String sql1 =  String.format("insert into board values (default, '%s' ,'%s','%s','%s',default, 0 )",board.title,board.writer,board.pwd,board.article);
        st.executeUpdate(sql1);
    }




    public static int board_count() throws SQLException {
        rs= st.executeQuery("SELECT COUNT(*) from board");
        System.out.println("()()");
        int count=rs.getInt(1);
        System.out.println(count);
        return count;
    }

    public static Vector<Board> Board_load(int count) throws SQLException {

        StringBuffer sql= new StringBuffer();
        rs= st.executeQuery("select conut * from board limit ");

        Vector<Board> boards = new Vector<Board>();


        return boards;
    }







}