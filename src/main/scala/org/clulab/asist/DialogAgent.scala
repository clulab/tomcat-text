//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  MQTT message relayer, subscribes to messages on one topic and
//  then re-publishes them on another.
//  Based on the Eclipse paho MQTT package defined here:
//  https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/package-tree.html
//
package org.clulab.asist


// coordinator for the Mosquitto subscriber and publisher
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


object DialogAgent extends App {

    val brokerUri   = "tcp://127.0.0.1:1883"
    val qos         = 2
    val topic       = "example_topic"
    val relay_topic = "relay_example_topic"
    val message     = "Hello Mosquitto!"

    val subscriber_to_topic = new DialogSubscriber("Sub1",  brokerUri, qos)
    val subscriber_to_relay_topic = new DialogSubscriber("Sub2",  brokerUri, qos)
    val publisher   = new DialogPublisher ("Pub",   brokerUri, qos)

    subscriber_to_topic.connect()
    subscriber_to_relay_topic.connect()
    publisher.connect()

    subscriber_to_topic.subscribe(topic)
    subscriber_to_relay_topic.subscribe(relay_topic)

    // proof-of-concept test:   

    // Publishing on topic will reach subscriber_to_topic
    publisher.publish(topic, message) 

    // add a relayer to send topic messages to relay_topic
    val relay = new DialogRelayer ("Relay", brokerUri, topic, relay_topic, qos)
    relay.connect()

    // Publishing on topic will now reach subscriber_to_topic AND subscriber_to_relay_topic
    publisher.publish(topic, message) 
}
