package chat;

import java.util.ArrayList;

/**
 * Created by RaysMac on 2015. 2. 23..
 */
public class userid {

    public static ArrayList<userid> connectuser = new ArrayList<userid>();
    public String id;
    String gid;


    userid(String id,String gid){
        this.id=id;
        this.gid=gid;
    }



    /*public static void checkdupli(String str) {
        if (!connectuser.contains(str))
            connectuser.add(str);
    }*/

    public static void checkdupli(String id,String gid) {
        ArrayList<String> ids = new ArrayList<String>();
        String temp=id;
        //String temp=new String();
        for(userid user : connectuser) {
            ids.add(user.id);
            System.out.println(user.gid);
            System.out.println(gid);
            if (user.gid.equals(gid)) {
                user.id = id;
                System.out.println("아이디를 변경");
                return;
            }
        }
        //if(ids.contains(id))
        //  temp =id+"_1";
        connectuser.add(new userid(temp,gid));

    }


    public static void deluser(String gid){
        for(int i=0; i<connectuser.size()-1; i++){
            System.out.print(connectuser.get(i)+"    "+gid);
            if(connectuser.get(i).gid.equals(gid))
            {

                connectuser.remove(i);
                System.out.print("접속 종료");
            }
        }

    }




}
