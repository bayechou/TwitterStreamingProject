package fr.twitter.processing

import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{ Seconds, StreamingContext }
import fr.twitter.util.Constants
import fr.twitter.data.TweetData
import fr.twitter.driver.DriverTwitterProcessing.{spark,ssc}
import spark.implicits._

/**
 * Listens to a Stream of tweets and saves them to csv file
 */

class TweetProcessing {

  /**
   * Create a DStream from Twitter using our streaming context
   */

  val tweets = TwitterUtils.createStream(ssc, None)

  /**
   * extract the data of each status update into RDD's using map()
   */

  val statues = tweets.map(status => new TweetData(status.getUser.getScreenName, status.getInReplyToStatusId, status.getRetweetedStatus, status.getText))
  .map(tweet => (tweet.getScreenName(), tweet.getStatusTweet(), tweet.getContent()))

  val  tests = ssc.remember(Seconds(2));
  

  //tweets.slice(Time(0),Time(20));
  
   
  /**
   * combine each partition's results into a single RDD
   * saves tweets to csv file
  */

  statues.foreachRDD((rdd, time) => {
    if (rdd.count() > 0) {
      val dframe = rdd.toDF(Constants.COL_SCREEN_NAME, Constants.COL_STATUS_TWEET, Constants.COL_CONTENT_TWEET)
      dframe.coalesce(1).write.option("header", true).csv(Constants.PREFIX_CSV_OUTPUT_BATCH + time.milliseconds.toString())
      dframe.printSchema()
      dframe.show
    }
  })

  /**
   *  kick it all off
   */
  ssc.start()
  ssc.awaitTermination()

}
