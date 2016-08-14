package com.mengli.staff.addstaff

import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Created by mlhuang on 8/10/16.
 */

@Component
class AddStaffClientFactory {
    @Value('${add.api.base.url}')
    String addApiBaseUrl

    @Value('${add.api.user}')
    String addApiUser

    @Value('{add.api.password}')
    String addApiPassword

    RESTClient addStaffClient(){
        RESTClient addApi=new RESTClient(addApiBaseUrl)
        addApi.handler.failure=addApi.handler.success
        addApi.ignoreSSLIssues()
        addApi.auth.basic addApiUser,addApiPassword
        addApi.encoder.'application/token.api+json'=addApi.encoder.'application/json'
        addApi.parser.'application/token.api+json'={response ->
            addApi.parser.parseJSON(response)
        }
        addApi
    }
}
