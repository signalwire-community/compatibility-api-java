package io.github.signalwirecommunity.model.xml;

import io.github.signalwirecommunity.endpoints.VoiceBin;
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

public class VoiceResponse {

    private String response;

    public VoiceResponse(Builder builder) {
        this.response = builder.response;
    }

    public String getResponse() {
        return response;
    }

    /**
     *
     */
    public static class Builder implements VoiceBin {

        private String response;
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = null;

        Document document = null;

        public Builder() {
            try {
                documentBuilder = documentFactory.newDocumentBuilder();
                document = documentBuilder.newDocument();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }


        List<Element> listOfElement = new ArrayList<>();

        /**
         * The Denoise verb enables or disables noise reduction for call audio inbound to signalwire.
         * It reduces noise on calls before dailing into a conference or forwarding to another number.
         *
         * @param type The type of noise 1 for "on" and 2 for "off"
         * @return Builder
         */
        @Override
        public Builder deniose(int type) {

            String selected = "";
            if (type == 1) {
                selected = "on";
            } else {
                selected = "off";
            }


            Element denoise = document.createElement("Denoise");
            denoise.appendChild(document.createTextNode(selected));
            listOfElement.add(denoise);

            return this;
        }

        /**
         * The Dial verb connects an existing call to another phone number.
         * Dial will end this new call if: the called number does not answer, the number does not exist, or SignalWire receives a busy signal.
         *
         * @param dialAttribute additional attribute needed to create a verb
         * @param phone value of the phone number
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/dial
         */
        @Override
        public Builder dial(HashMap<String, String> dialAttribute, String phone) {
            Element dialElement = document.createElement("Dial");
            dialElement.appendChild(document.createTextNode(phone));

            if (dialAttribute != null) {
                for (Map.Entry<String, String> attr : dialAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    dialElement.setAttributeNode(attr1);
                }
            }


            listOfElement.add(dialElement);
            return this;
        }

        /**
         * Dial verb's Number noun specifies what phone number to dial. You can use up to 10 Number's within a Dial to simultaneously call several people.
         * The first person to answer the call will be connected to the caller and the rest of the called numbers will be hung up.
         *
         * @param dialAttribute additional attribute needed while creating the dial tag
         * @param phoneNumber phone number (E164 format)
         * @param numberAttribute additional attribute needed while creating the number tag
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/number-noun
         */
        @Override
        public Builder dialWithNumberBin(HashMap<String, String> dialAttribute, String phoneNumber, HashMap<String, String> numberAttribute) {

            Element root = document.createElement("Dial");

            Element number = document.createElement("Number");


            if (dialAttribute != null) {
                for (Map.Entry<String, String> attr : dialAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    root.setAttributeNode(attr1);
                }
            }

            if (numberAttribute != null) {
                for (Map.Entry<String, String> attr : numberAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    number.setAttributeNode(attr1);
                }
            }

            number.appendChild(document.createTextNode(phoneNumber));
            root.appendChild(number);

            listOfElement.add(root);

            return this;
        }

        /**
         * Using the Number bin to dial multiple numbers
         *
         * @param dialAttribute additional attribute needed while creating the dial tag
         * @param phoneNumber phone number (E164 format)
         * @return Builder
         */
        @Override
        public Builder dialWithNumberBin(HashMap<String, String> dialAttribute, List<String> phoneNumber) {
            Element root = document.createElement("Dial");


            if (dialAttribute != null) {
                for (Map.Entry<String, String> attr : dialAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    root.setAttributeNode(attr1);
                }
            }

            for (String phone : phoneNumber) {
                Element phoneElement = document.createElement("Number");
                phoneElement.appendChild(document.createTextNode(phone));
                root.appendChild(phoneElement);
            }

            listOfElement.add(root);

            return this;
        }

        /**
         * Dial verb's Sip noun permits the set up of VoIP sessions using SIP (Session Initiation Protocol). You can send a call to any SIP endpoint.
         *
         * @param dialAttribute additional attribute needed while creating the dial tag
         * @param destination destination phone number (E164 format)
         * @param sipAttribute additional attribute needed while creating the sip tag
         * @return Builder
         * for more information https://developer.signalwire.com/compatibility-api/reference/sip-noun
         */
        @Override
        public Builder dialWithSIPBin(HashMap<String, String> dialAttribute, String destination, HashMap<String, String> sipAttribute) {

            Element root = document.createElement("Dial");

            Element sipElement = document.createElement("Sip");


            if (dialAttribute != null) {
                for (Map.Entry<String, String> attr : dialAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    root.setAttributeNode(attr1);
                }
            }

            if (sipAttribute != null) {
                for (Map.Entry<String, String> attr : sipAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    sipElement.setAttributeNode(attr1);
                }
            }

            sipElement.appendChild(document.createTextNode(destination));
            root.appendChild(sipElement);

            listOfElement.add(root);

            return this;

        }

        /**
         * Dial verb's Queue noun specifies what queue to dial
         *
         * @param dialAttribute additional attribute needed while creating the dial tag
         * @param text value of text sent along with the Queue tag
         * @param queueAttribute additional attribute needed while creating the queue tag
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/queue-noun
         */

        @Override
        public Builder dialWithQueueBin(HashMap<String, String> dialAttribute, String text, HashMap<String, String> queueAttribute) {

            Element root = document.createElement("Dial");

            Element queueElement = document.createElement("Queue");


            if (dialAttribute != null) {
                for (Map.Entry<String, String> attr : dialAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    root.setAttributeNode(attr1);
                }
            }

            if (queueAttribute != null) {
                for (Map.Entry<String, String> attr : queueAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    queueElement.setAttributeNode(attr1);
                }
            }

            queueElement.appendChild(document.createTextNode(text));
            root.appendChild(queueElement);

            listOfElement.add(root);

            return this;
        }

        /**
         * Dial verb's Conference noun allows the connection to a named conference room.
         *
         * @param dialAttribute additional attribute needed while creating the dial tag
         * @param text value of text sent along with the Queue tag
         * @param conferenceAttribute additional attribute needed while creating the queue tag
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/conference-noun
         */
        @Override
        public Builder dialWithConferenceBin(HashMap<String, String> dialAttribute, String text, HashMap<String, String> conferenceAttribute) {

            Element root = document.createElement("Dial");

            Element conferenceElement = document.createElement("Conference");


            if (dialAttribute != null) {
                for (Map.Entry<String, String> attr : dialAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    root.setAttributeNode(attr1);
                }
            }

            if (conferenceAttribute != null) {
                for (Map.Entry<String, String> attr : conferenceAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    conferenceElement.setAttributeNode(attr1);
                }
            }

            conferenceElement.appendChild(document.createTextNode(text));
            root.appendChild(conferenceElement);

            listOfElement.add(root);

            return this;
        }


        @Override
        public Builder echo(String timeout) {

            Element echoElement = document.createElement("Echo");

            if (timeout != null) {
                Attr attr1 = document.createAttribute("timeout");
                attr1.setValue(timeout);
                echoElement.setAttributeNode(attr1);
            }

            listOfElement.add(echoElement);

            return null;
        }

        /**
         * The Enqueue verb places a call in a specified call queue.
         * If the specified queue does not exist, a new queue will be created and the call will be placed into that new queue.
         *
         * @param enqueueAttr additional attribute needed while creating the enqueue tag
         * @param text value to be added in the Enqueue tag
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/enqueue
         */
        @Override
        public Builder enqueue(HashMap<String, String> enqueueAttr, String text) {

            Element enqueueElement = document.createElement("Enqueue");

            if (enqueueAttr != null) {
                for (Map.Entry<String, String> attr : enqueueAttr.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    enqueueElement.setAttributeNode(attr1);
                }
            }

            enqueueElement.appendChild(document.createTextNode(text));

            listOfElement.add(enqueueElement);

            return this;
        }

        @Override
        public Builder gatherWithSayBin(HashMap<String, String> gatherAttr, String text) {

            Element gatherElement = document.createElement("Gather");

            Element sayElement = document.createElement("Say");

            sayElement.appendChild(document.createTextNode(text));

            if (gatherAttr != null) {
                for (Map.Entry<String, String> attr : gatherAttr.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    gatherElement.setAttributeNode(attr1);
                }
            }

            gatherElement.appendChild(sayElement);

            listOfElement.add(gatherElement);

            return this;
        }

        @Override
        public Builder gatherWithPlayBin(HashMap<String, String> gatherAttr, String url) {
            Element gatherElement = document.createElement("Gather");

            Element sayElement = document.createElement("Play");

            sayElement.appendChild(document.createTextNode(url));

            if (gatherAttr != null) {
                for (Map.Entry<String, String> attr : gatherAttr.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    gatherElement.setAttributeNode(attr1);
                }
            }

            gatherElement.appendChild(sayElement);

            listOfElement.add(gatherElement);

            return this;
        }


        /**
         * The Hangup verb ends a call.
         *
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/hangup
         */
        @Override
        public Builder hangup() {

            Element hangup = document.createElement("Hangup");

            listOfElement.add(hangup);

            return this;
        }

        /**
         * The Leave verb transfers a call out of the queue containing that call.
         *
         * @return Builder
         * https://developer.signalwire.com/compatibility-api/reference/leave
         */
        @Override
        public Builder leave() {
            Element hangup = document.createElement("Leave");

            listOfElement.add(hangup);
            return this;
        }

        /**
         * The Pause verb waits silently for a distinctive number of seconds.
         *
         * https://developer.signalwire.com/compatibility-api/reference/pause
         * @param length length of the pause
         * @return Builder
         */
        @Override
        public Builder pause(String length) {

            Element hangup = document.createElement("Pause");

            Attr attr = document.createAttribute("length");
            attr.setValue(length);

            hangup.setAttributeNode(attr);

            listOfElement.add(hangup);

            return this;
        }

        /**
         * The Play verb plays an audio file, which SignalWire fetches from the URL you configured, back to the caller.
         *
         * https://developer.signalwire.com/compatibility-api/reference/play
         * @param playAttr additional attribute needed while creating the play tag
         * @param url value representing the url to be played
         * @return Builder
         */
        @Override
        public Builder play(HashMap<String, String> playAttr, String url) {

            Element play = document.createElement("Play");

            if (playAttr != null) {
                for (Map.Entry<String, String> attr : playAttr.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    play.setAttributeNode(attr1);
                }
            }

            play.appendChild(document.createTextNode(url));

            listOfElement.add(play);

            return this;
        }


        /**
         * The Record verb creates an audio file with the caller's voice and returns the URL to you.
         * Text transcriptions of these recorded calls can also be produced.
         *
         * https://developer.signalwire.com/compatibility-api/reference/record
         * @return Builder
         */
        @Override
        public Builder record(HashMap<String, String> recordAttribute) {
            Element record = document.createElement("Record");

            if (recordAttribute != null) {
                for (Map.Entry<String, String> attr : recordAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    record.setAttributeNode(attr1);
                }
            }

            listOfElement.add(record);
            return this;
        }

        /**
         * The Redirect verb transfers control from the current call to another.
         * It is effectively an exit statement from the current call, as there is no way to return to any instructions listed after the verb.
         *
         * https://developer.signalwire.com/compatibility-api/reference/redirect
         * @param url Link to call to beb redirected to
         * @return Builder
         */
        @Override
        public Builder redirect(String url) {
            Element redirectElement = document.createElement("Redirect");
            redirectElement.appendChild(document.createTextNode(url));
            listOfElement.add(redirectElement);
            return this;
        }

        /**
         * The Refer verb transfers a SIP call in SignalWire to a transfer target using the SIP REFER method.
         *
         * https://developer.signalwire.com/compatibility-api/reference/refer#examples
         * @param referAttribute additional attribute needed while creating the refer tag
         * @param sipAddress value of the Sip address
         * @return Builder
         */
        @Override
        public Builder refer(HashMap<String, String > referAttribute, String sipAddress) {
            Element referElement = document.createElement("Refer");
            Element sipElement = document.createElement("Sip");

            sipElement.appendChild(document.createTextNode(sipAddress));

            if (referAttribute != null) {
                for (Map.Entry<String, String> attr : referAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    referElement.setAttributeNode(attr1);
                }
            }

            referElement.appendChild(sipElement);
            listOfElement.add(referElement);
            return this;
        }

        /**
         * The Reject verb rejects a call to your SignalWire number.
         * It is effectively an exit statement from the current document, as there is no way to return to any instructions listed after the Reject verb.
         *
         * https://developer.signalwire.com/compatibility-api/reference/reject
         * @param reason Value to the reason to reject the call
         * @return Builder
         */
        @Override
        public Builder reject(String reason) {

            Element reject = document.createElement("Reject");

            if (reason != null){
                Attr attr = document.createAttribute("reason");
                attr.setValue(reason);
                reject.setAttributeNode(attr);
            }

            listOfElement.add(reject);

            return this;
        }

        /**
         * The Say verb reads the supplied text back to the caller. It is useful for text that is difficult to pre-record.
         *
         * https://developer.signalwire.com/compatibility-api/reference/say
         * @param sayAttribute additional attribute needed while creating the say tag
         * @param text value of content to say
         * @return Builder
         */
        @Override
        public Builder say(HashMap<String, String> sayAttribute, String text) {

            Element sayElement = document.createElement("Say");

            if (sayAttribute != null) {
                for (Map.Entry<String, String> attr : sayAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    sayElement.setAttributeNode(attr1);
                }
            }

            sayElement.appendChild(document.createTextNode(text));

            listOfElement.add(sayElement);
            return this;
        }

        /**
         * The Sms verb sends an SMS message to a phone number during a phone call.
         *
         * https://developer.signalwire.com/compatibility-api/reference/sms
         * @param smsAttribute additional attribute needed while creating the sms tag
         * @param text Sms content to be sent to destination
         * @return Builder
         */
        @Override
        public Builder sms(HashMap<String, String> smsAttribute, String text) {

            Element smsElement = document.createElement("Sms");

            if (smsAttribute != null) {
                for (Map.Entry<String, String> attr : smsAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    smsElement.setAttributeNode(attr1);
                }
            }

            smsElement.appendChild(document.createTextNode(text));

            listOfElement.add(smsElement);
            return this;
        }

        /**
         * The Stream instruction makes it possible to send raw audio streams from a running phone call over WebSockets in near real time, to a specified URL
         *
         * https://developer.signalwire.com/compatibility-api/reference/stream#websocket-messages
         * @param streamAttribute additional attribute needed while creating the stream tag
         * @return Builder
         */
        @Override
        public Builder stream(HashMap<String, String> streamAttribute) {

            Element startElement = document.createElement("Start");
            Element streamElement = document.createElement("Stream");

            if (streamAttribute != null) {
                for (Map.Entry<String, String> attr : streamAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    streamElement.setAttributeNode(attr1);
                }
            }
            startElement.appendChild(streamElement);

            listOfElement.add(startElement);

            return this;
        }

        /**
         * A stop message will be sent when the Stream is either stopped or the Call has ended.
         *
         * https://developer.signalwire.com/compatibility-api/reference/stream#stopping-a-stream
         * @param streamAttribute additional attribute needed while creating the stop stream tag
         * @return Builder
         */
        @Override
        public Builder stopStream(HashMap<String, String> streamAttribute) {

            Element stopElement = document.createElement("Stop");
            Element streamElement = document.createElement("Stream");

            if (streamAttribute != null) {
                for (Map.Entry<String, String> attr : streamAttribute.entrySet()) {
                    String key = attr.getKey();
                    String value = attr.getValue();

                    Attr attr1 = document.createAttribute(key);
                    attr1.setValue(value);
                    streamElement.setAttributeNode(attr1);
                }
            }
            stopElement.appendChild(streamElement);

            listOfElement.add(stopElement);

            return this;
        }

        public VoiceResponse build() {
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

                return new VoiceResponse(this);

            } catch (TransformerException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
