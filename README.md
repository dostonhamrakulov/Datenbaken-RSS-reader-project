Database and Web Technologies

### Programming languages and frameworks
```json
Backend:
	- Java
	- Java Spring Boot Framework

Database:
	- MySQL

Frontend:
	- PHP
	- Javascript
	- HTML5, CSS and Bootstrap
```

### IDE and tools
```Json
	- Intellij Idea, 
	- Postman
	- Eclipse
```

## Authors
```json
 - Doston Hamrakulov 
 - Sagar Kafle
```

## Positions
>*Software Engineers, Web Developers, Freelancers*



## Backend
### 2.2.1 Backend
* [X] - 1.The backend aggregates and processes the data of the various web feeds.
* [X] - It can handle multiple standards of web feeds, supporting at least one version of RSS
and the Atom format.
* [ ] - It prevents the updating of web feeds in a too small time interval, so that it waits at least 10 minutes between two requests for a web feed (and blocks other requests to do so).
* [X] - It ensures a consistent storage of the data in a database.
* [X] - Existing records can also be updated, but no duplicates are created.
* [X] - Records that have reached a certain age are deleted and no longer inserted. By
default, this age should be 30 days.

### 2.2.2 Database
* [X] - The database stores the aggregated and processed data of the web feeds.
* [X] - It can also be used to store the configuration.
* [X] - The frontend must never communicate directly with the database.


### 2.2.3 REST based interface
* [X] - The REST based interface provides communication between the frontend and the
database.
* [X] - It delivers the data from the database to the frontend.
* [X] - It can also be used to store configuration settings in the database.
* [X] - Furthermore, it is possible to use the REST based interface to initiate an update of the web feeds by the backend. Therefore, it is not necessary to implement a proactive service which automatically performs the update.


### 2.2.4 Frontend
* [X] - The information of all web feeds can be displayed. The following details must at least
be available:
  * [X] - a)title of the content
  * [X] - b)clickable link to the webpage with the actual content
  * [X] - c)date when the content was published according to the web feed
  * [X] - d)date when the entry in the web feed first got detected by the program
  * [X] - e)provider where the content is available
* [X] - The display of the contents of individual providers, i.e. web feed links, can be enabled
or disabled.
* [ ] - Usability aspects are taken into account.
* [ ] - The design is appealing.

### 2.2.4 Frontend
* [X] - The following information can be displayed for each provider:
  * [X] - a)address (URL) of the web feed
  * [X] - b)date of the last successful update of the web feed
  * [ ] - c)Date of the latest record according to information from the web feed (the date should also be available if all records are older than the threshold for deleting old records and therefore no record of the web feed is in the database anymore)
  * [X] - d)date of the last attempt to update with the number of records found or an indication of an error that occurred during the update attempt
* [X] - At least five different working web feeds and a malfunctioning web feed are available for demonstration purposes.


### 2.2.5 Additional task for groups
* [X] - The backend can also handle "broken" web feeds that do not fully adhere to the labelled standard.
* [X] - For each record, there is a detail view that shows the contents included in the web feed.
* [ ] - The time interval for the web feed update blockade and the age for deleting old records can be set via the frontend.
* [ ] - The complete web feed management takes place via the frontend, whereby at least the following options exist beyond the previous display options:
  * [ ] - Insert new web feeds with address (UsRL) and display name, which is also used in the overview
  * [ ] - Modify the entry of a web feed (change the URL or display name) while preserving existing records in the database
  * [ ] - Delete the entry of a web feed, deleting all associated data from the database
* [ ] - It is possible to export the data of the web feeds selected in the frontend view as a new web feed in RSS or Atom format. This export should also be reachable via a normal URL with a GET request and contain all stored data. Ultimately, this should make it possible to transform a web feed from RSS to Atom format or vice versa.




* [X] - It can handle multiple standards of web feeds, supporting at least one version of RSS
and the Atom format.
   * [X] - supporting RSS format
   * [X] - supporting Atom format
* [X] - It prevents the updating of web feeds in a too small time interval, so that it waits at least
10 minutes between two requests for a web feed (and blocks other requests to do so).
* [X] - Records that have reached a certain age are deleted and no longer inserted. By default, this age should be 30 days.



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
* [X] - Delete all feeds of web_feed_provider after provider is deleted
* [X] - Rewrite delete method of web_feed_providers properly with userid
   * [X] - Check if feed_provider exists or not
   * [X] - Check if a user exists or not
   * [X] - return Status Code, OK, NOT FOUND
   * [X] - Make DELETE request
* [X] - Inserting web feeds when a provider is added
   * [X] - checking whether a link is present or not
   * [X] - Consistancy of data like whether title is there or published date
* [X] - getting number of all feeds -- **GET request**
* [X] - getting number of feeds for a specific provider -- **GET request**


### Some Tasks
* [ ] - It prevents the updating of web feeds in a too small time interval, so that it waits at least
10 minutes between two requests for a web feed (and blocks other requests to do so). **Sagar**
* [X] - It ensures a consistent storage of the data in a database.
* [X] - Existing records can also be updated, but no duplicates are created
   * [X] - based on title and published date
* [X] - Records that have reached a certain age are deleted and no longer inserted. By
default, this age should be 30 days.
* [ ] - Furthermore, it is possible to use the REST based interface to initiate an update of the
web feeds by the backend. Therefore, it is not necessary to implement a proactive
service which automatically performs the update.
* [ ] - The display of the contents of individual providers, i.e. web feed links, can be enabled
or disabled.
* [X] - Date of the latest record according to information from the web feed (the date should also be
available if all records are older than the threshold for deleting old records and therefore no
record of the web feed is in the database anymore)
* [X] - date of the last attempt to update with the number of records found or an indication of an
error that occurred during the update attempt
* [ ] - 
* [X] - The time interval for the web feed update blockade and the age for deleting old records can be set
via the frontend.

* [X] - Fixing userid is always zero while adding provider
* [X] - Deleting all feeds when their provider is deleted by id

**16 June 2019**
* [X] - updating number of Feeds of a provider
* [X] - updating Latest_record_date of a provider(it means taking a publishedDate of latest web_feed and inserting into the provider)
* [X] - update providers of a user based on his/her update-period


## Additional Tasks:
* [ ] - The backend can also handle "broken" web feeds that do not fully adhere to the labelled standard.
* [X] - For each record, there is a detail view that shows the contents included in the web feed.
* [X] - The time interval for the web feed update blockade and the age for deleting old records can be set
via the frontend.
* [X] - The complete web feed management takes place via the frontend, whereby at least the following
options exist beyond the previous display options:
   * [X] - Insert new web feeds with address (URL) and display name, which is also used in the overview
   * [ ] - Modify the entry of a web feed (change the URL or display name) while preserving existing records in the
database
   * [X] - Delete the entry of a web feed, deleting all associated data from the database
* [ ] - It is possible to export the data of the web feeds selected in the frontend view as a new web feed in
RSS or Atom format. This export should also be reachable via a normal URL with a GET request
and contain all stored data. Ultimately, this should make it possible to transform a web feed from
RSS to Atom format or vice versa.
   * [X] - Exporting as JSON to Frontend is done
   * [X] - Converting JSON into RSS or ATOM **Sagar**


## APIs for User:

* [X] - add a user ---  **PUT request**
   * [X] - http://localhost:8080/user/add-user
Request:
```json
{
        
        "email": "user1@gmail.com",
        "name": "user1",
        "password":"user1@"
}
```


Response:
```json 
Created - CREATED 301

or bad quest
```


* [X] - log in a user request -- **POST request**
   * [X] - http://localhost:8080/user/get-user
```json
{
        
        "email": "user1@gmail.com",
        "password":"user1@"
}
```
Response:
```json
{
    "id": 1084,
    "email": "user1@gmail.com",
    "name": "user1",
    "password": "user1@",
    "status": 0,
    "feedage": 0,
    "updateperiod": 0
}

with Status code: FOUND 302
or 
HTTP Status Code: NOT_FOUND
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

* [X] - delete a provider by user_id  - **DELETE request**
   * [X] - http://localhost:8080/web-feed-provider/delete-by-userid/?userid=0
Response:
```json
Deleted with Status code: OK 200
or
cannot delete with Status code: NOT_FOUND 404
```

* [X] - update a provider with new number of feeds, updateDate, LastAttempDate, LatestFeedDate
  * [X] - http://localhost:8080//web-feed-provider/update-provider -- **PUT request**
Request:
```json
{
        "id": 1173,
        "updateddate": "Tue, 11 Jun 2019 00:30:19 CEST",
        "latestrecorddate": "Tue, 30 Jun 2019 00:30:21 CEST",
        "lastattempt": "Tue, 30 Jun 2019 00:30:21 CEST",
        "numfeeds": 130
}

Reponse:
1 (Updated) with Status code: OK 200
or
0(cannot update) with Status code: NOT_FOUND 404
```

* [X] - update a provider with new number of feeds, updateDate, LastAttempDate, LatestFeedDate
  * [X] - http://localhost:8080/web-feed-provider/update-only-provider -- **PUT request**
Request:
```json
{
   
	"id": 1173,
	"name": "New name",
	"link": "new url"

}

Reponse:
Updated with Status code: OK 200
or
cannot update with Status code: NOT_FOUND 404
```

* [X] - update a provider with new number of feeds, updateDate, LastAttempDate, LatestFeedDate
  * [X] - http://localhost:8080//web-feed-provider/update-error-of-provider -- **PUT request**
Request:
```json
{
        "id": 1173,
        "error": 111
}

Reponse:
Updated with Status code: OK 200
or
cannot update with Status code: NOT_FOUND 404
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

* [X] - delete feeds by provider_id -   **DELETE request**
   * [X] - http://localhost:8080/feeds/delete-by-providerid/?providerid=940
Response:
```json
Deleted with Status code: OK 200
or
cannot delete with Status code: NOT_FOUND 404
```


* [X] - delete feeds by provider_id and userid -   **DELETE request**
   * [X] - http://localhost:8080/web-feed-provider/delete-by-id-and-userid
Request:
```json
{
        "id": 1003,
        "userid": 102
}
```

Response:
```json
Deleted with Status code: OK 200
or
cannot delete with Status code: NOT_FOUND 404
```

* [X] - Update a web_feed--- **PUT request**
   * [X] - http://localhost:8080/feeds/update
Request:
```json
{
        "title": "Updated again",
        "link": "https://www.nytimes.com/2019/06/10/world/asia/sewol-ferry-accident.html?emc=rss&partner=rss",
        "publisheddate": "Mon, 10 Jun 2019 23:54:20 CEST"
	
}
```
Response:
```json
Updated with Status code: OK 200
or
cannot update with Status code: BAD_REQUEST
```

* [X] - Export feeds --- **GET request**
   * [X] - http://localhost:8080/feeds/export?ids=1322,1323,1324,1325,1326 --- **ids are separated by commas**
Response:
```json
[
    {
        "id": 1322,
        "title": "An Overloaded Ferry Flipped and Drowned Hundreds of Schoolchildren. Could It Happen Again?",
        "link": "https://www.nytimes.com/2019/06/10/world/asia/sewol-ferry-accident.html?emc=rss&partner=rss",
        "description": "South Korea promised to root out a culture that put profit ahead of safety. But cheating and corruption continue to endanger travelers.",
        "publisheddate": "Mon, 10 Jun 2019 23:54:20 CEST",
        "importeddate": "Tue, 11 Jun 2019 01:14:03 CEST",
        "providerid": 1173,
        "image": "src_img"
    },
...
with Status Code: OK
]
```

* [X] - Get total number of web feeds for a **specific provider**
  * [X] - http://localhost:8080/feeds/num-of-feeds-of-provider?providerid=1173   --- **GET request**
Response is Integer number:
```json
88

with Http Status: OK

if not found, it will return Status: NOT_FOUND
```

* [X] - update "deleted" of web feed status of a feed
  * [X] - http://localhost:8080/feeds/delete-feed?id=2142   --- **PUT request**
Response
```json
Deleted

with Http Status: OK

or
"Error occured" Status Code: INTERNAL_SERVER_ERROR
```

* [X] - modify a web feed 
  * [X] - http://localhost:8080/feeds/update-feed-by-user   --- **PUT request**
Request
```json
{
        "id": 3918,
        "title": "New name",
        "link": "htvdfvdfvdfvdftps://www.nytimes.com/2019/06/18/worlssdvsdfvsfvfd/africa/congo-ethnic-violence-ebola.html?emc=rss&partner=rss"
        
}

Updated

with Http Status: OK

or
"Error occured" Status Code: INTERNAL_SERVER_ERROR
```















Setup:
	- Installing Rome in Eclipse

