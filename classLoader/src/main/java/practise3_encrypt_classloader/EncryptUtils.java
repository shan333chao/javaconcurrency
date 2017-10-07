package practise3_encrypt_classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by HenDiao on 2017/10/6.
 */
public final class EncryptUtils  {
    public   static  final  byte ENCRIPT_KEY=(byte) 0xff;
    private EncryptUtils() {

    }
    public  static  void doEncrypt(String source, String taget){
        try (FileInputStream fis=new FileInputStream(source);
             FileOutputStream fos=new FileOutputStream(taget)){
            int data;
            while ((data=fis.read())!=-1){
                fos.write(data^ENCRIPT_KEY);
            }
            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        doEncrypt("D:\\classDir\\EncriptClass\\MyObject.class","D:\\classDir\\EncriptClass\\MyObject2.class");
    }
}
