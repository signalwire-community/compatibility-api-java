package io.github.signalwirecommunity.exceptions;

import com.google.gson.Gson;
import io.github.signalwirecommunity.model.BadRequest;

public class BadRequestException extends Exception{

    private BadRequest response;
    private Gson gson;

    public BadRequest getResponse() {
        return response;
    }

    public BadRequestException(){
        super();
    }

    public BadRequestException(String response){
        gson= new Gson();
        this.response = gson.fromJson(response, BadRequest.class);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public String getMessage() {
        return this.response.getMessage();
    }
}
