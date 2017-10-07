package practise3_encrypt_classloader;

/**
 * Created by HenDiao on 2017/10/6.
 */
public class SimpleEncrypt {
    private  static  final  byte ENCRIPT_KEY=(byte) 0xff;
    private  static  final String  content="hello sandy";
    public static void main(String[] args) {
        byte [] contentBytes=content.getBytes();
        byte [] encodes=new byte[contentBytes.length];
        for (int i=0;i<contentBytes.length;i++){
            encodes[i]=(byte)(contentBytes[i]^ENCRIPT_KEY);
        }
        System.out.println(new String( encodes));
        byte [] dencode=new byte[contentBytes.length];
        for (int k=0;k<encodes.length;k++){
            dencode[k]=(byte)(encodes[k]^ENCRIPT_KEY);
        }
        System.out.println(new String(dencode));

    }



}
