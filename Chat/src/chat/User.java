package chat;

/**
 * Created by RaysMac on 2015. 3. 26..
 */
public class User {

    public String userid;
    public String pwd;
    public String email;
    public String room;

    User(String userid, String pwd, String email, String room)
    {
        this.userid=userid;
        this.pwd=pwd;
        this.email=email;
        this.room=room;
    }
}
