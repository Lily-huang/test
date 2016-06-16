package mengli.test.model;

/**
 * Created by mengli.huang on 2015/11/21.
 */
public class BillNode {
    String date;
    float count;

    public BillNode(){

    }
    public BillNode(String date, float count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BillNode{" +
                "date='" + date + '\'' +
                ", count=" + count +
                '}';
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillNode billNode = (BillNode) o;

        if (Float.compare(billNode.count, count) != 0) return false;
        if (!date.equals(billNode.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + (count != +0.0f ? Float.floatToIntBits(count) : 0);
        return result;
    }
}
