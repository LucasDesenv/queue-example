package br.com.queue.example.config;

import br.com.queue.example.receiver.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lusouza on 14/05/18.
 */
@Configuration
public class RabbitConfiguration {

    public static final String QUEUE_NAME = "queue-example";
    public static final String EXCHANGER_NAME = "exchanger-example";

    @Bean
    Queue queue(){
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGER_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("people.#");
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

}
