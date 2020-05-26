package `in`.edak.xmltest.mq

import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms.Session

class QueueConfig(brokerUrl: String,
                  val clientQueueName : String
) {
    val ackMode = Session.AUTO_ACKNOWLEDGE
    val transacted = false

    val connectionFactory = ActiveMQConnectionFactory(brokerUrl)
    val connection = connectionFactory.createConnection()


    fun startListen() {
        val session = connection.createSession(transacted, ackMode)
        val queue = session.createQueue(clientQueueName)
        val consumer = session.createConsumer(queue)
        consumer.messageListener = SmsMessageListerner()
        connection.start()
    }
}