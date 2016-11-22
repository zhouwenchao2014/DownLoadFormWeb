package cn.myhomespace.zhou.utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zwc on 2016/11/21.
 */
public class HtmlCrawler {

    private static final String KEY="thunderpid";

    private static final String VALUE="00000";

    private static final String TAG="a";

    private static final String FTP="ftp";

    private static final String ED2K="ed2k";

    private static final String BASE_LINK="http://www.dygang.com/e/search/result/?searchid=";

    private static final String CLASS_NAME="classlinkclass";

    public static void main(String[] args) {
        for(int i=0;i<9;i++){
            final int a=i;
            Runnable runnable=new Runnable() {
                public void run() {
                    for(int j=0;j<9;j++){
                        for(int k=0;k<9;k++){
                            for(int l=0;l<9;l++){
                                for(int m=0;m<9;m++){
                                    for(int n=0;n<9;n++){
                                        String id=a+""+j+""+k+""+l+""+m+""+n;
                                        System.out.println(id);
                                        download(id);
                                    }
                                }
                            }
                        }
                    }
                }
            };
            Thread thread=new Thread(runnable);
            thread.start();

        }
    }

    public static void download(String id){
        Elements links=AnalysisHtml.getElementsFromUrlByClassName(CLASS_NAME,BASE_LINK+id);
        if(links!=null&&!links.isEmpty()){
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                WriteToFile.writeOrAppend("pathAndName.txt",linkText+":"+linkHref,false);
                Elements downLinks = AnalysisHtml.getElementsFromUrlByTag(TAG,linkHref);
                if(downLinks!=null&&!downLinks.isEmpty()){
                    for(Element downLink : downLinks){
                        String href = downLink.attr("href");
                        System.out.println(href);
                        if(href.contains(ED2K)||href.contains(FTP)){
                            WriteToFile.writeOrAppend("ThunderLink.txt",href,false);
                        }

                    }
                }

            }
        }
    }
}
