package com.frame.api.transform;

import com.frame.api.utils.ClazzFind;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by mlhuang on 8/17/16.
 */

@Component
public class SearchStaffTrans {

    @Value("${search.staff.url}")
    private String url;

    @Value("${search.staff.in}")
    private String input;

    @Value("${search.staff.out}")
    private String output;

    DataModel getDataModel(){
        Object obj = ClazzFind.getClazz(input);
        return (DataModel)obj;
    }

}
