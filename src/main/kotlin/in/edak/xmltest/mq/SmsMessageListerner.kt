package `in`.edak.xmltest.mq

import `in`.edak.xmltest.run.MessageXmlSerializer
import com.fasterxml.jackson.core.JsonProcessingException
import org.apache.activemq.command.ActiveMQTextMessage
import javax.jms.Message
import javax.jms.MessageListener

class SmsMessageListerner(): MessageListener {
    val messageXmlSerializer = MessageXmlSerializer()

    override fun onMessage(message: Message?) {
        (message as? ActiveMQTextMessage)?.let {textMqMessage ->
            try {
                val smsMessage = messageXmlSerializer.deserialize(textMqMessage.text)
                println(smsMessage  )
            } catch (e:Exception) {
                e.printStackTrace()
            }
        } ?: println("Wrong message type")
    }
}