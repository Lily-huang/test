package mengli.test.test;

import mengli.test.model.BillInput;
import mengli.test.model.BillResult;
import mengli.test.model.ChangeNode;
import mengli.test.model.Goods;

import java.util.List;

/**
 * Created by mlhuang on 4/22/16.
 */
public interface transaction {
    int test(int num);//for test
    BillResult getBill(BillInput billInput);
    int getBasic();
    int getSingle(String name);
    String getEnd();
    int getIncreasedTime(List<Goods> goodses);
    float getFee();

    String ifUserExist(String userId);//return membership type
    boolean addUser(String userId,String start);
    int getCurTimes(String userId);
    int getLimitTimes();
    String getStart(String userId);
    boolean ifPassHalf(String userId,String start,String changeDate);
    boolean ifChangeAllow(String now,String to);
    ChangeNode getStopFee(String userId, String start, String changeDate);
    ChangeNode getChangeFee(String now,String to);
}
