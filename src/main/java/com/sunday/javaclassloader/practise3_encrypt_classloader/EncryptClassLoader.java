package com.sunday.javaclassloader.practise3_encrypt_classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by HenDiao on 2017/10/6.
 */
public class EncryptClassLoader extends  ClassLoader {
    public  final  static String Default_Dir="D:\\classDir\\EncriptClass\\";

    private String dir= Default_Dir;

    public EncryptClassLoader(String classLoaderName) {
        super();

    }

    public EncryptClassLoader(String classLoaderName,ClassLoader parent){
        super(parent);


    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        File classFile=new File(dir,classPath+".class");
        if ( !classFile.exists())
        {
            throw  new ClassNotFoundException("not found class "+name+" in "+ dir);
        }

        byte[] classBytes=loadClassBytes(classFile);
        if (null==classBytes||classBytes.length==0){

            throw  new ClassNotFoundException("load class failed"+name+" in "+ dir);
        }

        return this.defineClass(name,classBytes,0,classBytes.length);

    }

    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream baos=new ByteArrayOutputStream();
             FileInputStream fis=new FileInputStream(classFile)
        ){
            byte[] buffer=new byte[1024];
            int data;
            while ((data=fis.read())!=-1){
                baos.write(data^EncryptUtils.ENCRIPT_KEY);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
