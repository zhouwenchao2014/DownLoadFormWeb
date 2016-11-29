package cn.myhomespace.zhou.utils;

import cn.myhomespace.zhou.object.WeiPanObject;
import com.alibaba.fastjson.JSON;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by zwc on 2016/11/25.
 */
public class WeiPanDownload {

    public static void downloadFromWeiPan(String id){
        Elements elements= AnalysisHtml.getElementsFromUrlByTag("script","http://vdisk.weibo.com/s/"+id,"UTF-8");
        if(elements!=null){
            for(Element element : elements){
                if( element.childNodes()!=null&&element.childNodes().size()!=0){
                    List<Node> nodes=element.childNodes();
                    String txt=nodes.get(0).attr("data");
                    if(txt.contains("download_list")&&txt.contains("mobi")){
                        txt=txt.replace("//页面数据","");
                        txt=txt.replace("vdisk.pagedata = ","");
                        int start=txt.indexOf("init")+5;
                        int end=txt.length()-9;
                        txt=txt.substring(start,end);
                        System.out.println(txt);
                        WeiPanObject weiPanObject= JSON.parseObject(txt, WeiPanObject.class);
                        if(weiPanObject!=null){
                            String[] download_lists=weiPanObject.getDownload_list().split("\",\"");
                            String download_list=download_lists[0].replace("[\"","");
                            String name = null;
                            try {
                                name = URLDecoder.decode(download_list.split("&")[4].split("=")[1],"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            DownLoadFile.downLoadFileUrl(download_list,name);
                            System.out.println(name);
                        }

                    }
                }
            }
        }

    }
}
