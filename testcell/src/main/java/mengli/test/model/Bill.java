package mengli.test.model;

/**
 * Created by mengli.huang on 2015/11/19.
 */
public class Bill {
    BillNode basic;
    BillNode cur;
    BillNode next;
    int increasedTime;

    public Bill()
    {

    }

    public Bill(BillNode basic, BillNode cur, BillNode next, int increasedTime) {
        this.basic = basic;
        this.cur = cur;
        this.next = next;
        this.increasedTime = increasedTime;
    }

    public BillNode getBasic() {
        return basic;
    }

    public void setBasic(BillNode basic) {
        this.basic = basic;
    }

    public BillNode getCur() {
        return cur;
    }

    public void setCur(BillNode cur) {
        this.cur = cur;
    }

    public BillNode getNext() {
        return next;
    }

    public void setNext(BillNode next) {
        this.next = next;
    }

    public int getIncreasedTime() {
        return increasedTime;
    }

    public void setIncreasedTime(int increasedTime) {
        this.increasedTime = increasedTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "basic=" + basic +
                ", cur=" + cur +
                ", next=" + next +
                ", increasedTime=" + increasedTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (increasedTime != bill.increasedTime) return false;
        if (!basic.equals(bill.basic)) return false;
        if (!cur.equals(bill.cur)) return false;
        if (!next.equals(bill.next)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = basic.hashCode();
        result = 31 * result + cur.hashCode();
        result = 31 * result + next.hashCode();
        result = 31 * result + increasedTime;
        return result;
    }
}
