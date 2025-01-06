# Rate Limiter

Protect servers from overload, enhance stability and strength security by preventing malicious attack (DDoS)

* User based: limit the number of creating short url by user
* IP based: limit the number of resolving url by IP (DDoS)
* Token bucket algorithm: easy to implement, enable handle steady traffic and traffic spike
* 429 Too Many Requests: block or delay requests
* Redis: count request using `INCR`, reset count using `EXPIRE`