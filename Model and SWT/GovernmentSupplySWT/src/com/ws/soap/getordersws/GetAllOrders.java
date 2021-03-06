
package com.ws.soap.getordersws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GetAllOrders", targetNamespace = "http://getOrdersWs.soap.ws.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GetAllOrders {


    /**
     * 
     * @return
     *     returns java.util.List<com.ws.soap.getordersws.Order>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllOrders", targetNamespace = "http://getOrdersWs.soap.ws.com/", className = "com.ws.soap.getordersws.GetAllOrders_Type")
    @ResponseWrapper(localName = "getAllOrdersResponse", targetNamespace = "http://getOrdersWs.soap.ws.com/", className = "com.ws.soap.getordersws.GetAllOrdersResponse")
    @Action(input = "http://getOrdersWs.soap.ws.com/GetAllOrders/getAllOrdersRequest", output = "http://getOrdersWs.soap.ws.com/GetAllOrders/getAllOrdersResponse")
    public List<Order> getAllOrders();

}
