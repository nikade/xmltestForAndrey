package `in`.edak.xmltest.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.util.*


// Дата класс использвать нельзя, потому что джексону нужен конструктор без парамтеров
// по этой же причине приходится значения поумолчанию прописывать, не очень красиво
class SmsMessage() {
    var sender: String = ""
    var message: String = ""
    // имя проперти не очень хорошее, у джексона есть анатоцаии, можно указать нормальное имя проперти,
    // а в анатоции указать какой тэг будет в xml-ке
    @JacksonXmlProperty(localName = "time_period")
    var timePeriod: String = ""

    @JacksonXmlElementWrapper(localName = "phones",useWrapping = true)
    @JacksonXmlProperty(localName = "phone")
    var phones: List<ReceiverPhone> = listOf()

    // equals и hashCode нужны чтобы в тестах assertEquals работал
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmsMessage

        if (sender != other.sender) return false
        if (message != other.message) return false
        if (timePeriod != other.timePeriod) return false
        if (phones != other.phones) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sender.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + timePeriod.hashCode()
        result = 31 * result + phones.hashCode()
        return result
    }

    override fun toString(): String {
        return "SmsMessage(sender='$sender', message='$message', timePeriod='$timePeriod', phones=$phones)"
    }
}

class ReceiverPhone(
    val number: String = "",
    val uuid: UUID = UUID.randomUUID()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReceiverPhone

        if (number != other.number) return false
        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number.hashCode()
        result = 31 * result + uuid.hashCode()
        return result
    }

    override fun toString(): String {
        return "ReceiverPhone(number='$number', uuid=$uuid)"
    }
}
/*data class ReceiverPhone(
    val phone: String,
    val uuid: UUID
)*/

/*
<smsMessage>
    <sender>+79031234567</sender>
    <message>Всем привет!</message>
    <time_period>10:00-22:00</time_period>
    <phones>
       <phone><number>+79132157888</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130003</uuid></phone>
       <phone><number>+79132157889</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130004</uuid></phone>
       <phone><number>+79132157880</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130005</uuid></phone>
       <phone><number>+79132157881</number><uuid>5192ec6a-85cb-11ea-bc55-0242ac130006</uuid></phone>
    </phones>
</smsMessage>

 */