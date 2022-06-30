package io.github.signalwirecommunity.model.xml;

import io.github.signalwirecommunity.endpoints.FaxBin;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaxResponse {

    private String response;

    public FaxResponse(Builder builder) {
        this.response = builder.response;
    }

    public String getResponse() {
        return response;
    }

    public static class Builder implements FaxBin {

        private String response;

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = null;

        Document document = null;

        List<Element> listOfElement = new ArrayList<>();

        public Builder() {
            try {
                documentBuilder = documentFactory.newDocumentBuilder();
                document = documentBuilder.newDocument();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

        /**
         * The <Receive> verb tells SignalWire to receive an incoming fax, which results in the creation of a new Fax instance resource.
         *
         * @param faxAttribute
         * @return
         * @Link https://developer.signalwire.com/compatibility-api/reference/receive
         */
        @Override
        public Builder receive(HashMap<String, String> faxAttribute) {
            Element receiveElement = document.createElement("Receive");

            if (faxAttribute != null) {
                for (Map.Entry<String, String> attr : faxAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    receiveElement.setAttributeNode(attr1);
                }
            }

            listOfElement.add(receiveElement);

            return this;

        }

        /**
         * The <Reject> verb tells SignalWire to reject an incoming fax, which results in a status of canceled.
         *
         * @return
         * @Link https://developer.signalwire.com/compatibility-api/reference/reject-1
         */
        @Override
        public Builder reject() {
            Element rejectElement = document.createElement("Reject");
            listOfElement.add(rejectElement);
            return this;
        }

        public FaxResponse build() {
            try {

                Element root = document.createElement("Response");
                document.appendChild(root);

                for (Element element : listOfElement) {
                    root.appendChild(element);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();

                Transformer transformer = transformerFactory.newTransformer();

                StringWriter writer = new StringWriter();

                DOMSource domSource = new DOMSource(document);

                StreamResult streamResult = new StreamResult(writer);

                transformer.transform(domSource, streamResult);

                response = writer.getBuffer().toString();

                return new FaxResponse(this);

            } catch (TransformerException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
