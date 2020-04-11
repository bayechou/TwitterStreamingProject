package fr.twitter.data

import fr.twitter.util.StatusTweet
import twitter4j.Status
import fr.twitter.util.Constants
/**
 * @constructor create a new TweetData with a screenName, inReplyToStatusId, retweetedStatus and text.
 * @param screenName The screen name of the user
 * @param inReplyToStatusId The ID of an existing status that the update is in reply to
 * @param retweetedStatus the representation of the original Tweet that was retweeted
 * @param content  The text of tweet (actual UTF-8)
 *
 * if tweet.in_reply_to_status_id is not None:
 *           # Tweet is a reply
 *           is_reply = True
 *       else:
 *           # Tweet is not a reply
 *           is_reply = False
 *
 *  If it's a retweet, the tweet will contain a property named retweeted_status. To be complete,
 *  retweeted_status will not appear if the tweet is not a retweet
 */

case class TweetData(screenName: String, inReplyToStatusId: Long, retweetedStatus: Status, content: String) extends DataTrait {

  def getScreenName(): String = screenName

  def getContent(): String = content

  def getStatusTweet(): String = {
    if (inReplyToStatusId != Constants.NONE_REPLY_TO_STATUS_ID)
      StatusTweet.Reply.toString
    else if (retweetedStatus != null)
      StatusTweet.Retweet.toString
    else
      StatusTweet.OriginalTweet.toString
  }

  override def printData() = {
    println(Constants.COL_SCREEN_NAME + " : " + screenName + " Status : " + getStatusTweet())
  }
}
