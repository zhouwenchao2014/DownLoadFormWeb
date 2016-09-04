/**
 * Created by zhouwenchao on 16/2/20.
 */
window.onload=function(){
    $.ajax({
        type: 'POST',
        url: "/getMatchHistory" ,
        dataType:'json',
        success:function(data) {
            if(data){
                var body=$("body");
                var heroName="";
                var heroId="";
                for(var i=0;i<10;i++){
                    heroIds=data[i].players;
                    for(var j=0;j<heroIds.length;j++){
                       heroId= heroIds[j].hero_id;
                        $.ajax({
                            type: 'POST',
                            url: "/getHeroInfo" ,
                            data:{"heroId":heroId},
                            dataType:'json',
                            success:function(hero) {
                                if(hero){
                                    //npc_dota_hero_
                                    //var heroObject=JSON.parse(hero);
                                    heroName=hero.name.substring(14);
                                    body.append("<div><img src='http://cdn.dota2.com/apps/dota2/images/heroes/"+heroName+"_sb.png'></div>");
                                }
                            }
                        });

                    }
                }
            }else{
                     }
           },
          error : function() {
                   // view("异常！");
                   alert("异常！");
              }

        });
}
