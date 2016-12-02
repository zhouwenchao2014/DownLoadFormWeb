package cn.myhomespace.zhou.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;

/**
 * 根据url获取各种流
 * Created by zhouwenchao on 16/2/18.
 */
public class UrlUtils {



    public static String getNameFromUrl(String url){
        String fileName=url.substring(url.lastIndexOf("/"),url.length());
        return fileName;
    }

    public static BufferedReader getBufferByUrlNoProperty(String urlStr){
        BufferedReader bufferedReader=null;
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        try {
            URL url=new URL(urlStr);
            System.out.println("Open connect");
            URLConnection urlConnection=url.openConnection();
            System.out.println("get stream");
            inputStream=urlConnection.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream,"GBK");
            bufferedReader=new BufferedReader(inputStreamReader);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    /**
     * 根据url获取字符流
     * @param urlStr
     * @return
     */
    public static BufferedReader getBufferByUrl(String urlStr, Queue<String> queue){
        BufferedReader bufferedReader=null;
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        try {
            URL url=new URL(urlStr);
            URLConnection urlConnection=url.openConnection();
            urlConnection.setRequestProperty("Accept-Charset","utf-8");
            urlConnection.setRequestProperty("Content-Type","text/html;utf-8");
//            urlConnection.setRequestProperty("Cookie","Hm_lpvt_e0812646157cf7c35cba87cb1e72afc5=1479143386");
//            urlConnection.setRequestProperty("Cookie","Hm_lvt_e0812646157cf7c35cba87cb1e72afc5=1479143379");
//            urlConnection.setRequestProperty("Cookie","AV_TOKEN=\"2|1:0|10:1479143380|8:AV_TOKEN|16:NTk1NjcwNjpUN2Zu|9f7cf1ec65c7bb037f1f8506c475e66c3bcdb8319a1ec60986ae535badaef0b7\"");
            urlConnection.setRequestProperty("Cookie","Hm_lpvt_e0812646157cf7c35cba87cb1e72afc5=1480423398");
            urlConnection.setRequestProperty("Cookie","Hm_lvt_e0812646157cf7c35cba87cb1e72afc5=1480422838,1480423398");
            urlConnection.setRequestProperty("Cookie","AV_TOKEN=\"2|1:0|10:1480423205|8:AV_TOKEN|16:MTc4NTE2Nzo1MTI4|40f64f1c568578de4eab35bbba576284f09dd971c53575ba7bd60e1f8cdbbee3\"");
            urlConnection.connect();
            inputStream=urlConnection.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream,"GBK");
            bufferedReader=new BufferedReader(inputStreamReader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            while (true){
                String url=queue.poll();
                if(url!=null){
                    String[] urls=url.split(",");
                    DownLoadFile.downLoadFile(urls[0],urls[1]);
                }
            }
//            e.printStackTrace();
        }finally {

//            try {
//                if (inputStream!=null){
//                    inputStream.close();
//                }
//                if(inputStreamReader!=null){
//                    inputStreamReader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return bufferedReader;
    }

    /**
     * 根据url获取InputStream
     * @return
     */
    public static InputStream getInputStreamByUrl(String urlStr){
        InputStream inputStream=null;
        try {
            URL url=new URL(urlStr);
            URLConnection urlConnection=url.openConnection();
            inputStream=urlConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return inputStream;
    }

    /**
     * 根据InputStream获取BufferedReader
     * @param inputStream
     * @return
     */
    public static BufferedReader getbufferByInputSteam(InputStream inputStream){
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        return bufferedReader;
    }
}
