package com.rajeev.iot.publisher;

import com.rajeev.iot.utils.MqttConstants;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Scanner;

/**
 * Created by rajeev on 17/10/16.
 */
public class MqttPublisher {
    private MqttClient client;
    String clientId = "callicoder-pub";

    public MqttPublisher() {
        try {
            client = new MqttClient(MqttConstants.BROKER_URL, clientId);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void start() {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setWill(client.getTopic(MqttConstants.OFFLINE_TOPIC), "I'm gone :(".getBytes(), 0, false);
            client.connect(options);

            Scanner keyboard = new Scanner(System.in);
            String message;
            do {
                System.out.println("Type your Message. Type 'Quit' to quit.");
                message = keyboard.next();
                sendMessage(message);
            } while (!message.equalsIgnoreCase("Quit"));

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void sendMessage(String message) throws MqttException {
        MqttTopic messageTopic = client.getTopic(MqttConstants.MESSAGE_TOPIC);
        messageTopic.publish(new MqttMessage(message.getBytes()));
    }

    public static void main(String... args) {
        final MqttPublisher publisher = new MqttPublisher();
        publisher.start();
    }
}
