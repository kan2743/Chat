package chat;

/**
 * Created by RaysMac on 2015. 3. 19..
 */
public class Board {
    public String title,writer,pwd,article;
    int acces;


    Board(String title,String writer,String pwd, String article){
        this.title=title;
        this.writer=writer;
        this.pwd=pwd;
        this.article=article;
        acces=0;
    }




}


