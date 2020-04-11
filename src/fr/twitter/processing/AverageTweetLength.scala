package fr.twitter.processing

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.StreamingContext._
import fr.twitter.configuration.AuthenticatedUser._
import java.util.concurrent._
import java.util.concurrent.atomic._
import fr.twitter.driver.DriverTwitterProcessing.{ spark, ssc }
/**
 * Uses thread-safe counters to keep track of the average length of
 *  Tweets in a stream.
 */
class AverageTweetLength {

  // Create a DStream from Twitter using our streaming context
  val tweets = TwitterUtils.createStream(ssc, None)

  // Now extract the text of each status update into DStreams using map()
  val lengths = tweets.map(_.getText()).map(_.length())

  
  // As we could have multiple processes adding into these running totals
  // at the same time, we'll just Java's AtomicLong class to make sure
  // these counters are thread-safe.
  var totalTweets = new AtomicLong(0)
  var totalChars = new AtomicLong(0)

  // In Spark 1.6+, you  might also look into the mapWithState function, which allows
  // you to safely and efficiently keep track of global state with key/value pairs.
  // We'll do that later in the course.

  lengths.foreachRDD(rdd => {


    var count = rdd.count()
    var max = 0L;
    if (count > 0) {
      totalTweets.getAndAdd(count)

      totalChars.getAndAdd(rdd.reduce(_+_))

      if(max < count) max = count
      println("Total tweets: " + totalTweets.get() +
        " Total characters: " + totalChars.get() +
        " Average: " + totalChars.get() / totalTweets.get()+
        "The longest length : "+max)
    }
  })

  // Set a checkpoint directory, and kick it all off
  // I could watch this all day!
  //ssc.checkpoint("C:/checkpoint/")
  ssc.start()
  ssc.awaitTermination()

}
