Database and Web Technologies

## Backend

* [X] - Connecting to Database
* [X] - Queries over DB with Spring

* [X] - APIs for web_feed_providers
* [X] - APIs for web_feed
* [O] - APIs for User
* [O] - APIs for User Login
* [X] - RSS Reading
   * [X] - Validating RSS data
   * [X] - Getting individual RSS items
   * [O] - other main tasks
* [X] - Writing RSS data into DB
* [O] - Update web_feed_provider
* [O] - Delete all feeds of web_feed_provider after provider is deleted




## Database:

* [X] - Create schema
* [X] - Create tables
* [O] - Create ER Model
* [O] - Next task
* [O] - Next task
* [O] - Next task


## Frontend
* [O] - one
* [O] - two


## Web_feed_providers APIs:

* [X] - getting all web_feed_providers:
   * [X] - http://localhost:8080//web-feed-provider/all
```json 
[
    {
        "provider_id": 1,
        "name": "wordpress",
        "link": "http://wordpress.org/news/feed/",
        "updated_date": "2018/09/09",
        "num_feeds": 10,
        "error": 0
    },
...
]
```

* [X] - getting a web_feed_provider by id
   * [X] - http://localhost:8080//web-feed-provider/{provider}  -- provider=1
```json
{
    "provider_id": 1,
    "name": "wordpress",
    "link": "http://wordpress.org/news/feed/",
    "updated_date": "2018/09/09",
    "num_feeds": 10,
    "error": 0
}
```

* [X] - deleting a web_feed_provider by id
   * [X] - http://localhost:8080//web-feed-provider/1
```json
Response HTTP OK
```

* [X] - adding Web Feed Proviver
   * [X] - http://localhost:8080//web-feed-provider/add
```json
Example_1:
{
	
	"link" : "https://www.espn.com/espn/rss/news"
}

Example_2: --- without date

{
	
	"name" : "US news",
	"link" : "https://www.usnews.com/rss/education",
	"num_feeds" : 12,
	"error" : 1
}

```


## Web_feed APIs:
* [X] - getting a web_feed by id
   * [X] - http://localhost:8080/feeds/{id}  -- id=1

```json
{
    "id": 1,
    "title": "Title1",
    "link": "Link1",
    "description": "Description1",
    "published_date": "2019/01/01",
    "imported_date": "2019/05/05",
    "provider_id": 1,
    "image": "img_src_1"
}

if not found, it will return Status: 404 Not Found
```

* [X] - http://localhost:8080/feeds/all   ---->
Response will be:
```json
[
    {
        "id": 0,
        "title": "Title1",
        "link": "Link1",
        "description": "Description1",
        "published_date": "2019/01/01",
        "imported_date": "2019/05/05",
        "provider_id": 1,
        "image": "img_src_1"
    },
...
]
```



