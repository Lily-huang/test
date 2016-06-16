package mengli.test.model;

/**
 * Created by mengli.huang on 2015/11/22.
 */
public class BillResult {
    String start;
    String end;
    Bill bill;

    public BillResult(){}
    public BillResult(String start, String end, Bill bill) {
        this.start = start;
        this.end = end;
        this.bill = bill;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "BillResult{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", bill=" + bill +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillResult that = (BillResult) o;
        if(bill==null||end==null||start==null)
            return false;
        if (!bill.equals(that.bill)) return false;
        if (!end.equals(that.end)) return false;
        if (!start.equals(that.start)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + bill.hashCode();
        return result;
    }
}
