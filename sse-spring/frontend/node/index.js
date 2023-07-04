var EventSource = require('eventsource')

eventSourceBuilder = () => {
  var evtSource = new EventSource("http://localhost:8080/stream-sse-mvc");

  evtSource.onmessage = (event) => {
    console.log(event.data);
  };

  evtSource.onerror = (err) => {
    console.error("EventSource failed:", err);
    evtSource.close();
    setTimeout(eventSourceBuilder, 2000);
  };
};

eventSourceBuilder();
