package com.mengli.parallel.multi;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by mlhuang on 7/8/16.
 */

@RunWith(MultiThreadedRunner.class)
public class MultiTest {
    @Test
    public void test_add(){
        C c=new C();
        assert (c.testAdd(1,2)==3);
    }
}
