package mengli.test.externaltools;

import jxl.*;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

public class ExcelData implements Iterator<Object[]> {
    private Workbook book = null;
    private Sheet sheet = null;
    private int rowNum = 0; // 行数
    private int curRowNo = 0; // 当前行数
    private int columnNum = 0; // 列数
    private String[] columnnName; // 列名

    /**
     * 在TestNG中由@DataProvider(dataProvider = "name")修饰的方法读取Excel时，
     * 调用此类的构造方法(此方法会得到列名并将当前行移到下一行)执行完后， 转到TestNG自己的方法中去，然后由它们调用此类实现的
     * hasNext()、next()方法 得到一行数据，使用getAllData得到全部数据，然后返回给由@Test(dataProvider =
     * "name")修饰的方法，如此反复到数据读完为止.
     *
     * @param filepath
     *            Excel文件名
     * @param casename
     *            用例名
     */
    public ExcelData(String filepath, String casename) {
        try {
            File directory = new File(".");
            String ss = "case.";
            //mac"/"与windows"\\"运行有区别
            book = Workbook.getWorkbook(new File(directory.getCanonicalPath()
                    + "/data/"
                    + ss.replaceAll("\\.", Matcher.quoteReplacement("/"))
                    + filepath + ".xls"));
            this.sheet = book.getSheet(casename);
            this.rowNum = sheet.getRows(); // 得到行数

            Cell[] c = sheet.getRow(0);
            this.columnNum = c.length; // 得到列数
            columnnName = new String[c.length];
            for (int i = 0; i < c.length; i++) {
                columnnName[i] = c[i].getContents().toString();
            }
            this.curRowNo++; // 当前行数

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("unable to read Excel data");
        }
    }

    public boolean hasNext() {
        /**
         * 方法功能：是否有下一条数据. 如果行数为0即空sheet 或者 当前行数大于总行数
         * 就关闭对excel的操作返回false，否则返回true.
         */
        if (this.rowNum == 0 || this.curRowNo >= this.rowNum) {
            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else
            return true;
    }

    public Object[] next() {
        /**
         * 方法功能：得到并返回下一行数据. 使用for将一行的数据放入Object[]并返回，且将curRowNo当前行下移.
         */
        Cell[] c = sheet.getRow(this.curRowNo);
        Object[] col = new Object[columnNum];
        for (int i = 0; i < this.columnNum; i++) {
            if (c.length > i) {
                col[i] = c[i].getContents().toString();
            } else {
                col[i] = "";
            }
        }
        this.curRowNo++;
        return col;
    }

    /**
     * 方法功能：返回全部数据数据.
     */
    public Object[][] getAllData() {
        List<Object[]> rr = new ArrayList<Object[]>();
        while (hasNext()) {
            rr.add(next());
        }
        Object[][] oss = new Object[rr.size()][rr.get(0).length];
        for (int i = 0; i < rr.size(); i++)
            for (int j = 0; j < rr.get(0).length; j++) {
                oss[i][j] =rr.get(i)[j];
            }

        return oss;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }
}
