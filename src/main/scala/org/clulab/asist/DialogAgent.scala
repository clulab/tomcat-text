package org.clulab.asist


import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

/**
 * A singleton class to provide a convenient interface to the Eclipse Paho
 * MQTT client library.
 */

object DialogAgent extends App {

    println("DialogAgent Starting")

    val gson: Gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    val topic1 = "example_topic"
    val topic2 = "observations/events/#"

    def printException(me: MqttException): Unit =  {
        System.err.println("MqttException:")
        System.err.println("reason " + me.getReasonCode())
        System.err.println("msg " + me.getMessage())
        System.err.println("loc " + me.getLocalizedMessage())
        System.err.println("cause " + me.getCause())
        System.err.println("excep " + me)
    }

/*
  val mqttSource: Source[MqttMessage, Future[Done]] =
    MqttSource.atMostOnce(
      connectionSettings.withClientId(clientId = "source-spec/source"),
      MqttSubscriptions(
        Map(topic1 -> MqttQoS.AtLeastOnce, topic2 -> MqttQoS.AtLeastOnce)),
      bufferSize = 8
    )
*/



    try{
        val persistence: MemoryPersistence = new MemoryPersistence()
        val connectOptions: MqttConnectOptions = new MqttConnectOptions()

        // Note: We are currently hard-coding the port number to the default
        // (1883), but we may want to change this to be settable by the
        // end-user later.
        val client: MqttClient = 
            new MqttClient("tcp://127.0.0.1:1883", "TomcatClient", persistence)
        connectOptions.setCleanSession(true)
        client.connect(connectOptions)
    } 
    catch {
        case me: MqttException =>printException(me);
    }


/*

    def publish(object: Object, topic: String): Unit = {
        publish(gson.toJson(object), topic)
    }

    def publish(msg: String, topic: String): Unit = {
        try {
            val message: MqttMessage = new MqttMessage(msg.getBytes())
            message.setQos(2)
            publish(topic, message)
        }
        catch {
            case me: MqttException => printException(me)
        }
    }
*/
    println("DialogAgent Stopping")
}


