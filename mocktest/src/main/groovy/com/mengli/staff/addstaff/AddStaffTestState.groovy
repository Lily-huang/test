package com.mengli.staff.addstaff

import com.github.dreamhead.moco.HttpServer
import com.github.dreamhead.moco.Moco
import com.github.dreamhead.moco.monitor.DefaultRequestHit
import com.mengli.mockapp.dao.DataRepository

/**
 * Created by mlhuang on 8/10/16.
 */
class AddStaffTestState {
    DefaultRequestHit requestHit
    def addRequestXMLBody
    DataRepository dataRepository
    HttpServer httpServer
    def result
    int status

    private final AddStaffHttpHandler addStaffHttpHandler
    private static final int MOCO_PORT=9999
    Map newStaff=[:]

    AddStaffTestState(AddStaffHttpHandler staffHandler,DataRepository repository){
        this.addStaffHttpHandler=staffHandler
        requestHit=new DefaultRequestHit()
        this.dataRepository=repository
        this.dataRepository.clean()
        httpServer= Moco.httpServer(MOCO_PORT,requestHit)
    }

    void sendAddStaffRequest(){
        running(httpServer,new com.github.dreamhead.moco.Runnable(){
            @Override
            void run() throws Exception{
                result=addStaffHttpHandler.addStaff(newStaff)
                status=result[1] as int
            }
        })
    }

    void setupBasicAddStaffRequest(String no,String name,int age){
        newStaff=[data:[name:name,no:no,age:age]]
        newStaff.name="test"
    }

    void verifyXMLSent() {
        addRequestXMLBody = new XmlSlurper().parseText(requestHit.requests.get(0).content.toString()).Body.AddEditStaffRequest
        assert addRequestXMLBody != null
    }

    void createAddStaffRequestResponseMapping(){
        httpServer.request(Moco.by(Moco.uri('/service/staff/addStaff')))
        .response(Moco.header(HEADER_NAME,HEAD_VALUE),
        Moco.with(pageFactory.createResponse()))
    }

}
