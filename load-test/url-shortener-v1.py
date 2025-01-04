from locust import HttpUser, task

# Save url
class UrlShortener(HttpUser):

    @task
    def url_shorten(self):
        self.client.post("/api/v1/shorten", json={"longUrl": "http://amazon.com"})