package chat;

import java.util.ArrayList;

/**
 * Created by RaysMac on 2015. 3. 10..
 */

public class chat {
    public  ArrayList<String> chatlog=new ArrayList<String>();
    public  ArrayList<userid> connectuser = new ArrayList<userid>();
    public String roomname;
    int type;

    // type
    // 0 노멀 채팅
    // 1 1:1 노멀 채팅
    // 2 멀티 랜덤 채팅


    chat(String roomname, int type){

        this.roomname=roomname;
        this.type=type;
        ArrayList<String> chatlog=new ArrayList<String>();
        ArrayList<userid> connectuser = new ArrayList<userid>();

    }

    public void checkdupli(String id,String gid) {
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


    public void deluser(String gid){
        for(int i=0; i<connectuser.size()-1; i++){
            System.out.print(connectuser.get(i)+"    "+gid);
            if(connectuser.get(i).gid.equals(gid))
            {
                connectuser.remove(i);
                System.out.print("접속 종료");
            }
        }

    }

    public static chat findroom(String uri) {

        for (chat a : DB.db) {
            if (a.roomname.equals(uri))
                return a;
        }
        return null;
    }



}
