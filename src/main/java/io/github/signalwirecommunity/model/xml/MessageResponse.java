package io.github.signalwirecommunity.model.xml;

import io.github.signalwirecommunity.endpoints.MessageBin;
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

public class MessageResponse {

    private String response;

    public MessageResponse(Builder builder) {
        this.response = builder.response;
    }

    public String getResponse() {
        return response;
    }

    public static class Builder implements MessageBin {

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
         * The Message verb sends an SMS or MMS message to a phone number.
         *
         * https://developer.signalwire.com/compatibility-api/reference/message#send-an-image-with-your-message-mms
         * @param messageAttribute xml attribute while creating verb
         * @param text value of the verb
         * @return Builder
         */
        @Override
        public Builder message(HashMap<String, String> messageAttribute, String text) {
            Element messageElement = document.createElement("Message");

            if (messageAttribute != null) {
                for (Map.Entry<String, String> attr : messageAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    messageElement.setAttributeNode(attr1);
                }
            }

            messageElement.appendChild(document.createTextNode(text));

            listOfElement.add(messageElement);

            return this;
        }

        /**
         * dd a picture to the message by specifying a URL with a nested Media noun.
         * The Body noun is optional if you are sending media and you do not want to send text with your media in the message.
         *
         * https://developer.signalwire.com/compatibility-api/reference/message#send-an-image-with-your-message-mms
         * @param messageAttribute xml attribute while creating verb
         * @param body body of the verb
         * @param mediaUrl url to media file
         * @return Builder
         */
        @Override
        public Builder messageWithMedia(HashMap<String, String> messageAttribute, String body, String mediaUrl) {

            Element messageElement = document.createElement("Message");

            Element bodyElement = document.createElement("Body");

            Element mediaElement = document.createElement("Media");


            if (messageAttribute != null) {
                for (Map.Entry<String, String> attr : messageAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    messageElement.setAttributeNode(attr1);
                }
            }

            bodyElement.appendChild(document.createTextNode(body));

            mediaElement.appendChild(document.createTextNode(mediaUrl));

            messageElement.appendChild(bodyElement);

            messageElement.appendChild(mediaElement);

            listOfElement.add(messageElement);

            return this;
        }

        /**
         * The Redirect verb transfers control from the current document to another.
         * It is effectively an exit statement from the current document, as there is no way to return to any instructions listed after the Redirect verb.
         *
         * @param url url to redirect call
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/redirect-1
         */
        @Override
        public Builder redirect(String url) {

            Element redirectElement = document.createElement("Redirect");
            redirectElement.appendChild(document.createTextNode(url));
            listOfElement.add(redirectElement);

            return this;
        }

        public MessageResponse build() {
            try {

                Element root = document.createElement("Response");
                document.appendChild(root);

                for (Element element :
                        listOfElement) {
                    root.appendChild(element);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();

                Transformer transformer = transformerFactory.newTransformer();

                StringWriter writer = new StringWriter();

                DOMSource domSource = new DOMSource(document);

                StreamResult streamResult = new StreamResult(writer);

                transformer.transform(domSource, streamResult);

                response = writer.getBuffer().toString();

                return new MessageResponse(this);

            } catch (TransformerException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
