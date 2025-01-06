# Cache


**Hybrid Cache**

Uses both local and remote cache.
* Local Cache: Caffeine
* Remote Cache: Redis (Lettuce)

- Low latency: Local cache is faster than remote.
- Memory management: handle memory by using LRU policy
- Prevents cache stampede: Minimizes backend load when cache is missing (local cache -> remote cache -> db)
- Clustering: scaling in high traffic, replicate data across node
- Cache warm-up: Updates local cache during server startup.

### Redis
* pros:
    * rich data structure: string, list, sorted set, etc
    * persistence: RDB snapshot, AOF logs for backup, recovery
    * replication and clustering: scalability, fault-tolerance
    * atomic operation
    * high performance
* cons:
    * single thread: might cause bottleneck

### Memcache
* pros:
    * simplicity: easy to setup, simple key-value
    * performance: fast get/set operation
    * multi thread: multi core process
    * low memory
* cons:
    * no persistence: no backup, data is lost on restart of failure
    * limited data structure
    * no built in clustering
    * only LRU eviction