package cn.myhomespace.zhou.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zwc on 2016/11/22.
 */
public class ElementsUtils {

    private static final String FTP="ftp";

    private static final String ED2K="ed2k";

    private static final String THUNDER_HASH="magnet:?xt=urn:btih:";

    private static final String UTF_8="utf-8";

    public static void getInfoByTagAndRegex(String tag,String url,String regex){
        Elements downLinks = AnalysisHtml.getElementsFromUrlByTag(tag,url,UTF_8);
        if(downLinks!=null&&!downLinks.isEmpty()){
            for(Element downLink : downLinks){
                String href = downLink.attr("href");
                System.out.println(href);
                if(href.contains(ED2K)||href.contains(FTP)||href.contains(THUNDER_HASH)){
                    WriteToFile.writeOrAppend("ThunderLink.txt",href,false);
                }

            }
        }
    }
}
