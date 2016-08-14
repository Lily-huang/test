package com.mengli.staff.addstaff

import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by mlhuang on 8/10/16.
 */

@SuppressWarnings('DuplicatedStringLiteral')
class AddStaffStep {

    @Autowired
    private AddStaffClientFactory addStaffClientFactory

    AddStaffTestState addStaffTestState

    @Before
    void beforeScenario() {
        addStaffTestState = AddStaffTestStateFactory.createStaffState()
    }

    @When('a request is made to add the staff')
    void addStaff() {
        addStaffTestState.createAddStaffRequestResponseMapping()
        addStaffTestState.sendAddStaffRequest()
    }

    @Then('the response status code is \'$status\'')
    void assertResponseStatusCode(int status) {
        assert addStaffTestState.status == status, "The excepted status code is $status,but the actual is ${addStaffTestState.status}"
    }

}
