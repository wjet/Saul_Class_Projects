package CMPS_3240_6240Fall16.EmailSpam

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable

/**ERROR: I don't know if this is the source of all errors but it won't import and is definitely needed**/
import edu.illinois.cs.cogcomp.saul.learn.SaulWekaWrapper
import weka.classifiers.bayes.NaiveBayes
import edu.illinois.cs.cogcomp.lbjava.learn.SparseNetworkLearner

import Readers.DocumentReader
import Readers.Document
import CMPS_3240_6240Fall16.EmailSpam.{SpamDataModel}



object SpamClassifiers {
  object SpamClassifier extends Learnable[Document](docs) {
    def label = spamLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using(wordFeature)
  }

  object SpamClassifierWithCache extends Learnable[Document](docs) {
    def label = spamLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using(wordFeature)
    override val useCache = true
  }

  object DeserializedSpamClassifier extends Learnable[Document](docs) {
    def label = spamLabel
    override lazy val classifier = new SupportVectorMachine()
    override def feature = using(wordFeature)
  }
}
