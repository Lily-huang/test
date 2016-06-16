package mengli.test.test;

import mengli.test.model.Bill;
import mengli.test.model.BillNode;
import mengli.test.model.BillResult;
import mengli.test.model.Goods;
import mengli.test.tools.DataHandler;
import mengli.test.tools.DateUtil;
import java.util.List;
import java.util.Map;

/**
 * Created by mengli.huang on 2015/11/19.
 */
public class Calculator {
    private static final String PRICE_PATH = "";
    private static final Map<String, Integer> priceMap = DataHandler.getPriceMap(PRICE_PATH);

    public static enum LimitRule {
        None(0, "in limit"),
        CUR_LIMIT(1, "transaction over limit"),
        ADD_LIMIT(2, "over limit after this transaction"),;

        public int code;
        public String desc;

        private LimitRule(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static LimitRule fromCode(int code) {
            switch (code) {
                case 0:
                    return None;
                case 1:
                    return CUR_LIMIT;
                case 2:
                    return ADD_LIMIT;
                default:
                    return None;
            }
        }
    }

    private static float getOverMemberFee() {
        return new Temporary().getBasic();
    }

    private static float getSingle(List<Goods> goodsList) {
        float single = 0.0f;
        for (Goods goods : goodsList) {
            single += priceMap.get(goods.getName()) * goods.getNum();
        }
        return single;
    }

    private static LimitRule ifOverLimit(int curTimes, int limitTimes, int increasedTimes) {
        if (curTimes > limitTimes) {
            return LimitRule.fromCode(1);
        }
        if (curTimes + increasedTimes > limitTimes) {
            return LimitRule.fromCode(2);
        }
        return LimitRule.fromCode(0);
    }

    public static BillResult getBill(int basic, float fee, int curTimes, int limitTimes, String start, String end, List<Goods> goodsList, String out, String back, int increasedTimes) {
        if (DateUtil.compareDate(start, out) || !DateUtil.compareDate(back, out)) {
            return new BillResult();
        }
        BillNode base = new BillNode(start, basic);
        String curEnd = DateUtil.getNextMonth(start);
        String nextEnd = DateUtil.getNextMonth(DateUtil.getNextMonth(start));
        float single = getSingle(goodsList);
        LimitRule limitRule = ifOverLimit(curTimes, limitTimes, increasedTimes);
        if (limitRule.equals(LimitRule.CUR_LIMIT)) {
            return limitBill(base,start,end,fee,curEnd,nextEnd,out,back,single,curTimes,limitTimes,increasedTimes,false);
        }
        if (limitRule.equals(LimitRule.ADD_LIMIT)) {
            return limitBill(base,start,end,fee,curEnd,nextEnd,out,back,single,curTimes,limitTimes,increasedTimes,true);
        }
        return limitBill(base, start,end, 0.0f, curEnd, nextEnd, out, back, single, curTimes, limitTimes, increasedTimes, false);

    }

    private static BillResult limitBill(BillNode base, String start,String end, float fee, String curEnd, String nextEnd, String out, String back, float single, int curTimes, int limitTimes, int increasedTimes,boolean isAdd) {
        BillNode cur = new BillNode();
        BillNode next = new BillNode();
        float additional = base.getCount() * fee * (increasedTimes + curTimes - limitTimes);
        float overMemberFee = 0.0f;
        float curAdditional = 0.0f;
        float nextAdditional = 0.0f;
        if (DateUtil.compareDate(curEnd,out)&&isAdd) {
            curAdditional = additional;
        } else {
            if (DateUtil.compareDate(nextEnd, out)&&isAdd) {
                nextAdditional = additional;
            }
        }
        if (DateUtil.compareDate(curEnd, out)) {
            cur = new BillNode(curEnd, curAdditional + single * DateUtil.daysBetween(out, DateUtil.compareDate(curEnd, back) ? back : curEnd));
        }
        if (!DateUtil.compareDateSmall(back, end)) {
            overMemberFee = increasedTimes * getOverMemberFee();
        }
        if (DateUtil.compareDateSmall(curEnd, back)) {
            next = new BillNode(nextEnd, nextAdditional + overMemberFee + single * DateUtil.daysBetween((DateUtil.compareDateSmall(out, curEnd)) ? DateUtil.getNextDay(curEnd) : out, DateUtil.compareDate(nextEnd, back) ? back : nextEnd));
        }
        return new BillResult(start,end,new Bill(base, cur, next, increasedTimes));
    }
}
