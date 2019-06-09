Database and Web Technologies

## Backend

* [X] - It can handle multiple standards of web feeds, supporting at least one version of RSS
and the Atom format.
   * [X] - supporting RSS format
   * [ ] - supporting Atom format
* [ ] - It prevents the updating of web feeds in a too small time interval, so that it waits at least
10 minutes between two requests for a web feed (and blocks other requests to do so).
* [ ] - Records that have reached a certain age are deleted and no longer inserted. By default, this age should be 30 days.



* [X] - Connecting to Database
* [X] - Queries over DB with Spring
* [X] - APIs for web_feed_providers
* [X] - APIs for web_feed
* [X] - APIs for User
* [X] - APIs for User Login
* [X] - RSS Reading
   * [X] - Validating RSS data
   * [X] - Reading whole RSS file from url
   * [X] - Getting individual RSS items
   * [X] - Validating RSS items before inserting into DB
* [X] - Writing individual RSS items into DB in time interval
   * [X] - checking whether a link is present or not
   * [X] - Consitancy of data like whether title is there or published date
* [X] - Getting all new web feeds from web feed provider when program starts
* [X] - sdds
* [X] - Update web_feed_provider
* [ ] - Delete all feeds of web_feed_provider after provider is deleted
* [ ] - Rewrite delete method of web_feed_providers properly with userid
   * [ ] - Check if feed_provider exists or not
   * [ ] - Check if a user exists or not
   * [ ] - return Status Code, OK, NOT FOUND
   * [ ] - Make POST request
* [X] - Inserting web feeds when a provider is added
   * [X] - checking whether a link is present or not
   * [X] - Consistancy of data like whether title is there or published date
* [ ]

### Additional Task
* [ ] - The time interval for the web feed update blockade and the age for deleting old records can be set
via the frontend.

* [X] - Fixing userid is always zero while adding provider



## Database:

* [X] - Create schema
* [X] - Create tables
* [ ] - Create ER Model
* [X] - Inserting
* [X] - Configuration with Spring JPA


## Frontend
* [ ] - TASK_1
* [ ] - TASK_2



All APIs from Backend:


## APIs for Web_feed_providers:

* [X] - getting all web_feed_providers:   --- **GET request**
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

* [X] - getting a web_feed_provider by id   --- **GET request**
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

* [X] - deleting a web_feed_provider by id   --- **DELETE request**
   * [X] - http://localhost:8080//web-feed-provider/1
```json
Response HTTP OK
```

* [X] - adding Web Feed Proviver    - **POST request**
   * [X] - http://localhost:8080//web-feed-provider/add
```json
{
	
	"name":"Name_1",
	"link" : "https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml",
	"userid":11
}

```

* [X] - get all web_feed_providers by user_id:   **GET request**
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



## APIs for Web_feed:
* [X] - getting a web_feed by id
   * [X] - http://localhost:8080/feeds/{id}  -- id=1   **GET request**

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

* [X] - http://localhost:8080/feeds/all   ---->  **GET request**
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
  * [X] - http://localhost:8080/feeds/link   - **POST request**
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

if not found, it will return Status: NO_CONTENT
```

* [X] - adding a single web feed  - **POST request**
   * [X] - http://localhost:8080/feeds/add
Request:
```json
{
		
        "title": "Title_3",
        "link": "Link4",
        "description": "Description4",
        "published_date": "2019/01/01",
        "imported_date": "2019/05/05",
        "provider_id": 1,
        "image": "img_src_4"
}

```
Reponse:
```json
with Http Status: Created

if not found, it will return Status: BAD_GATEWAY
```

* [X] - Get total number of web feeds
  * [X] - http://localhost:8080/feeds/number-of-feeds   --- **GET request**
Response is Integer number:
```json
88

with Http Status: OK

if not found, it will return Status: BAD_GATEWAY

```

* [X] - get all feeds by provider_id:  --- **GET request**
  * [X] - http://localhost:8080/feeds/feeds-of-provider/?providerid=786
```json
[
    {
        "id": 787,
        "title": "Tech Giants Amass a Lobbying Army for an Epic Washington Battle",
        "link": "https://www.nytimes.com/2019/06/05/us/politics/amazon-apple-facebook-google-lobbying.html?emc=rss&partner=rss",
        "description": "Amazon, Apple, Facebook and Google, facing the growing possibility of antitrust action and legislation to rein in their power, are spending freely to gain influence and access.",
        "published_date": "Thu, 06 Jun 2019 01:04:39 CEST",
        "imported_date": "Sun, 09 Jun 2019 11:02:06 CEST",
        "providerid": 786,
        "image": "src_img"
    },
    {
        "id": 788,
        "title": "Democratic Candidates Woo Silicon Valley for Donations, Then Bash It",
        "link": "https://www.nytimes.com/2019/06/06/us/politics/democrats-2020-donations-silicon-valley.html?emc=rss&partner=rss",
        "description": "Until recently big tech companies were seen as one of the few relatively untainted sources of big-money donations for Democrats. Now, that’s changing.",
        "published_date": "Fri, 07 Jun 2019 04:00:48 CEST",
        "imported_date": "Sun, 09 Jun 2019 11:02:06 CEST",
        "providerid": 786,
        "image": "src_img"
    },

...
]
```

* [X] - delete feeds by provider_id
   * [X] - http://localhost:8080/feeds/delete-by-providerid/?providerid=940
Response:
```json
Deleted with Status code: OK 200
or
cannot delete with Status code: NOT_FOUND 404
```


## APIs for User:

* [X] - get all users   --- **GET request**
   * [X] - http://localhost:8080/user/all

Response:
```json
[
    {
        "id": 101,
        "name": "user1",
        "email": "user1@gmail.com",
        "status": 1,
        "feedage": 11,
        "updateperiod": 55
    },
...
]

with Http Status: FOUND (302)

if not found, it will return Status: NOT_FOUND (404)
```

* [X] - get user by id --- --- **GET request**
   * [X] - http://localhost:8080/user/?id=101
Response:
```json
{
    "id": 101,
    "name": "user1",
    "email": "user1@gmail.com",
    "status": 1,
    "feedage": 11,
    "updateperiod": 5
}

with Http Status: FOUND (302)

if not found, it will return Status: NOT_FOUND (404)
```

* [X] - update feed-age by id and feed-age **PUT request**
   * [X] - http://localhost:8080/user/update-feed-age
Request:
```json
{
    "id": 101,
    "feedage": 22
}
```
Response
```json
{
    "id": 101,
    "name": "user1",
    "email": "user1@gmail.com",
    "status": 1,
    "feedage": 22,
    "updateperiod": 55
}

with Http Status: OK (200)

if not found, it will return Status: BAD_REQUEST(400)
```

* [X] - update **update-period** by id and update-period **PUT request**
   * [X] - http://localhost:8080/user/update-updateperiod
Request:
```json
{
    "id": 101,
    "updateperiod": 66
}
```
Response
```json
{
    "id": 101,
    "name": "user1",
    "email": "user1@gmail.com",
    "status": 1,
    "feedage": 22,
    "updateperiod": 66
}

with Http Status: OK (200)

if not found, it will return Status: BAD_REQUEST(400)
```
Setup:
	- Installing Rome in Eclipse

