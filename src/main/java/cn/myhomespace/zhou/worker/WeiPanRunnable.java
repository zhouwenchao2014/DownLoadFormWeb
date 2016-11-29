package cn.myhomespace.zhou.worker;

import cn.myhomespace.zhou.object.WeiPanObject;
import cn.myhomespace.zhou.utils.AnalysisHtml;
import cn.myhomespace.zhou.utils.DownLoadFile;
import cn.myhomespace.zhou.utils.WeiPanDownload;
import com.alibaba.fastjson.JSON;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by zwc on 2016/11/23.
 */
public class WeiPanRunnable implements Runnable{

    private String id;
    private List<String> ids;

    public WeiPanRunnable(String id,List<String> ids) {
        this.id = id;
        this.ids = ids;
    }

    public void run() {
        int size=ids.size();
        for(int i=0;i<size;i++)
        {
            for(int t=0;t<size;t++)
            {
                for(int j=0;j<size;j++)
                {
                    for(int k=0;k<size;k++)
                    {
                        for(int l=0;l<size;l++)
                        {
                            for(int m=0;m<size;m++)
                            {
                                for(int n=0;n<size;n++)
                                {
                                    for(int o=0;o<size;o++)
                                    {
                                        for(int p=0;p<size;p++)
                                        {
                                            for(int q=0;q<size;q++)
                                            {
                                                for(int r=0;r<size;r++)
                                                {
                                                    WeiPanDownload.downloadFromWeiPan(id+ids.get(i)+ids.get(t)+ids.get(j)+ids.get(k)+ids.get(l)+ids.get(m)+ids.get(n)+ids.get(o)+ids.get(p)+ids.get(q)+ids.get(r));
                                                    for(int s=size;s<size;s++)
                                                    {
                                                        WeiPanDownload.downloadFromWeiPan(id+ids.get(i)+ids.get(t)+ids.get(j)+ids.get(k)+ids.get(l)+ids.get(m)+ids.get(n)+ids.get(o)+ids.get(p)+ids.get(q)+ids.get(r)+ids.get(s));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}
