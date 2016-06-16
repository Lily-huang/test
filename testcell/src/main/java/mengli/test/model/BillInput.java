package mengli.test.model;

import java.util.List;

/**
 * Created by mengli.huang on 2015/11/21.
 */
public class BillInput {
    String memberType;
    String userId;
    String start;
    String out;
    String back;
    int curTimes;
    List<Goods> goodsList;

    public BillInput(){

    }

    public BillInput(String memberType,  String userId,String start, String out, String back, int curTimes, List<Goods> goodsList) {
        this.memberType = memberType;
        this.start = start;
        this.userId = userId;
        this.out = out;
        this.back = back;
        this.curTimes = curTimes;
        this.goodsList = goodsList;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public int getCurTimes() {
        return curTimes;
    }

    public void setCurTimes(int curTimes) {
        this.curTimes = curTimes;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "BillInput{" +
                "memberType='" + memberType + '\'' +
                ", userId='" + userId + '\'' +
                ", start='" + start + '\'' +
                ", out='" + out + '\'' +
                ", back='" + back + '\'' +
                ", curTimes=" + curTimes +
                ", goodsList=" + goodsList +
                '}';
    }
}
