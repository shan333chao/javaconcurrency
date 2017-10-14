/**
 * Created by HenDiao on 2017/10/14.
 */
public class Singleton2 {
    private Singleton2(){

    }
    private static  class  Holder{
        public static Singleton2 instance=new Singleton2() ;
    }
    public Singleton2 getInstance(){
        return Holder.instance;
    }
    private void show(){
        System.out.println("show");
    }
    static {
        System.out.println("loaded");
    }
}

