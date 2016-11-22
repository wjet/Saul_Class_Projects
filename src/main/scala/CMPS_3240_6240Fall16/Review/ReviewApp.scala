package CMPS_3240_6240Fall16.Review

import CMPS_3240_6240Fall16.Review.ReviewClassifiers.{DeserializedReviewClassifier, ReviewClassifier, ReviewClassifierWithCache}
import edu.illinois.cs.cogcomp.saul.util.Logging

import scala.collection.JavaConversions._

object ReviewApp extends Logging {
  val starta = 0
  val enda = 1
  val startb = 2
  val endb = 3
  val trainDataGood = new Reader(starta,starta,"GOOD").docs.toList
  val trainDataBad = new Reader(enda,enda,"BAD").docs.toList
  val trainData = trainDataGood ++ trainDataBad
  val testDataGood = new Reader(startb,startb, "GOOD").docs.toList
  val testDataBad = new Reader(endb,endb,"BAD").docs.toList
  val testData = testDataGood ++ testDataBad

  object ReviewExperimentType extends Enumeration {
    val TrainAndTest, CacheGraph, TestUsingGraphCache, TestSerialization = Value
  }

  def main(args: Array[String]): Unit = {
    /** Choose the experiment you're interested in by changing the following line */
    val testType = ReviewExperimentType.TrainAndTest

    testType match {
      case ReviewExperimentType.TrainAndTest => TrainAndTestReviewClassifier()
      case ReviewExperimentType.CacheGraph => ReviewClassifierWithGraphCache()
      case ReviewExperimentType.TestUsingGraphCache => ReviewClassifierFromCache()
      case ReviewExperimentType.TestSerialization => ReviewClassifierWithSerialization()
    }

  }

  /** A standard method for testing the Review Classification problem. Simply training and testing the resulting model.*/
  def TrainAndTestReviewClassifier(): Unit = {
    /** Defining the data and specifying it's location  */
    ReviewDataModel.docs populate trainData
    ReviewClassifier.learn(30)
    ReviewClassifier.test(testData)
  }

  /** Review Classifcation, followd by caching the data-model graph. */
  val graphCacheFile = "models/temp.model"
  def ReviewClassifierWithGraphCache(): Unit = {
    /** Defining the data and specifying it's location  */
    ReviewDataModel.docs populate trainData
    ReviewDataModel.deriveInstances()
    ReviewDataModel.write(graphCacheFile)
    ReviewClassifierWithCache.learn(30)

    ReviewClassifierWithCache.test(testData)
  }

  /** Testing the functionality of the cache. `ReviewClassifierWithCache` produces the temporary model file need for
    * this methdd to run.
    */
  def ReviewClassifierFromCache() {
    ReviewDataModel.load(graphCacheFile)
    ReviewClassifierWithCache.learn(30)

    ReviewClassifierWithCache.test(testData)
  }

  /** Testing the serialization functionality of the model. We first train a model and save it. Then we load the model
    * and use it for prediction. We later check whether the predictions of the deserialized model are the same as the
    * predictions before serialization.
    */
  def ReviewClassifierWithSerialization(): Unit = {
    ReviewDataModel.docs populate trainData
    ReviewClassifier.learn(30)
    ReviewClassifier.save()
    DeserializedReviewClassifier.load(ReviewClassifier.lcFilePath, ReviewClassifier.lexFilePath)
    val predictionsBeforeSerialization = testData.map(ReviewClassifier(_))
    val predictionsAfterSerialization = testData.map(DeserializedReviewClassifier(_))
    logger.info(predictionsBeforeSerialization.mkString("/"))
    logger.info(predictionsAfterSerialization.mkString("/"))
    logger.info(predictionsAfterSerialization.indices.forall(it => predictionsBeforeSerialization(it) == predictionsAfterSerialization(it)).toString)
  }
}
