package mengli.test.rent;

import junit.framework.Assert;
import mengli.test.externaltools.ExcelData;
import mengli.test.model.BillInput;
import mengli.test.model.BillNode;
import mengli.test.model.BillResult;
import mengli.test.tools.ClazzFind;
import mengli.test.externaltools.DiagnoseLogger;
import mengli.test.tools.Transform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mengli.huang on 2015/11/21.
 */

@Test(groups = {"dvd", "dvd_rent"})
public class RentTest {
    protected static final DiagnoseLogger diagnose = DiagnoseLogger.getInstance();
    private static final String TEST_DEMO_PATH="mengli.test.test.";
    @BeforeClass
    public void doBeforeClass() throws Exception {
        diagnose.info("------------------one test started-------------------");
    }

    @Test(dataProvider = "rentdata")
    public void doTest(String type, String userId,String curTimes, String num1,String num2,String num3,String num4,String start, String end,String out, String back, String basicDate,String basic,String curDate,String cur,String nextDate,String next,String increasedTime) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Transform transform=new Transform();
        BillInput billInput=transform.getInput(type,userId,curTimes,start,out,back,num1,num2,num3,num4);
        BillResult billResult=transform.getResult(start,end,new BillNode(basicDate,Integer.parseInt(basic)),new BillNode(curDate,Integer.parseInt(cur)),new BillNode(nextDate,Integer.parseInt(next)),increasedTime);
        Object obj= ClazzFind.getClazz(TEST_DEMO_PATH + type);
        Method methodTest=obj.getClass().newInstance().getClass().getMethod("getBill",BillInput.class);
        Object arguments = (BillInput)billInput;
        BillResult result=(BillResult)methodTest.invoke(obj,arguments);
        if (result.equals(billResult)) {
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
        ExcelData allDate = new ExcelData("dvd_rent", "dvd_rent");
        Object[][] items = allDate.getAllData();
        return items;
    }
}
