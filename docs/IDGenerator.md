# ID Generator

creating unique and short IDs for URLs

### Redis `INCR`
The `INCR` in Redis generates auto increment IDs
* pros:
    * Low latency: fast due to memory-based
    * Atomicity: The single-threaded of Redis ensures no collisions.
    * Scalability: clustering and sharding, master and replica
* cons
    * SPOF: Data can be lost if the Redis server crashes.
    * Replication Lag: delays in replication cause ID synchronization issues.

### Database `Auto Increment ID`
use `AUTO_INCREMENT` to automatically generate unique IDs.
* pros:
    * Built-in Functionality: minimal setup
    * Durability: IDs managed by DB system
    * Fail over: replication
* cons:
    * Performance: Slower than Redis
    * Scalability: Inefficient in distributed database
    * Locking Issues: concurrency issue due to transaction locking.