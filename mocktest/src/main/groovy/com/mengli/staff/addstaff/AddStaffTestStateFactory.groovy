package com.mengli.staff.addstaff

import com.mengli.mockapp.dao.DataRepository
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by mlhuang on 8/10/16.
 */
class AddStaffTestStateFactory {
    @Autowired
    private AddStaffHttpHandler staffHttpHandler

    @Autowired
    private DataRepository dataRepository

    AddStaffTestState createStaffState(){
        new AddStaffTestState(staffHttpHandler,dataRepository)
    }
}
