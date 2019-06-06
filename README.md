Database and Web Technologies

## Backend

* [X] - Connecting to Database
* [X] - Queries over DB with Spring
* [O] - RSS Reader
* [O] - Inserting data into the DB
* [O] - Updating data in the DB
* [O] - Reading data from DB
* [O] - sd



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


## APIs:
* [X] - http://localhost:8080/feeds/all   ---->
Result will be:
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


* [X] - getting all web_feed_providers:
   * - http://localhost:8080//web-feed-provider/all
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
   * - http://localhost:8080//web-feed-provider/{provider}  -- provider=1
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
   * - http://localhost:8080//web-feed-provider/1
```json
Response HTTP OK
```

* [O] - adding Web Feed Proviver
   * - http://localhost:8080//web-feed-provider/add
```json
Example_1:
{
	
	"name" : "USA goverment",
	"link" : "https://www.usa.gov/rss/updates.xml",
	"updated_date" : "some_date",
	"num_feeds" : 12,
	"error" : 1
}

Example_2: --- without date

{
	
	"name" : "US news",
	"link" : "https://www.usnews.com/rss/education",
	"num_feeds" : 12,
	"error" : 1
}

```


