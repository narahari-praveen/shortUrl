# shortUrl

Short URL app helps user to shorten URL. Go to the page type in a URL and get a short URL out of it. I will also
be shown all existing short URLs and the number of times it was used. When anyone opens
the short URL, they will be redirected to the initial URL that was used to generate a short
URL.

Currently all API are exposed.

POST - localhost:8081/shortenURL
Request Body : 
{
    "url": "http://flipkart.com"
}

GET - localhost:8081/getAllShortUrls



