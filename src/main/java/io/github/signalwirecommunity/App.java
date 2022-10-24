package io.github.signalwirecommunity;

import io.github.signalwirecommunity.enums.MessageStatus;
import io.github.signalwirecommunity.model.message.*;
import io.github.signalwirecommunity.repository.MessageRepository;

public class App {

    public static void main(String[] args) {
        // test here
        final String PROJECT_ID = "0a35e5c9-9f2f-4e5e-bcb2-cd599b176a26";
        final String API_TOKEN = "PTe32a647b3b9792d550cc20590ada86afa0d421804ec0370d";
        final String SPACE_NAME = "olajideawoyinka";

        SignalWireClient client = new SignalWireClient(PROJECT_ID, API_TOKEN, SPACE_NAME);

        MessageRepository repository = client.message();

        MessageRequest request = new MessageRequest.Builder()
                .body("Testing Java Wrapper library")
                .to("+2348091036281")
                .from("+18336372376")
                .build();

        NewMessageResponse result = repository.create(request);

        System.out.println(result.sid);

    }

}
