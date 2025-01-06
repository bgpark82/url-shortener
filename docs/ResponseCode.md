# Response Code

**Status Code `301`**

- Prevents traffic loss through browser caching.
- Generally used for permanent URL redirection.
- Adjust `Cache-Control` and `Expires` headers when changing URLs.

### 301 Moved Permanently

- Permanently redirects the URL.
- Internally utilizes browser caching.
- Advantages:
    - SEO-friendly: Prompts search engines to update the indexed URL.
    - Prevents traffic loss: Cached URL reduces server traffic.
- Disadvantages:
    - Difficult to change: Permanent setting can complicate updates.
    - Caching: Requires additional work to update redirection.

### 302 Found

- Temporarily redirects the URL.
- Advantages:
    - Temporary redirection: Suitable for event or promotion pages.
    - No impact on search engines: Original URL remains indexed.
- Disadvantages:
    - Traffic loss: URL redirection occurs every time.