Solution d'exercice: Twitter
Create a Scala project in proper structure using Class, Interface, configuration files, unit
tests etc that retrieves tweets from a twitter account (eg @realDonaldTrump) in a continuous
manner (streaming) and store the tweets in a csv file with an extra field/ column called "Status"
along with the original fields of the tweet.
The value of the column "Status" can be one of the three values:
1. "Retweet" if the tweet is a retweet of an another tweet.
2. "Reply Tweet" if the tweet is a reply to an existing tweet.
3. "Original Tweet" if the tweet is neither a "Retweet" or a "Reply Tweet"
The developer can make any kind of assumptions regarding the project in order to make it work.
Documentation of the twitter api can be found here:
https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object.html
The developer may need to create a twitter account, if they don't have one and do some
prerequisites like creating access_token and tonken_secret in order to start consuming timeline of
a twitter account.

Requirement: 
1- create teweet account 
2- retrieves credentials from your account and fill in the file credentials.txt
   - consumerKey 
   - consumerSecret 
   - accessToken 
   - accessTokenSecret 