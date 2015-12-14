
package leilaoonline.web.full.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the leilaoonline.web.full.wsclient package. 
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

    private final static QName _ObterLeiloesPorNomeCategoria_QNAME = new QName("http://ws.mack.com.br/", "obterLeiloesPorNomeCategoria");
    private final static QName _ObterLeiloesPorNomeCategoriaResponse_QNAME = new QName("http://ws.mack.com.br/", "obterLeiloesPorNomeCategoriaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: leilaoonline.web.full.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObterLeiloesPorNomeCategoriaResponse }
     * 
     */
    public ObterLeiloesPorNomeCategoriaResponse createObterLeiloesPorNomeCategoriaResponse() {
        return new ObterLeiloesPorNomeCategoriaResponse();
    }

    /**
     * Create an instance of {@link ObterLeiloesPorNomeCategoria }
     * 
     */
    public ObterLeiloesPorNomeCategoria createObterLeiloesPorNomeCategoria() {
        return new ObterLeiloesPorNomeCategoria();
    }

    /**
     * Create an instance of {@link LeilaoVO }
     * 
     */
    public LeilaoVO createLeilaoVO() {
        return new LeilaoVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterLeiloesPorNomeCategoria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.mack.com.br/", name = "obterLeiloesPorNomeCategoria")
    public JAXBElement<ObterLeiloesPorNomeCategoria> createObterLeiloesPorNomeCategoria(ObterLeiloesPorNomeCategoria value) {
        return new JAXBElement<ObterLeiloesPorNomeCategoria>(_ObterLeiloesPorNomeCategoria_QNAME, ObterLeiloesPorNomeCategoria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterLeiloesPorNomeCategoriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.mack.com.br/", name = "obterLeiloesPorNomeCategoriaResponse")
    public JAXBElement<ObterLeiloesPorNomeCategoriaResponse> createObterLeiloesPorNomeCategoriaResponse(ObterLeiloesPorNomeCategoriaResponse value) {
        return new JAXBElement<ObterLeiloesPorNomeCategoriaResponse>(_ObterLeiloesPorNomeCategoriaResponse_QNAME, ObterLeiloesPorNomeCategoriaResponse.class, null, value);
    }

}
