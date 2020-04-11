package fr.twitter.driver

import fr.twitter.configuration.AuthenticatedUser._
import org.apache.spark.streaming.{ Seconds, StreamingContext }
import org.apache.spark.sql.SparkSession
import fr.twitter.processing.TweetProcessing
import fr.twitter.util.Constants
import fr.twitter.processing._
import org.apache.spark.SparkConf

/**
 *
 * Create a Java/ Scala project in proper structure using Class, Interface, configuration files etc
 * that retrieves tweets from a twitter account (eg @realDonaldTrump) in a continuous manner (streaming)
 * and store the tweets in a csv file with an extra field/ column called "Status" along with the original
 * fields of the tweet.
 *
 * The value of the column "Status" can be one of the three values:
 * 1. "Retweet" if the tweet is a retweet of an another tweet.
 * 2. "Reply Tweet" if the tweet is a reply to an existing tweet.
 * 3. "Original Tweet" if the tweet is neither a "Retweet" or a "Reply Tweet"
 * The developer can make any kind of assumptions regarding the project in order to make it work.
 * Documentation of the twitter api can be found here:
 * https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object.html
 * The developer may need to create a twitter account, if they don't have one and do some prerequisites  like creating access_token and tonken_secret in order to start consuming timeline of a twitter account.
 */

object DriverTwitterProcessing extends App {

  /**
   * Configure Twitter credentials
   */

  setupAuthenticateUserTwitter()

  setupLoggingLevel();
  /**
   * Set up a Spark streaming context named "SavingTweets"
   * that runs locally using all CPU cores and five-second
   * batches of data
   */
   val conf = new SparkConf().setAppName(Constants.CONTEXT_TWITTER_STREAMING_NAME).setMaster(Constants.LOCAL_MASTER_WITH_ALL_CPU_CORES)

   val spark = SparkSession.builder().config(conf).getOrCreate()
   val ssc = new StreamingContext(spark.sparkContext, Seconds(2))
  
  // val ssc   = new StreamingContext(Constants.LOCAL_MASTER_WITH_ALL_CPU_CORES, Constants.CONTEXT_TWITTER_STREAMING_NAME, Seconds(2))
  // val spark = SparkSession.builder().getOrCreate()

  /**
   * Configure Level log
   */

  setupLoggingLevel()

  /**
   * Listens to a Stream of tweets and saves them to csv file
   */
  
  new TweetProcessing()
  
// new PopularHashtags()
// new AverageTweetLength()
}