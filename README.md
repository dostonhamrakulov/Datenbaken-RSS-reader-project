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
   * [X] - Reading whole RSS file from url
   * [X] - Getting individual RSS items
   * [X] - Validating RSS items before inserting into DB
* [X] - Writing individual RSS items into DB
* [X] - Getting all new web feeds from web feed provider when program starts
* [X] - sdds
* [X] - Update web_feed_provider
* [O] - Delete all feeds of web_feed_provider after provider is deleted
* [O] - Rewrite delete method of web_feed_providers properly with userid
   * [O] - Check if feed_provider exists or not
   * [O] - Check if a user exists or not
   * [O] - return Status Code, OK, NOT FOUND
   * [O] - Make POST request




## Database:

* [X] - Create schema
* [X] - Create tables
* [O] - Create ER Model
* [X] - Inserting
* [X] - Configuration with Spring JPA


## Frontend
* [O] - one
* [O] - two


## Web_feed_providers APIs:

* [X] - getting all web_feed_providers:   --- **GET method**
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

with Http Status: OK
if not found, it will return Status: 404 Not Found
```

* [X] - getting a web_feed_provider by id   --- **GET method**
   * [X] - http://localhost:8080//web-feed-provider/{provider}  -- provider=1

**Response** will be :
```json
{
    "provider_id": 1,
    "name": "wordpress",
    "link": "http://wordpress.org/news/feed/",
    "updated_date": "2018/09/09",
    "num_feeds": 10,
    "error": 0, 

}

with Http Status: OK
if not found, it will return Status: 404 Not Found
```

* [X] - deleting a web_feed_provider by id   --- **DELETE method**
   * [X] - http://localhost:8080//web-feed-provider/1
```json
Response HTTP OK
```

* [X] - adding Web Feed Proviver    - **POST method**
   * [X] - http://localhost:8080//web-feed-provider/add
```json
{
	
	"name":"Name_1",
	"link" : "https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml",
	"userId":11
}

```

* [X] - get all web_feed_providers by user_id:   **GET method**
   * [X] - **http://localhost:8080/web-feed-provider/feed-providers-of-user/101**
```json
[
    {
        "id": 1,
        "name": "NEW YORK Times",
        "link": "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml",
        "updated_date": "2018/09/09",
        "num_feeds": 10,
        "error": 0,
        "userid": 101
    },
...
]
```



## Web_feed APIs:
* [X] - getting a web_feed by id
   * [X] - http://localhost:8080/feeds/{id}  -- id=1   **GET method**

Response:
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

with Http Status: OK

if not found, it will return Status: 404 Not Found
```

* [X] - http://localhost:8080/feeds/all   ---->  **GET method**
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

with Http Status: OK
if not found, it will return Status: 404 Not Found
```


*  [X] - get a single web feed by a link
  * [X] - http://localhost:8080/feeds/link   - **POST method**
RequestBody:
```json
{
    "link": "https://www.nytimes.com/2019/06/08/world/americas/mexico-tariffs-migration.html?emc=rss&partner=rss"
}

```
ResponseBody:
```json
[
    {
        "id": 661,
        "title": "Mexico Sets Domestic Priorities Aside to Meet Terms of U.S. Trade Deal",
        "link": "https://www.nytimes.com/2019/06/08/world/americas/mexico-tariffs-migration.html?emc=rss&partner=rss",
        "description": "President Andrés Manuel López Obrador agreed to divert scant resources to controlling migration instead of fulfilling promises like combating violence.",
        "published_date": "Sun, 09 Jun 2019 05:11:06 CEST",
        "imported_date": "Sun, 09 Jun 2019 06:47:03 CEST",
        "provider_id": 1,
        "image": "src_img"
    }
]

with Http Status: FOUND

if not found, it will return Status: 404 Not Found
```

* [X] - Get total number of web feeds
  * [X] - http://localhost:8080/feeds/number-of-feeds   --- **GET method**
Response is Integer number:
```json
88
```

Setup:
	- Installing Rome in Eclipse

