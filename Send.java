// package com.roman.rmq.publisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws java.io.IOException {

        try {
            // Create a connection
            // Abstracts socket, auth
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();

            // Create a channel
            Channel channel = connection.createChannel();

            //Declare a queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Publish a message to the queue
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
        } catch (TimeoutException tOutEx) {
            tOutEx.printStackTrace(System.err);
        }
    }

}
