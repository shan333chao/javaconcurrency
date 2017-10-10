package com.sunday.javaclassloader.practise2_create_classLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by HenDiao on 2017/10/5.
 */
public class MyClassLoader extends  ClassLoader {

    private  final  static String Default_Dir="D:\\classDir\\EncriptClass";
    private   String classLoaderName;
    private String dir= Default_Dir;

    public MyClassLoader(ClassLoader parent ) {
        super(parent);

    }

    public MyClassLoader(String classLoaderName) {
        super();
        this.classLoaderName=classLoaderName;
    }

    public MyClassLoader(String classLoaderName,ClassLoader parent){
        super(parent);
        this.classLoaderName=classLoaderName;

    }

    public  String getDir(){
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

    /**
     * xx.xx.x.x.x.xx.
     * @param sname
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String sname) throws ClassNotFoundException {
        if (sname==null||sname=="")return null;
        System.out.println("sname = [" + sname + "]");
        String classPath= sname.replace(".","/");
        File clazzFile=new File(dir,classPath+".class");
        if (!clazzFile.exists()){
            throw  new ClassNotFoundException("not found class "+sname+" in "+ dir);

        }
        byte [] classBytes= new byte[0];
        try {
            classBytes = loadClassBytes(clazzFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null==classBytes||classBytes.length==0){
            throw  new ClassNotFoundException("load the class "+sname+" failed");
        }
        return this.defineClass(sname,classBytes,0,classBytes.length);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
       Class<?> clazz=null;
       ///打破双亲委托机制
       if (name.startsWith("java.")){
           try {
             ClassLoader system=ClassLoader.getSystemClassLoader();
             system.loadClass(name);
             if (clazz!=null){
                 if (resolve){
                        resolveClass(clazz);
                 }else {
                     return  clazz;
                 }
             }
           }catch (Exception e){

           }
       }

       try {
           clazz=findClass(name);
       }catch (Exception e){

       }
       if (clazz==null&&getParent()!=null){
           getParent().loadClass(name);
       }

        return super.loadClass(name, resolve);
    }

    private byte[] loadClassBytes(File clazzFile) throws IOException {
     String fileUrl=   clazzFile.getPath();
     ;
         return Files.readAllBytes(   Paths.get(fileUrl));
//        try (ByteArrayOutputStream baos=new ByteArrayOutputStream();
//             FileInputStream fis=new FileInputStream(clazzFile)
//        ){
//            byte[] buffer=new byte[1024];
//            int len=0;
//            while ((len=fis.read(buffer))!=-1){
//                baos.write(buffer,0,len);
//            }
//            baos.flush();
//            return baos.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}
