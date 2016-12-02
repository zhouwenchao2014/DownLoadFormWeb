package cn.myhomespace.zhou.utils;


import cn.myhomespace.zhou.object.WeiPanObject;
import cn.myhomespace.zhou.worker.WeiPanRunnable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final String THUNDER_HASH="magnet:?xt=urn:btih:";

    private static final String UTF_8="utf-8";

    public static void main(String[] args) {
        //WeiPanDownload.downloadFromWeiPan("uxDx_GIHW6WOS");
        //weiPan();
        //clcl();
        xunshukan();
    }

    public static void xunshukan(){
        List<String> list=new ArrayList<String>();
        String baseUrl="http://www.xunshukan.com/h/?";
        list.add("dushiqinggan/");
        list.add("renqiluanlun/");
        list.add("xiaoyuanchunse/");
        list.add("wuxiagudian/");
        list.add("paoniumiji/");
        list.add("xingjingli/");
        list.add("changpianHxiaoshuo/");
        int size=list.size();
        for(int j=1;j<=size;j++){
            for(int i=1;i<37;i++){
                String url=baseUrl+list.get(j-1)+"list_"+j+"_"+i+".html";
                Elements elements=AnalysisHtml.getElementsFromUrlByTag("a",url,"GBK");
                if(elements!=null){
                    for(Element element : elements){
                        String linkHref = element.attr("href");
                        String text = element.text();
                        if(linkHref.contains("dushiqinggan")){
                            Elements contentsEle = AnalysisHtml.getElementsFromUrlByAttributeValue("id","booktext","http://www.xunshukan.com/h/"+linkHref,"GBK");
                            for(Element element1 : contentsEle){
                                String content = element1.text();
                                WriteToFile.writeOrAppend("E:\\Tool\\Ebook\\"+list.get(j-1),text+".txt",content);
                            }
                        }

                    }
                }
            }
        }


    }

    public static void clcl(){
        for(int i=0;i<25;i++){
            String url="http://t66y.com/thread0806.php?fid=20&search=&page="+(i+1);
            Elements elements=AnalysisHtml.getElementsFromUrlByTag("a",url,UTF_8);
            if(elements!=null){
                for(Element element : elements){
                    String linkHref = element.attr("href");
                    String text = element.text();
                    Elements contentsEle = AnalysisHtml.getElementsFromUrlByAttributeValue("class","tpc_content do_not_catch",linkHref,UTF_8);
                    for(Element element1 : contentsEle){
                        String content = element1.text();
                        WriteToFile.writeOrAppend(text+".txt",content,false);
                    }
                }
            }
        }
    }

    public static void weiPan(){
        List<String> ids=new ArrayList<String>();
        for(int i=(int)'a';i<'a'+26;i++)
        {
            ids.add((char)i+"");
        }
        for(int i=(int)'A';i<'A'+26;i++)
        {
            ids.add((char)i+"");
        }
        for(int i=0;i<10;i++){
            ids.add(i+"");
        }
        ids.add("-");
        System.out.println("数据组装完毕");
        for(String str : ids){
            WeiPanRunnable weiPanRunnable=new WeiPanRunnable(str,ids);
            Thread thread=new Thread(weiPanRunnable);
            thread.start();
        }


    }


    /**
     * 琉璃神社下载
     */
    public static void liuli() {
        for(int i=1;i<145;i++){
            final int a=i;
            Runnable runnable=new Runnable() {
                public void run() {
                    String url="http://www.hacg.la/wp/page/"+a+"/";
                    if(a==1){
                        url="http://hacg.la/wp/";
                    }
                    Elements elements=AnalysisHtml.getElementsFromUrlByAttributeValue("rel","bookmark",url,UTF_8);
                    if(elements!=null){
                        for(Element element : elements){
                            String linkHref = element.attr("href");
                            String text = element.text();
                            System.out.println(linkHref);
                            WriteToFile.writeOrAppend("liu-li-url.txt",text+":"+linkHref,false);
                            String linkText = element.text();
                            if(linkHref!=null){
                                Elements pLinks = AnalysisHtml.getElementsFromUrlByTag("p",linkHref,UTF_8);
                                Elements strongLinks = AnalysisHtml.getElementsFromUrlByTag("strong",linkHref,UTF_8);
                                Elements preLinks = AnalysisHtml.getElementsFromUrlByTag("pre",linkHref,UTF_8);
                                if(preLinks!=null){
                                    for(Element preLink : preLinks){
                                        if(preLink.text().contains("本站不提供下载")){
                                            Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]");
                                            Matcher matcher = pattern.matcher(preLinks.text());
                                            String mes=matcher.replaceAll("");
                                            WriteToFile.writeOrAppend("pre.txt",THUNDER_HASH+mes,false);
                                        }
                                    }
                                }
                                if(pLinks!=null){
                                    for(Element pLink : pLinks){
                                        Pattern pattern = Pattern.compile("[0-9A-Za-z]{41}");
                                        Matcher matcher = pattern.matcher(pLink.text());
                                        if(matcher.find()){
                                            String message=matcher.group();
                                            System.out.println(message);
                                            WriteToFile.writeOrAppend(a+".txt",THUNDER_HASH+message,false);
                                        }
                                    }
                                }
                                if(strongLinks!=null){
                                    for(Element strongLink : strongLinks){
                                        Pattern pattern = Pattern.compile("[0-9A-Za-z]{41}");
                                        Matcher matcher = pattern.matcher(strongLink.text());
                                        if(matcher.find()){
                                            String message=matcher.group();
                                            System.out.println(message);
                                            WriteToFile.writeOrAppend(a+".txt",THUNDER_HASH+message,false);
                                        }
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

    /**
     * 电影港下载
     */
    public static void movie() {
        for(int i=0;i<9;i++){
            final int a=i;
            for(int j=0;j<9;j++){
                final int b=j;
                Runnable runnable=new Runnable() {
                public void run() {
                        for(int k=0;k<9;k++){
                            for(int l=0;l<9;l++){
                                for(int m=0;m<9;m++){
                                    for(int n=0;n<9;n++){
                                        String id=a+""+b+""+k+""+l+""+m+""+n;
                                        System.out.println(id);
                                        download(id);
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
    }

    public static void download(String id){
        Elements links=AnalysisHtml.getElementsFromUrlByClassName(CLASS_NAME,BASE_LINK+id,UTF_8);
        if(links!=null&&!links.isEmpty()){
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                WriteToFile.writeOrAppend("pathAndName.txt",linkText+":"+linkHref,false);
                //WriteToFile.writeOrAppend("ThunderLink.txt",linkText,false);
                Elements downLinks = AnalysisHtml.getElementsFromUrlByTag(TAG,linkHref,UTF_8);
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
