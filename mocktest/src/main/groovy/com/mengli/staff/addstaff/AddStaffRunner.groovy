package com.mengli.staff.addstaff

import com.mengli.mockapp.runner.AdhocStoryRunner


/**
 * Created by mlhuang on 8/10/16.
 */


class AddStaffRunner extends AdhocStoryRunner{
    @Override
    String getFeatureName(){
        'biz.addstaff'
    }

    @Override
    List<String> getStoriesToInclude(){
        ['stories/staff/addstaff/*.story']
    }

}
