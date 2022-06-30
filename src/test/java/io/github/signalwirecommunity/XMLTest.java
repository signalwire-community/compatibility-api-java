package io.github.signalwirecommunity;

import io.github.signalwirecommunity.model.xml.VoiceResponse;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class XMLTest {

    @Test
    public void getAllXMLBinsTest() {
//        LamlResponse expected = AppTest.getClient().xml().list();
//        String actual = "Enter-account-sid";
//        assertThat(expected.laml_bins.get(0).account_sid, equalTo(actual));
    }

    @Test
    public void getXMLBinByNameTest() {
//        LamlResponse expected = AppTest.getClient().xml().list("friendly-name-of-bin");
//        String actual = "friendly-name-of-bin";
//        assertThat(expected.laml_bins.get(0).name, equalTo(actual));
    }

    @Test
    public void createNewBinTest() {
        VoiceResponse response = new VoiceResponse.Builder()
                .say(null, "Hello world")
                .build();

//        String bin = response.getResponse();
//        String actual = "friendly-name-of-bin";
//        Bin expected = AppTest.getClient().xml().create("friendly-name-of-bin", bin);
//        assertThat(expected.name, equalTo(actual));

    }

    @Test
    public void getXMLBySIDTest(){
//        Bin expected = AppTest.getClient().xml().get("xml-sid");
//        String actual = "xml-friendly-name";
//        assertThat(expected.name, equalTo(actual));
    }

    @Test
    public void updateXMLBySIDTest(){
        VoiceResponse response = new VoiceResponse.Builder()
                .say(null, "You are welcome to SW")
                .dial(null, "+2348091036281")
                .build();

//        String content = response.getResponse();
//        String sid = "xml-sid";
//        Bin expected = AppTest.getClient().xml().update(sid, "name-of-xml", content);
//        String actual = "sid-of-the-xml";
//
//        assertThat(expected.sid, equalTo(actual));
    }

    @Test
    public void deleteXMLBySidTest(){
//        String sid = "SID for the xml";
//        SuccessResponse expected  = AppTest.getClient().xml().delete(sid);
//        assertThat(expected.getSuccess().toString(), equalTo("true"));
    }

}
