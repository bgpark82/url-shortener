# Short URL resolution API

### Test Configuration
- Users: 500 test users
- Ramp-up: 5 minutes gradual ramp-up

### 1. Database Integration
![img.png](../images/resolve_db_metric.png)
![img_1.png](../images/resolve_db_locus.png)
tomcat
- max.thread: 200

hikari
- max.pool.size: 10
- connection.timeout: 1000

(Use the same configration for following tests)

### 2. Redis Integration
![img_2.png](../images/resolve_redis_metric.png)
![img_3.png](../images/resolve_redis_locus.png)

### 3. LocalCache Integration
![img_4.png](../images/resolve_local_metric.png)
![img_5.png](../images/resolve_local_locus.png)