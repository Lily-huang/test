package mengli.test.tools;

import mengli.test.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengli.huang on 2015/11/21.
 */
public class Transform {
    public BillInput getInput(String type,String userId,String curTimes, String start,String out, String back,String num1,String num2,String num3,String num4) {
        return new BillInput(type,userId,start,out,back,Integer.parseInt(curTimes),getGoods(num1,num2,num3,num4));
    }
    public BillResult getResult(String start,String end,BillNode basic,BillNode cur,BillNode next, String increasedTime){
        return new BillResult(start,end,new Bill(basic,cur,next,Integer.parseInt(increasedTime)));
    }
    public List<Goods> getGoods(String num1,String num2,String num3,String num4){
        List<Goods> goodsList=new ArrayList<Goods>();
        goodsList.add(new Goods("Movies",Integer.parseInt(num1)));
        goodsList.add(new Goods("Documentaries",Integer.parseInt(num2)));
        goodsList.add(new Goods("Games",Integer.parseInt(num3)));
        goodsList.add(new Goods("Sports",Integer.parseInt(num4)));
        return goodsList;
    }
}
