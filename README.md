# Deep Twitter - Tweet Extraction Application

Tweet extraction application with Twitter API. Tweets will be using for 
[Deep Learning](https://www.udacity.com/course/deep-learning--ud730) with [TensorFlow](https://www.tensorflow.org/).
Tweets should be clear for learning.

**Designed and Implemented By:**

* @mertkosan
* @mbenlioglu

## About

This application has three components, below these three components will be clarified. Each application has own purpose,
that's why there are three components.

1. Crawler

    This application connects Twitter thanks to authorization keys provided by Twitter for developers. Needed tweets are
    Turkish tweets, that's why application is tracking ```tr``` language. However, one can change filter option. Collected
    tweets from Twitter are inserted into database.

2. Filtering

    Unfortunately, tweets are hard to read and interpreted by computers. That's why, this application is removing
    unnecessary elements, words in tweets (emojis, links, short words etc.).
    
3. Transfer

    This application reads tweets from text file and transfers to the database. At the end, clear database will be using
    with [Elastic Search](https://www.elastic.co/), but this is not scope of this application.

>**Important Note**: Insertion of tweets to database has not been done one by one. Scheduler is created and set to 1 minute. 
> Each 1 minute, batch tweets are inserted and committed into database. This method boosts the application speed.
    
## Getting Started

* [Twitter](https://twitter.com/) account with phone verification is needed.
* From [Twitter Developer](https://developer.twitter.com/) website, 4 authorization keys should be obtained and
  integrated to application.
    * Consumer Key
    * Consumer Secret
    * Access Token
    * Access Token Secret
* [MySQL](https://www.mysql.com/) database configuration has been used.
* Configure [persistence.xml](/src/main/resources/META-INF/persistence.xml) for database connection pool / username 
  / password etc.
