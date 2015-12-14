
package leilaoonline.web.full.wsclient;

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
@WebServiceClient(name = "ConsultaLeilaoWS", targetNamespace = "http://ws.mack.com.br/", wsdlLocation = "http://localhost:8080/leilao_online_full/ConsultaLeilaoWS?wsdl")
public class ConsultaLeilaoWS_Service
    extends Service
{

    private final static URL CONSULTALEILAOWS_WSDL_LOCATION;
    private final static WebServiceException CONSULTALEILAOWS_EXCEPTION;
    private final static QName CONSULTALEILAOWS_QNAME = new QName("http://ws.mack.com.br/", "ConsultaLeilaoWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/leilao_online_full/ConsultaLeilaoWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CONSULTALEILAOWS_WSDL_LOCATION = url;
        CONSULTALEILAOWS_EXCEPTION = e;
    }

    public ConsultaLeilaoWS_Service() {
        super(__getWsdlLocation(), CONSULTALEILAOWS_QNAME);
    }

    public ConsultaLeilaoWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CONSULTALEILAOWS_QNAME, features);
    }

    public ConsultaLeilaoWS_Service(URL wsdlLocation) {
        super(wsdlLocation, CONSULTALEILAOWS_QNAME);
    }

    public ConsultaLeilaoWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CONSULTALEILAOWS_QNAME, features);
    }

    public ConsultaLeilaoWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsultaLeilaoWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ConsultaLeilaoWS
     */
    @WebEndpoint(name = "ConsultaLeilaoWSPort")
    public ConsultaLeilaoWS getConsultaLeilaoWSPort() {
        return super.getPort(new QName("http://ws.mack.com.br/", "ConsultaLeilaoWSPort"), ConsultaLeilaoWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConsultaLeilaoWS
     */
    @WebEndpoint(name = "ConsultaLeilaoWSPort")
    public ConsultaLeilaoWS getConsultaLeilaoWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.mack.com.br/", "ConsultaLeilaoWSPort"), ConsultaLeilaoWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CONSULTALEILAOWS_EXCEPTION!= null) {
            throw CONSULTALEILAOWS_EXCEPTION;
        }
        return CONSULTALEILAOWS_WSDL_LOCATION;
    }

}