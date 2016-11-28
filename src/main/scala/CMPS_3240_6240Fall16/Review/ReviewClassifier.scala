package CMPS_3240_6240Fall16.Review

import edu.illinois.cs.cogcomp.lbjava.learn.SparseNetworkLearner
import edu.illinois.cs.cogcomp.saul.classifier.Learnable

/**ERROR: I don't know if this is the source of all errors but it won't import and is definitely needed**/
import CMPS_3240_6240Fall16.Review.ReviewDataModel._



object ReviewClassifiers {
  object ReviewClassifier extends Learnable[Document](docs) {
    def label = ReviewLabel
    override lazy val classifier = new SparseNetworkLearner()
    override def feature = using(wordFeature, bigramFeature, trigramFeature,QuadgramFeature ,ratingFeature)
  }

  object ReviewClassifierWithCache extends Learnable[Document](docs) {
    def label = ReviewLabel
    override lazy val classifier = new SparseNetworkLearner()
    override def feature = using(wordFeature, bigramFeature, trigramFeature, QuadgramFeature, ratingFeature)
    override val useCache = true
  }

  object DeserializedReviewClassifier extends Learnable[Document](docs) {
    def label = ReviewLabel
    override lazy val classifier = new SparseNetworkLearner()
    override def feature = using(wordFeature, bigramFeature, trigramFeature,QuadgramFeature, ratingFeature)
  }
}
