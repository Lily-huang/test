package com.mengli.parallel.single;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Created by mlhuang on 7/8/16.
 */
public class ParallelTest {
    public static void main(String[] args) {
        Class[] cls = { A.class, B.class };

        Result rt;

        // 并发以类为维度
        // rt = JUnitCore.runClasses(ParallelComputer.classes(), cls);

        // 并发以方法为维度
        // rt = JUnitCore.runClasses(ParallelComputer.methods(), cls);

        // 并发以类和方法为维度
        rt = JUnitCore.runClasses(new ParallelComputer(true, true), cls);

        System.out.println(rt.getRunCount() + " " + rt.getFailures()  + " " + rt.getRunTime());
    }
}
