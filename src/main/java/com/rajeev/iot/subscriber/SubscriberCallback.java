package com.rajeev.iot.subscriber;

import com.rajeev.iot.utils.MqttConstants;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by rajeev on 19/10/16.
 */
public class SubscriberCallback implements MqttCallback {

    public void connectionLost(Throwable cause) {
        //This is called when the connection is lost. We could reconnect here.
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message arrived. Topic: " + topic + "  Message: " + message.toString());

        if (MqttConstants.OFFLINE_TOPIC.equals(topic)) {
            System.err.println("Publisher gone!");
        }
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        //no-op
    }
}
