
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
@WebServiceClient(name = "LavalServerService", targetNamespace = "http://server.cms.dsd/", wsdlLocation = "http://localhost:8080/DCMS/LVL?wsdl")
public class LavalServerService
    extends Service
{

    private final static URL LAVALSERVERSERVICE_WSDL_LOCATION;
    private final static WebServiceException LAVALSERVERSERVICE_EXCEPTION;
    private final static QName LAVALSERVERSERVICE_QNAME = new QName("http://server.cms.dsd/", "LavalServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/DCMS/LVL?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LAVALSERVERSERVICE_WSDL_LOCATION = url;
        LAVALSERVERSERVICE_EXCEPTION = e;
    }

    public LavalServerService() {
        super(__getWsdlLocation(), LAVALSERVERSERVICE_QNAME);
    }

    public LavalServerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LAVALSERVERSERVICE_QNAME, features);
    }

    public LavalServerService(URL wsdlLocation) {
        super(wsdlLocation, LAVALSERVERSERVICE_QNAME);
    }

    public LavalServerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LAVALSERVERSERVICE_QNAME, features);
    }

    public LavalServerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LavalServerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DCMSInterface
     */
    @WebEndpoint(name = "LavalServerPort")
    public DCMSInterface getLavalServerPort() {
        return super.getPort(new QName("http://server.cms.dsd/", "LavalServerPort"), DCMSInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DCMSInterface
     */
    @WebEndpoint(name = "LavalServerPort")
    public DCMSInterface getLavalServerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.cms.dsd/", "LavalServerPort"), DCMSInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LAVALSERVERSERVICE_EXCEPTION!= null) {
            throw LAVALSERVERSERVICE_EXCEPTION;
        }
        return LAVALSERVERSERVICE_WSDL_LOCATION;
    }

}
