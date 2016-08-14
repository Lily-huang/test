package com.mengli.staff.addstaff

import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by mlhuang on 8/10/16.
 */

@Component
class AddStaffHttpHandler {
    private static final String CONTEN_TYPE = 'application/token.api+json'

    @Autowired
    AddStaffClientFactory clientFactory

    def addStaff(Map jsonBody) {
        RESTClient staffClient = clientFactory.addStaffClient()
        def name = jsonBody.data.attributes.name
        def response = staffClient.post(
                [path             : "add/info//",
                 body             : jsonBody,
                 requestContenType: CONTEN_TYPE]
        )
        [response.data, response.status, httpHeaderTranslator(response.allHeafers)]
    }

    private httpHeaderTranslator(header) {
        Map<String, String> result = [:]
        header.each {
            result.put(it.name, it.value)
        }
        result
    }
}
