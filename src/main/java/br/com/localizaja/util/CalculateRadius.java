package br.com.localizaja.util;

import java.net.URL;
import java.text.Normalizer;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author adriana
 * @see
 * http://wehavescience.com/2012/12/14/distancia-entre-dois-enderecos-usando-java-e-google-directions-api/
 */
public class CalculateRadius {

    public static String calcularDistancia(String origem, String destino) throws Exception {
        String urlDirection
                = "http://maps.google.es/maps/api/directions/xml?origin="
                + removeAcentos(origem) + "&destination=" + removeAcentos(destino)
                + "&sensor=false";
        System.out.println(urlDirection);
        URL url = new URL(urlDirection);

        Document document = getDocumento(url);

        return analisaXml(document);
    }

    public static Double calcularDistanciaDouble(String origem, String destino) throws Exception {
        String distancia = calcularDistancia(origem, destino);
        if (distancia.indexOf(",") > 0) {
            distancia = distancia.substring(0, distancia.indexOf(" ")).trim().replace(",", ".");
        } else {
            distancia = distancia.substring(0, distancia.indexOf(" ")).trim();
        }
        return new Double(distancia);
    }

    @SuppressWarnings("rawtypes")
    public static String analisaXml(Document document) {
        List list = document
                .selectNodes("//DirectionsResponse/route/leg/distance/text");

        Element element = (Element) list.get(list.size() - 1);

        return element.getText();
    }

    public static Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    public static String removeAcentos(String str) {

        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;

    }
}
