package CMPS_3240_6240Fall16.Review

import edu.illinois.cs.cogcomp.saul.datamodel.DataModel

import scala.collection.JavaConversions._

object ReviewDataModel extends DataModel {
  val docs = node[MovieDocument]

  val wordFeature = property(docs, "wordF") {
    x: MovieDocument => x.getWords.toList
  }

  val bigramFeature = property(docs, "bigram") {
    x: MovieDocument =>
      val words = x.getWords.toList

      /** bigram features */
      words.sliding(2).map(_.mkString("-")).toList
  }

  val trigramFeature = property(docs, "trigram"){
    x: MovieDocument =>
      val words =x.getWords.toList

      /**trigram features */

      words.sliding(3).map(_.mkString("-")).toList
  }

  val QuadgramFeature = property(docs, "quadgram"){
    x: MovieDocument =>
      val words =x.getWords.toList

      /**trigram features */

      words.sliding(4).map(_.mkString("-")).toList
  }

//WITH NEW FEATURE
 val ratingFeature = property(docs,"buzzOwned"){
    x : MovieDocument => x.getGrade


      //working on feature for list

  }
  val dictionaryFeature = property(docs,"gbAverage"){
    x : MovieDocument => x.getNetGB

  }
  val ReviewLabel = property(docs, "label") {
    x: MovieDocument => x.getLabel
  }
  val LengthFeature = property(docs, "length") {
    x : MovieDocument => x.getWords.toList.length

  }
}
