@Grapes([
@Grab(group='org.apache.activemq', module='activemq-all', version='5.5.1'),
@Grab(group='org.slf4j', module='slf4j-simple', version='1.5.11'),
@Grab(group="org.apache.camel", module="camel-core", version="2.8.1"),
@Grab(group="org.apache.activemq", module="activemq-camel", version="5.5.1")])
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.spi.LifecycleStrategy
import org.apache.camel.CamelContext
import org.apache.camel.Component
import org.apache.camel.Endpoint
import org.apache.camel.Service
import org.apache.camel.Route
import org.apache.camel.spi.RouteContext
import org.apache.camel.Processor
import org.apache.camel.builder.ErrorHandlerBuilder
import java.util.concurrent.ThreadPoolExecutor
import org.apache.camel.StartupListener

abstract class EventDriverBinding extends Script implements StartupListener {

    def eventName
    def headerFields
    def messageBody
    def jms
    def producerTemplate
    def queueName
    def camelStarted = false

    EventDriverBinding() {
        ExpandoMetaClass.enableGlobally()

        Integer.metaClass.getSecs() {
            delegate * 1000
        }

        def ctx = new DefaultCamelContext()
        ctx.addStartupListener(this)
        ctx.start()
        producerTemplate = ctx.createProducerTemplate()
    }

    void eventName(eventName) {
        println(eventName)
        this.eventName = eventName
    }

    void queueName(queueName) {
        println(queueName)
        this.queueName = queueName
    }

    void header(fields) {
        this.headerFields = fields
    }
    def sendEvent = {
        while (!camelStarted) {
            sleep (1.secs)
        }
        it()
        println "\nsending event : ${eventName}"
        println "header {"
        headerFields.each() {key, value -> println "${key} == ${value}"}
        println "}"
        println "body {"
        println messageBody
        println "}"

        producerTemplate.sendBody("activemq:${queueName}",messageBody)
    }

    def body = {
        def messageBody = it()
        this.messageBody = messageBody
    }

    void onCamelContextStarted(CamelContext camelContext, boolean b) {
        camelStarted = true
    }
}
