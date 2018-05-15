package br.com.queue.example;

import br.com.queue.example.config.RabbitConfiguration;
import br.com.queue.example.receiver.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by lusouza on 14/05/18.
 */
@Component
public class MessageExample implements CommandLineRunner{

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public MessageExample(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGER_NAME, "people.register", "Posting a message to register a person.");
//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
