package org.clulab.asist


import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


// A "Hello World" example of publishing to a Mosquitto topic

object MqttPublish {

    println("MqttPublish Starting")

    val broker = "tcp://127.0.0.1:1883"
    val text = "Hello Mosquitto!"
    val qos = 2
    val clientID = "TomcatClient"
    val topic = "example_topic"
    val persistence = new MemoryPersistence()


    try {
        val client = new MqttClient(broker, clientID, persistence)
        val connectOptions = new MqttConnectOptions()
        connectOptions.setCleanSession(true)
        println("Connecting to broker: "+broker)
        client.connect(connectOptions)
        println("Connected")
        println("On topic: "+topic)
        println("Publishing message: "+text)
        val message = new MqttMessage(text.getBytes())
        message.setQos(qos)
        client.publish(topic, message)
        println("Message published")
        client.disconnect()
        println("Disconnected")
    }
    catch {
        case me: MqttException => {
            println("Caught a MqttException")
        }
        case _ =>{
            println("Caught an Exception")
        }
    }
    

    println("MqttPublish Stopping")
}


