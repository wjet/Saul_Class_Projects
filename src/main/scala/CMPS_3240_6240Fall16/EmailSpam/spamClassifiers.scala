package CMPS_3240_6240Fall16.EmailSpam

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable

/**ERROR: I don't know if this is the source of all errors but it won't import and is definitely needed**/
import CMPS_3240_6240Fall16.EmailSpam.SpamDataModel._
import Readers.Document



object SpamClassifiers {
  object SpamClassifier extends Learnable[Document](docs) {
    def label = spamLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using(wordFeature, bigramFeature, trigramFeature)
  }

  object SpamClassifierWithCache extends Learnable[Document](docs) {
    def label = spamLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using(wordFeature, bigramFeature, trigramFeature)
    override val useCache = true
  }

  object DeserializedSpamClassifier extends Learnable[Document](docs) {
    def label = spamLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using(wordFeature, bigramFeature, trigramFeature)
  }
}
