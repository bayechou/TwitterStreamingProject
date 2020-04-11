package fr.twitter.util

object Constants {
  
  val NONE_REPLY_TO_STATUS_ID = -1
   
  val COL_SCREEN_NAME                 = "ScreenName" 
  val COL_STATUS_TWEET                = "StatusTweet"
  val COL_CONTENT_TWEET               = "Content"
  
  val REPLY_TWEET                     = "Reply Tweet"
  val RETWEET_TWEET                   = "Retweet"
  val ORIGINAL_TWEET                  = "Original Tweet"
  
  val PREFIX_CSV_OUTPUT_BATCH         = "Batch_tweets_"
  
  val FILE_CREDENTIALS                = "/src/credentials.txt"
  
  val PROP_OAUTH                      = "twitter4j.oauth."
  
  val LOCAL_MASTER_WITH_ALL_CPU_CORES = "local[*]"
  val CONTEXT_TWITTER_STREAMING_NAME  = "ProcessingTweets"
  
  val ACCOUNT_TRUMP                   = "realDonaldTrump"
  val BATCH_INTERVAL                  = 5
}