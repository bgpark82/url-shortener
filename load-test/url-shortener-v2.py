from locust import HttpUser, task
import random

# search url
class UrlShortener(HttpUser):

    @task
    def url_shorten(self):
        random_number = random.randint(1, 500000)
        hash = base58_encode(random_number)
        res = self.client.get(f"/{hash}", allow_redirects=False)
        print(f"response: {res}, hash: {hash}")


# Base58 문자 집합 정의
BASE58_ALPHABET = '123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz'

def base58_encode(num):
    if num == 0:
        return BASE58_ALPHABET[0]

    encoded = ''
    while num > 0:
        num, remainder = divmod(num, 58)
        encoded = BASE58_ALPHABET[remainder] + encoded

    return encoded
