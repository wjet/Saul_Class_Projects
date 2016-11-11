package CMPS_3240_6240Fall16

import edu.illinois.cs.cogcomp.lbjava.learn.SparseNetworkLearner
import edu.illinois.cs.cogcomp.saul.classifier.Learnable

/**
  * Created by Parisa on 9/13/16.
  */

object BadgeClassifiers {
  import BadgeDataModel._
  object BadgeClassifier extends Learnable[String](badge) {
    def label = BadgeLabel
    override lazy val classifier = new SparseNetworkLearner()
    override def feature = using(BadgeFeature1)
  }
}