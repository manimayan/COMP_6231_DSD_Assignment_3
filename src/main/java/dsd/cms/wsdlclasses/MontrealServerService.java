
package dsd.cms.wsdlclasses;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MontrealServerService", targetNamespace = "http://server.cms.dsd/", wsdlLocation = "http://localhost:8080/DCMS/MTL?wsdl")
public class MontrealServerService
    extends Service
{

    private final static URL MONTREALSERVERSERVICE_WSDL_LOCATION;
    private final static WebServiceException MONTREALSERVERSERVICE_EXCEPTION;
    private final static QName MONTREALSERVERSERVICE_QNAME = new QName("http://server.cms.dsd/", "MontrealServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/DCMS/MTL?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MONTREALSERVERSERVICE_WSDL_LOCATION = url;
        MONTREALSERVERSERVICE_EXCEPTION = e;
    }

    public MontrealServerService() {
        super(__getWsdlLocation(), MONTREALSERVERSERVICE_QNAME);
    }

    public MontrealServerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MONTREALSERVERSERVICE_QNAME, features);
    }

    public MontrealServerService(URL wsdlLocation) {
        super(wsdlLocation, MONTREALSERVERSERVICE_QNAME);
    }

    public MontrealServerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MONTREALSERVERSERVICE_QNAME, features);
    }

    public MontrealServerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MontrealServerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DCMSInterface
     */
    @WebEndpoint(name = "MontrealServerPort")
    public DCMSInterface getMontrealServerPort() {
        return super.getPort(new QName("http://server.cms.dsd/", "MontrealServerPort"), DCMSInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DCMSInterface
     */
    @WebEndpoint(name = "MontrealServerPort")
    public DCMSInterface getMontrealServerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.cms.dsd/", "MontrealServerPort"), DCMSInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MONTREALSERVERSERVICE_EXCEPTION!= null) {
            throw MONTREALSERVERSERVICE_EXCEPTION;
        }
        return MONTREALSERVERSERVICE_WSDL_LOCATION;
    }

}