package CMPS_3240_6240Fall16.EmailSpam

import edu.illinois.cs.cogcomp.saul.datamodel.DataModel
import Readers.Document

import scala.collection.JavaConversions._

object SpamDataModel extends DataModel {
  val docs = node[Document]

  val wordFeature = property(docs, "wordF") {
    x: Document => x.getWords.toList
  }

  val bigramFeature = property(docs, "bigram") {
    x: Document =>
      val words = x.getWords.toList

      /** bigram features */
      words.sliding(2).map(_.mkString("-")).toList
  }

  val trigramFeature = property(docs, "trigram"){
    x: Document =>
      val words =x.getWords.toList

      /**trigram features */

      words.sliding(3).map(_.mkString("-")).toList
  }

 /** val ratingFeature = property(docs,"rating"){
    x : Document =>
      val words = x.getWords.toList

      //working on feature for list
  }**/
  val spamLabel = property(docs, "label") {
    x: Document => x.getLabel
  }
}
