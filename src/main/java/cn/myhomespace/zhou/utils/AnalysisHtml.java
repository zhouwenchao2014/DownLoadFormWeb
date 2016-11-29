package cn.myhomespace.zhou.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zwc on 2016/11/22.
 */
public class AnalysisHtml {

    private static final String LAN="gb2312";

    public static Elements getElementsFromUrlByClassName(String className,String urlStr,String lan){

        Document doc=getDocumentFromUrl(urlStr,lan);
        Elements links = doc.getElementsByClass(className);
        return links;
    }

    public static Elements getElementsFromUrlByAttributeValue(String key,String value,String urlStr,String lan){

        Document doc=getDocumentFromUrl(urlStr,lan);
        Elements links = doc.getElementsByAttributeValue(key,value);
        return links;
    }

    public static Elements getElementsFromUrlByTag(String tag,String urlStr,String lan){

        Document doc=getDocumentFromUrl(urlStr,lan);
        if(doc!=null){
            Elements links = doc.getElementsByTag(tag);
            return links;
        }else{
            return null;
        }

    }

    private static Document getDocumentFromUrl(String urlStr,String lan){
        if(!StringUtils.isNotBlank(lan)){
            lan = LAN;
        }
        URL url = null;
        Document doc=null;
        try {
            url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
            doc = Jsoup.parse(urlConnection.getInputStream(),lan,urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
