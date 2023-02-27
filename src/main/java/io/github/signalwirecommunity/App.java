package io.github.signalwirecommunity;

import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.CallResponse;
import io.github.signalwirecommunity.model.call.VoiceBuilder;
import io.github.signalwirecommunity.model.laml.Bin;
import io.github.signalwirecommunity.model.laml.LamlResponse;
import io.github.signalwirecommunity.model.message.MessageRequest;
import io.github.signalwirecommunity.model.message.NewMessageResponse;
import io.github.signalwirecommunity.model.phone.PhoneResponse;
import io.github.signalwirecommunity.model.rest.SipResponse;
import io.github.signalwirecommunity.model.xml.VoiceResponse;
import io.github.signalwirecommunity.repository.LamlRepository;
import io.github.signalwirecommunity.repository.MessageRepository;

public class App {

    public static void main(String[] args) {
        // test here

        SignalWireClient client = new SignalWireClient("0a35e5c9-9f2f-4e5e-bcb2-cd599b176a26", "PT7be4032f4edd6e271fe47f5e284620cb595660f141ad0cb0", "olajideawoyinka");

        MessageRepository repository = client.message();



//        LamlResponse response = repository.list();
//
//        response.laml_bins.forEach(laml -> {
//            System.out.println(laml.sid + "|" + laml.name);
//        });

        try {

            MessageRequest request = new MessageRequest.Builder()
                    .to("+2348091036281")
                    .from("+18334143320")
                    .body("Testing again!")
                    .build();

           NewMessageResponse response = repository.create(request);
            System.out.println(response.sid);
        } catch (SignalWireException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void testing(int status) throws SignalWireException {
        if (status >= 400) {
            throw new SignalWireException("21211", "status is greater than 400", "Learn more on this code", status);
        }
    }
}
