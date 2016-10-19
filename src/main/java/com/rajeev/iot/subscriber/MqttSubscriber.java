package com.rajeev.iot.subscriber;

import com.rajeev.iot.utils.MqttConstants;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by rajeev on 17/10/16.
 */
public class MqttSubscriber {

    private MqttClient client;
    String  clientId = "callicoder-sub";

    public MqttSubscriber() {

        try {
            client = new MqttClient(MqttConstants.BROKER_URL, clientId);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() {
        try {

            client.setCallback(new SubscriberCallback());
            client.connect();

            //Subscribe to all subtopics of callicoder
            final String topic = "callicoder/#";
            client.subscribe(topic);

            System.out.println("Subscriber is now listening to " + topic);

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        final MqttSubscriber subscriber = new MqttSubscriber();
        subscriber.start();
    }
}
