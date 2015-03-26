package chat;


import java.io.*;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import java.sql.*;

public class DB_user {

    public static User getuser(String findid) throws SQLException {
        ResultSet rs=DB.st.executeQuery("select * from users where userid='"+ findid+"'" );
        String userid = null;
        String pwd = null;
        String email = null;
        String room = null;
        if(rs.next()) {
            userid = rs.getString(1);
            pwd = rs.getString(2);
            email = rs.getString(3);
            room = rs.getString(4);
            User user = new User(userid, pwd, email, room);
            return user;
        }
        return null;
    }

    public static void insert(User user) throws SQLException {
        String sql= String.format("insert into users values('%s','%s','%s','%s')",user.userid,user.pwd,user.email,user.room);
        System.out.print(sql);
        DB.st.executeUpdate(sql);
    }

    public static void update(User user) throws SQLException {
        String sql = String.format("UPDATE users SET pwd= '%s', email ='%s', room = '%s' where userid = '%s'",user.pwd,user.email,user.room,user.userid);
        System.out.println(sql);
        DB.st.executeUpdate(sql);
    }
}