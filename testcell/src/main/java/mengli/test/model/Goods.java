package mengli.test.model;

/**
 * Created by mengli.huang on 2015/11/19.
 */
public class Goods {
    String name;
    int num;

    public Goods(String name,int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
