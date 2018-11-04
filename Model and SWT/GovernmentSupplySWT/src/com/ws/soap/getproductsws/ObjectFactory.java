
package com.ws.soap.getproductsws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ws.soap.getproductsws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllProductsResponse_QNAME = new QName("http://getProductsWs.soap.ws.com/", "getAllProductsResponse");
    private final static QName _GetAllProducts_QNAME = new QName("http://getProductsWs.soap.ws.com/", "getAllProducts");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ws.soap.getproductsws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllProducts_Type }
     * 
     */
    public GetAllProducts_Type createGetAllProducts_Type() {
        return new GetAllProducts_Type();
    }

    /**
     * Create an instance of {@link GetAllProductsResponse }
     * 
     */
    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link Section }
     * 
     */
    public Section createSection() {
        return new Section();
    }

    /**
     * Create an instance of {@link Department }
     * 
     */
    public Department createDepartment() {
        return new Department();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://getProductsWs.soap.ws.com/", name = "getAllProductsResponse")
    public JAXBElement<GetAllProductsResponse> createGetAllProductsResponse(GetAllProductsResponse value) {
        return new JAXBElement<GetAllProductsResponse>(_GetAllProductsResponse_QNAME, GetAllProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProducts_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://getProductsWs.soap.ws.com/", name = "getAllProducts")
    public JAXBElement<GetAllProducts_Type> createGetAllProducts(GetAllProducts_Type value) {
        return new JAXBElement<GetAllProducts_Type>(_GetAllProducts_QNAME, GetAllProducts_Type.class, null, value);
    }

}
