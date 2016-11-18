package CMPS_3240_6240Fall16.WilsonPeter
import edu.illinois.cs.cogcomp.saul.util.Logging
import edu.illinois.cs.cogcomp.saulexamples.data.DocumentReader
import CMPS_3240_6240Fall16.WilsonPeter.MRSentimentDataModel._
import CMPS_3240_6240Fall16.WilsonPeter.MRSentimentClassifiers._
import CMPS_3240_6240Fall16.WilsonPeter.Reader
import scala.collection.JavaConversions._
/**
  * Created by wilso on 11/11/2016.
  */
object MRSentimentApp extends Logging{

  val trainData = new DocumentReader("data/peterriser/train").docs.toList
  val testData = new DocumentReader("data/peterriser/test").docs.toList

  object MRSentimentExperimentType extends Enumeration {

    val TrainAndTest, CacheGraph, TestUsingGraphCache, TestSerialization = Value

  }



  def main(args: Array[String]): Unit = {


    val testType = MRSentimentExperimentType.TrainAndTest



    testType match {

      case MRSentimentExperimentType.TrainAndTest => TrainAndTestMRSentimentClassifier()

      case MRSentimentExperimentType.CacheGraph => MRSentimentClassifierWithGraphCache()

      case MRSentimentExperimentType.TestUsingGraphCache => MRSentimentClassifierFromCache()

      case MRSentimentExperimentType.TestSerialization => MRSentimentClassifierWithSerialization()

    }

  }




  def TrainAndTestMRSentimentClassifier(): Unit = {


    MRSentimentDataModel.review populate trainData

   MRSentimentClassifier.forget()

   MRSentimentClassifier.learn(60)

    MRSentimentClassifier.test(testData)

  }





  val graphCacheFile = "models/temp.model"

  def MRSentimentClassifierWithGraphCache(): Unit = {


    MRSentimentDataModel.review populate trainData

    MRSentimentDataModel.deriveInstances()

    MRSentimentDataModel.write(graphCacheFile)

    MRSentimentlassifierWithCache.learn(60)

    MRSentimentClassifierWithCache.test(testData)

  }





  def MRSentimentClassifierFromCache() {

    MRSentimentDataModel.load(graphCacheFile)

    MRSentimentClassifierWithCache.learn(60)

    MRSentimentClassifierWithCache.test(testData)

  }





  def MRSentimentClassifierWithSerialization(): Unit = {

    MRSentimentDataModel.review populate trainData

    MRSentimentClassifier.learn(60)

    MRSentimentClassifier.save()

    DeserializedSpalassifier.load(MRSentimentClassifier.lcFilePath, MRSentimentClassifier.lexFilePath)

    val predictionsBeforeSerialization = testData.map(MRSentimentClassifier(_))

    val predictionsAfterSerialization = testData.map(DeserializedMRSentimentClassifier(_))
    /**
    logger.info(predictionsBeforeSerialization.mkString("/"))

    logger.info(predictionsAfterSerialization.mkString("/"))

    logger.info(predictionsAfterSerialization.indices.forall(it => predictionsBeforeSerialization(it) == predictionsAfterSerialization(it)).toString)
**/
  }

  object MRSentimentExperimentType extends Enumeration {

    val TrainAndTest, CacheGraph, TestUsingGraphCache, TestSerialization = Value

  }



  def main(args: Array[String]): Unit = {


    val testType = MRSentimentExperimentType.TrainAndTest



    testType match {

      case MRSentimentExperimentType.TrainAndTest => TrainAndTestMRSentimentClassifier()

      case MRSentimentExperimentType.CacheGraph => MRSentimentClassifierWithGraphCache()

      case MRSentimentExperimentType.TestUsingGraphCache => MRSentimentClassifierFromCache()

      case MRSentimentExperimentType.TestSerialization => MRSentimentClassifierWithSerialization()

    }

  }




  def TrainAndTestMRSentimentClassifier(): Unit = {


    MRSentimentDataModel.review populate trainData

    MRSentimentClassifier.forget()

    MRSentimentClassifier.learn(30)

    MRSentimentClassifier.test(testData)

  }



  val graphCacheFile = "models/temp.model"

  def MRSentimentClassifierWithGraphCache(): Unit = {



    MRSentimentDataModel.review populate trainData

    MRSentimentDataModel.deriveInstances()

    MRSentimentDataModel.write(graphCacheFile)

    MRSentimentClassifierWithCache.learn(60)

    MRSentimentClassifierWithCache.test(testData)

  }




  def MRSentimentClassifierFromCache() {

    MRSentimentDataModel.load(graphCacheFile)

    MRSentimentClassifierWithCache.learn(60)

    MRSentimentClassifierWithCache.test(testData)

  }





  def MRSentimentClassifierWithSerialization(): Unit = {

    MRSentimentDataModel.review populate trainData

    MRSentimentClassifier.learn(60)

    MRSentimentClassifier.save()

    DeserializedSpamClassifier.load(MRSentimentClassifier.lcFilePath, MRSentimentClassifier.lexFilePath)

    val predictionsBeforeSerialization = testData.map(MRSentimentClassifier(_))

    val predictionsAfterSerialization = testData.map(DeserializedMRSentimentClassifier(_))

    /**
    logger.info(predictionsBeforeSerialization.mkString("/"))

    logger.info(predictionsAfterSerialization.mkString("/"))

    logger.info(predictionsAfterSerialization.indices.forall(it => predictionsBeforeSerialization(it) == predictionsAfterSerialization(it)).toString)
**/
  }


}
