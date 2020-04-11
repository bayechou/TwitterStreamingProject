package fr.twitter.util

/**
 * The value of the column "Status" can be one of the three values:
 * 1. "Retweet" if the tweet is a retweet of an another tweet.
 * 2. "Reply Tweet" if the tweet is a reply to an existing tweet.
 * 3. "Original Tweet" if the tweet is neither a "Retweet" or a "Reply Tweet"
 */

object StatusTweet extends Enumeration {
  val Reply         = Value(Constants.REPLY_TWEET)     //"Retweet" if the tweet is a retweet of an another tweet.
  val Retweet       = Value(Constants.RETWEET_TWEET)   //"Reply Tweet" if the tweet is a reply to an existing tweet. 
  val OriginalTweet = Value(Constants.ORIGINAL_TWEET)  // "Original Tweet" if the tweet is neither a "Retweet" or a "Reply Tweet"
}
