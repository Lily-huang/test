package mengli.test.rent;

import junit.framework.Assert;
import mengli.test.externaltools.ExcelData;
import mengli.test.model.ChangeNode;
import mengli.test.tools.ClazzFind;
import mengli.test.externaltools.DiagnoseLogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mengli.huang on 2015/11/22.
 */
public class ChangeTest {
    protected static final DiagnoseLogger diagnose = DiagnoseLogger.getInstance();
    private static final String TEST_DEMO_PATH = "mengli.test.test.";

    @BeforeClass
    public void doBeforeClass() throws Exception {
        diagnose.info("------------------one test started-------------------");
    }

    @Test(dataProvider = "rentdata")
    public void doTest(String type, String method, String userId,String start, String changeDate,String now,String to, String changeCount, String changeEnd) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj = ClazzFind.getClazz(TEST_DEMO_PATH + type);
        ChangeNode changeNode=new ChangeNode(Float.parseFloat(changeCount),changeEnd);
        Method methodTest;
        Object arguments1;
        Object arguments2;
        Object arguments3;
        ChangeNode changed=new ChangeNode();
        if (method.equals("getStopFee")) {
            arguments1 = (String) userId;
            arguments2 = (String) start;
            arguments3 = (String) changeDate;
            methodTest=obj.getClass().newInstance().getClass().getMethod(method,String.class,String.class,String.class);
            changed = (ChangeNode) methodTest.invoke(obj, arguments1,arguments2,arguments3);
        } else if (method.equals("getChangeFee")) {
            arguments1 = (String) now;
            arguments2 = (String) to;
            methodTest=obj.getClass().newInstance().getClass().getMethod(method,String.class,String.class);
            changed=(ChangeNode)methodTest.invoke(obj,arguments1,arguments2);
        }
        if (changed.equals(changeNode)) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @AfterClass(alwaysRun = true)
    public void doAfterClass() {
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        diagnose.info("----------------test end----------------");
    }

    @DataProvider(name = "rentdata")
    public Object[][] data() {
        ExcelData allDate = new ExcelData("dvd_rent", "dvd_change");
        Object[][] items = allDate.getAllData();
        return items;
    }
}
