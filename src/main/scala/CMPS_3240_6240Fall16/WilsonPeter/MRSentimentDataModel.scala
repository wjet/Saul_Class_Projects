package CMPS_3240_6240Fall16.WilsonPeter
import edu.illinois.cs.cogcomp.saul.datamodel.DataModel
import scala.collection.JavaConversions._


/**
  * Created by wilso on 11/11/2016.
  */
object MRSentimentDataModel extends DataModel {

  val review = node[Document]

  val words = property(review){
    x: Document =>
      val a = x.getWords.length.toDouble
  }

  val bigrams = property(review) {
    x: Document =>
      val words = x.getWords.toList

      words.sliding(2).map(_.mkString("-")).toList
  }
  val positivityLabel = property(review){
    x: Document => x.getLabel
  }
}
