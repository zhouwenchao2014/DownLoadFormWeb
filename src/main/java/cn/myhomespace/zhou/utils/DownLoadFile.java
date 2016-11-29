package cn.myhomespace.zhou.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 根据文件URL下载文件
 * 各种文件，如图片，txt。等
 * Created by wenchao on 2016-2-24.
 */
public class DownLoadFile {

    //private static String path=SystemMessage.getUserHome()+"/Img";

    private static String path="E:\\Tool\\Ebook";


    private static Queue<String> queue = new LinkedList<String>();

    public static void main(String[] args) {
//        System.out.println(SystemMessage.getUserHome());
//        downLoadImg("http://cdn.dota2.com/apps/dota2/images/heroes/antimage_lg.png");
        Runnable geturlRunnable=new Runnable() {
            public void run() {
                downLoadFromLocalFile();
            }
        };
        Thread geturlThread=new Thread(geturlRunnable);
        geturlThread.start();
        Runnable downLoadRunnable=new Runnable() {
            public void run() {
                while (true){
                    String url=queue.poll();
                    if(url!=null){
                        String[] urls=url.split(",");
                        downLoadFile(urls[0],urls[1]);
                    }
                }
            }
        };
        Thread downLoadThread=new Thread(downLoadRunnable);
        downLoadThread.start();

    }

    public static void downLoadFromLocalFile(){
        File file=new File("E:\\Tool\\Ebook\\view.htm");
        try {
            InputStream inputStream=new FileInputStream(file);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            String fileName="";
            while ((line=bufferedReader.readLine())!=null){
                if(line.contains("item-title")){
                    int start=line.indexOf(">")+1;
                    int end=line.indexOf("<",5);
                    fileName=line.substring(start,end);
                }
                if(line.contains(" href=")&&line.contains(".txt")){
                    int start=line.indexOf("http://");
                    int end=line.indexOf(".txt")+4;
                    String urlStr=line.substring(start,end);
                    queue.offer(urlStr+","+fileName);
                    System.out.println(urlStr);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downLoadFromLogin(){
        String urlStr="http://apanr.net/login?account=linofking&password=zhou953685177";
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection resumeConnection = (HttpURLConnection) url.openConnection();


            resumeConnection.setRequestProperty("Accept-Charset","utf-8");
            resumeConnection.setRequestProperty("Content-Type","text/html;utf-8");
            resumeConnection.setRequestProperty("Cookie","Hm_lpvt_e0812646157cf7c35cba87cb1e72afc5=1471008539");
            resumeConnection.setRequestProperty("Cookie","Hm_lvt_e0812646157cf7c35cba87cb1e72afc5=1471007775,1471007784,1471008526,1471008539");
            resumeConnection.connect();

            InputStream urlStream = resumeConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(

                    new InputStreamReader(urlStream,"utf-8"));

            String ss = null;
            StringBuilder total = new StringBuilder();
            while ((ss = bufferedReader.readLine()) != null) {
                total.append(ss);
                System.out.println(ss);
            }

            bufferedReader.close();
            resumeConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean downLoadImg(String url){
        InputStream inputStream=UrlUtils.getInputStreamByUrl(url);
        String fileName=UrlUtils.getNameFromUrl(url);
        createAndDownLoadImg(inputStream,fileName);
        return true;
    }

    public static boolean downLoadFile(String url){
        BufferedReader bufferedReader=UrlUtils.getBufferByUrl(url,queue);
        String fileName=UrlUtils.getNameFromUrl(url);
        createAndDownLoadFile(bufferedReader,fileName);
        return true;
    }

    /**
     *
     * @param url
     * @param fileName
     * @return
     */
    public static boolean downLoadFileUrl(String url,String fileName){
        BufferedReader bufferedReader=UrlUtils.getBufferByUrlNoProperty(url);
        createAndDownLoadFile(bufferedReader,fileName);
        return true;
    }



    public static boolean downLoadFile(String url,String fileName){
        BufferedReader bufferedReader=UrlUtils.getBufferByUrl(url,queue);
        createAndDownLoadFile(bufferedReader,fileName);
        return true;
    }

    public static void createAndDownLoadFile(BufferedReader bufferedReader,String fileName){
        File dir=new File(path);
        File file=new File(path+"/"+fileName);
        if(!file.exists()){
            try {
                dir.mkdir();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            String line="";
            fileName=fileName.replace("/","");
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            while ((line=bufferedReader.readLine())!=null){
                bufferedWriter.write(line+"\n");
                System.out.println(line);
            }
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createAndDownLoadImg(InputStream inputStream,String fileName){
        File dir=new File(path);
        File file=new File(path+"/"+fileName);
        if(!file.exists()){
            try {
                dir.mkdir();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int byteread=0;
        int bytesum=0;
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inputStream.read(buffer)) != -1) {
                bytesum += byteread;
                //System.out.println(bytesum);
                fileOutputStream.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
