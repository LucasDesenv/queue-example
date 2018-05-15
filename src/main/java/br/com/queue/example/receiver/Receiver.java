package br.com.queue.example.receiver;

import org.springframework.stereotype.Component;

/**
 * Created by lusouza on 14/05/18.
 */
@Component
public class Receiver {

    public void receiveMessage(String message){
        System.out.println(String.format("Message received: %s", message));
    }
}
