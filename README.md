# Naive-Bayes Classifier
A simple Naive-Bayes email spam classifier.

This is a spare time project. 

An email spam classifier that uses Apache Lucene to handle document tokenization,lemmatization and stop word elimination.
Posterior probabilities are calculated with the Bayes Theorem and the Laplace smoothing .
Datasets are split in 80% for training and 20% for testing.
In the "datasets" folder there are several ham/spam datasets used as training/test instances. On these datasets the program shows very good results giving:
  ~97% Macroaveraged recall
  ~96% Macroaveraged precision
