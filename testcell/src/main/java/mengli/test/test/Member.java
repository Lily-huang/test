package mengli.test.test;

import mengli.test.model.*;
import mengli.test.tools.StringUtil;

import java.util.List;

/**
 * Created by mengli.huang on 2015/11/19.
 */
public abstract class Member implements transaction{

    public int getBasic() {
        return 0;
    }


    public int getSingle(String name) {
        return 0;
    }


    public String getEnd() {
        return "";
    }


    public int getIncreasedTime(List<Goods> goodses) {
        int count=0;
        for(Goods goods:goodses){
            count += goods.getNum();
        }
        return count;
    }


    public float getFee() {
        return 0;
    }


    public String ifUserExist(String userId) {
        return "";
    }


    public boolean addUser(String userId, String start) {
        return false;
    }


    public int getCurTimes(String userId) {
        return 0;
    }


    public int getLimitTimes() {
        return 0;
    }


    public String getStart(String userId) {
        return "";
    }


    public boolean ifPassHalf(String userId,String start, String changeDate) {
        return false;
    }


    public boolean ifChangeAllow(String now, String to) {
        return false;
    }


    public ChangeNode getStopFee(String userId,String start, String changeDate) {
        if(!ifPassHalf(userId,start,changeDate)){
            return new ChangeNode(getBasic()/2,changeDate);
        }
        return new ChangeNode(0,getEnd());
    }


    public ChangeNode getChangeFee(String now, String to) {
        return new ChangeNode(1,"");//TODO
    }


    public int test(int num) {
        return num*2;
    }


    public BillResult getBill(BillInput billInput){
        if(StringUtil.isEmpty(ifUserExist(billInput.getUserId()))){
            addUser(billInput.getUserId(),billInput.getStart());
        }
        return Calculator.getBill(getBasic(),getFee(),billInput.getCurTimes(),getLimitTimes(),billInput.getStart(),getEnd(),billInput.getGoodsList(),billInput.getOut(),billInput.getBack(),getIncreasedTime(billInput.getGoodsList()));
    }
}
