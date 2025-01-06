# Message Queue


In case of high write traffic, a message queue ensures asynchronous traffic handling

* Prevent Overload: Manages high write traffic by queuing requests
* Loose Coupling: Enables independent system components
* Asynchronous: Ensures low response time for clients
* Scalability: easy scaling by adding more consumers
* Retry Queue: retries requests that are retriable (reliability)
* Dead Letter Queue (DLQ): Handles non-retriable requests manually

### Kafka
distributed streaming platform handling large-scale data

* pros:
    * durability: store data on disk, replicate data (retention)
    * sequential process
    * high availability: data is replicated across servers (automatic recovery)
* cons:
    * consumer lag: process delay
    * complexity

### RabbitMQ
general message queue design for asynchronous communication

* pros:
    * simplicity: simpler setup than kafka
    * light weight: suitable for smaller traffic
* cons:
    * lack of durability: data hold in memory (potentially loss)
 