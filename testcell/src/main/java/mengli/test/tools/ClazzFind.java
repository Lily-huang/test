package mengli.test.tools;

import mengli.test.externaltools.DiagnoseLogger;

/**
 * Created by mengli.huang on 2015/11/21.
 */
public class ClazzFind {
    protected static final DiagnoseLogger diagnose = DiagnoseLogger.getInstance();
    public static Object getClazz(String clazzName){
        Object obj=new Object();
        try {
            Class clazz = Class.forName(clazzName);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            diagnose.error("clazz "+clazzName+" not found");
        } catch (IllegalAccessException e) {
            diagnose.error("clazz " + clazzName + " not illegal");
        } catch (InstantiationException e) {
            diagnose.error("clazz " + clazzName + " not instance");
        }
        return obj;
    }
}
