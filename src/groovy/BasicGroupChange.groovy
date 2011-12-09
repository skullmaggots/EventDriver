/* Event testing DSL
*  send single event
*  send event within a loop
*  send single event, and expect a reply
*  send multiple events, and expect a reply
*  make assertions on returned messages
*/
queueName "EventDriverTest"

(0..5).each {
    sendEvent {
        eventName "group-change-event"
        header ([sourceApp:"MPS"])
        body {
            "This is the body"
        }
    }
    sleep(1.secs)
}


