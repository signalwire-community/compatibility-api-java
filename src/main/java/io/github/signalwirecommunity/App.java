package io.github.signalwirecommunity;

import io.github.signalwirecommunity.enums.MachineDetection;
import io.github.signalwirecommunity.enums.StatusCallBack;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.VoiceBuilder;

public class App {

    public static void main(String[] args) {

        SignalWireClient client = new SignalWireClient("0a35e5c9-9f2f-4e5e-bcb2-cd599b176a26", "PTe32a647b3b9792d550cc20590ada86afa0d421804ec0370d", "olajideawoyinka");

        // Test here
        VoiceBuilder voice = new VoiceBuilder.Builder()
                .to("+2349038279486")
                .url("https://olajideawoyinka.signalwire.com/laml-bins/b439c31d-d27a-44e7-ab08-a7fe70d7b2da")
                .from("+18336372376")
                .machineDetection(MachineDetection.Enable)
                .record(false)
                .build();

        System.out.println(voice.getResponse());

        Call call = client.voice().update("57466289-00c2-4e27-a9ef-d42ebe385fa9", voice.getResponse());
//
        System.out.println(call.getAccount_sid());


    }

}
