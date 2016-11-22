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

    public static Elements getElementsFromUrlByClassName(String className,String urlStr){

        Document doc=getDocumentFromUrl(urlStr);
        Elements links = doc.getElementsByClass(className);
        return links;
    }

    public static Elements getElementsFromUrlByAttributeValue(String key,String value,String urlStr){

        Document doc=getDocumentFromUrl(urlStr);
        Elements links = doc.getElementsByAttributeValue(key,value);
        return links;
    }

    public static Elements getElementsFromUrlByTag(String tag,String urlStr){

        Document doc=getDocumentFromUrl(urlStr);
        Elements links = doc.getElementsByTag(tag);
        return links;
    }

    private static Document getDocumentFromUrl(String urlStr){
        URL url = null;
        Document doc=null;
        try {
            url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            doc = Jsoup.parse(urlConnection.getInputStream(),"gb2312",urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
