package mengli.test.model;

/**
 * Created by mengli.huang on 2015/11/22.
 */
public class ChangeNode {
    float count;
    String changeDate;

    public ChangeNode(){}
    public ChangeNode(float count, String changeDate) {
        this.count = count;
        this.changeDate = changeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeNode that = (ChangeNode) o;

        if (Float.compare(that.count, count) != 0) return false;
        if (changeDate != null ? !changeDate.equals(that.changeDate) : that.changeDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (count != +0.0f ? Float.floatToIntBits(count) : 0);
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        return result;
    }
}
