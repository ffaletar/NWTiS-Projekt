
package org.foi.nwtis.ffaletar.soap.klijenti;

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
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "IoT_Master", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", wsdlLocation = "http://nwtis.foi.hr:8080/DZ3_Master/IoT_Master?wsdl")
public class IoTMaster_Service
    extends Service
{

    private final static URL IOTMASTER_WSDL_LOCATION;
    private final static WebServiceException IOTMASTER_EXCEPTION;
    private final static QName IOTMASTER_QNAME = new QName("http://serveri.ws.dkermek.nwtis.foi.org/", "IoT_Master");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://nwtis.foi.hr:8080/DZ3_Master/IoT_Master?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        IOTMASTER_WSDL_LOCATION = url;
        IOTMASTER_EXCEPTION = e;
    }

    public IoTMaster_Service() {
        super(__getWsdlLocation(), IOTMASTER_QNAME);
    }

    public IoTMaster_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), IOTMASTER_QNAME, features);
    }

    public IoTMaster_Service(URL wsdlLocation) {
        super(wsdlLocation, IOTMASTER_QNAME);
    }

    public IoTMaster_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, IOTMASTER_QNAME, features);
    }

    public IoTMaster_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IoTMaster_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IoTMaster
     */
    @WebEndpoint(name = "IoT_MasterPort")
    public IoTMaster getIoTMasterPort() {
        return super.getPort(new QName("http://serveri.ws.dkermek.nwtis.foi.org/", "IoT_MasterPort"), IoTMaster.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IoTMaster
     */
    @WebEndpoint(name = "IoT_MasterPort")
    public IoTMaster getIoTMasterPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://serveri.ws.dkermek.nwtis.foi.org/", "IoT_MasterPort"), IoTMaster.class, features);
    }

    private static URL __getWsdlLocation() {
        if (IOTMASTER_EXCEPTION!= null) {
            throw IOTMASTER_EXCEPTION;
        }
        return IOTMASTER_WSDL_LOCATION;
    }

}
