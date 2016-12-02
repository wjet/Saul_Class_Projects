package CMPS_3240_6240Fall16.Review

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable

/**ERROR: I don't know if this is the source of all errors but it won't import and is definitely needed**/
import CMPS_3240_6240Fall16.Review.ReviewDataModel._



object ReviewClassifiers {
  object ReviewClassifier extends Learnable[MovieDocument](docs) {
    def label = ReviewLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using( wordFeature, bigramFeature, trigramFeature, ratingFeature,QuadgramFeature)
  }

  object ReviewClassifierWithCache extends Learnable[MovieDocument](docs) {
    def label = ReviewLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using( wordFeature, bigramFeature, trigramFeature, ratingFeature,QuadgramFeature)
    override val useCache = true
  }

  object DeserializedReviewClassifier extends Learnable[MovieDocument](docs) {
    def label = ReviewLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using( wordFeature, bigramFeature, trigramFeature, ratingFeature,QuadgramFeature)
  }
}
