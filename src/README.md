#shorten URL
simple shorten url with h2 DB to store the url and keys


## how to run
- to generate shorten url:
    POST `http://localhost:8080/api/v1/shorten
    Request (content type `application/json`):
    
    {
    "url": "http://www.urls.com/this-is-a-really-long-url"
    }

   response:    {  "id" : "Xfh3fg"}

- to use the shorten url and redirect to original site
    GET `http://localhost:8080/api/v1/go/{id}`
    Path parameter `id`, i.e `Xfh3fs0tHj4`

    resposne will redirect to original site


- you can use simple html to generate the shorten url: http://localhost:8080
    page will popup the generate url link

- application support LRU (default configuration is 2 keys)