package CMPS_3240_6240Fall16.Review

import edu.illinois.cs.cogcomp.saul.datamodel.DataModel

import scala.collection.JavaConversions._

object ReviewDataModel extends DataModel {
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
//WITH NEW FEATURE
 val ratingFeature = property(docs,"buzzOwned"){
    x : Document =>
      val buzzOwned = x.getBuzzwords.toList

       buzzOwned.sliding(2).map(_.mkString("-")).toList
      //working on feature for list

  }
  val ReviewLabel = property(docs, "label") {
    x: Document => x.getLabel
  }
}
