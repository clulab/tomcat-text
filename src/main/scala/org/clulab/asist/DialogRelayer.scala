//  DialogRelayer
//
//  Author:  Joseph Astier
//  Date:  2020 November
//
//  Asynchronous event-driven Mosquitto message subscriber
//  Based on the Eclipse Paho Mosquitto package:
//  https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/package-tree.html
//
package org.clulab.asist

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class DialogRelayer(
        clientId: String, 
        brokerUri: String, 
        srcTopic: String,
        dstTopic: String,
        qos: Int) 
            extends DialogSubscriber(clientId + "Sub", brokerUri, qos) {

    val publisher = new DialogPublisher(clientId + "Pub",  brokerUri, qos) 

    override def connect() : Unit = {
        super.connect()
        publisher.connect()
        subscribe(srcTopic)
    }

    override def messageArrived(topic: String, msg: MqttMessage): Unit = { 
        publisher.publish(dstTopic, msg.toString)
    }
}


class Foo() {
}


class Bar() {
}
