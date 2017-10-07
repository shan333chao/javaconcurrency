package practise2_create_classLoader;

/**
 * Created by HenDiao on 2017/10/5.
 */
public class MyObject {

    static {
        System.out.println("My obj static block");
    }
    public  String Hello(){
        System.out.println("my obj hello");
        return "my obj hello";
    }
}
