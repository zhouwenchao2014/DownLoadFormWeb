package cn.myhomespace.zhou.utils;

import java.io.*;

/**
 * Created by zwc on 2016/11/22.
 */
public class WriteToFile {

    private static final String PATH=System.getProperty("user.home")+"\\DownLoadFromDYG";

    private static final String FILE_NAME="info.txt";

    public static void writeOrAppend(String message){
        File file=new File(PATH+"/"+FILE_NAME);
        write(PATH,file,message);
    }
    public static void writeOrAppend(String var,String message,boolean isPath){
        File file=null;
        if(isPath){
            file=new File(var+"/"+FILE_NAME);
            write(var,file,message);
        }else{
            file=new File(PATH+"/"+var);
            write(PATH,file,message);
        }



    }
    public static void writeOrAppend(String path,String fileName,String message){
        File file=new File(path+"/"+fileName);
        write(path,file,message);
    }

    private static void write(String path,File file,String message){
        FileWriter fileWriter=null;
        File filePath=new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(message+"\r\n");
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
