package workshop;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificationDemo {
    public static void run() throws Exception {
        // Load dataset from the 'datasets' folder
        // Tip: You can replace 'iris.arff' with another .arff file for experimentation
        Instances data = new DataSource("datasets/iris.arff").getDataSet();

        // Set the class index (i.e., target variable) to the last attribute
        // This is important for supervised learning (classification)
        data.setClassIndex(data.numAttributes() - 1);

        // Instantiate a J48 decision tree classifier (similar to C4.5 algorithm)
        J48 tree = new J48();

        // TODO 1: Replace J48 with another classifier, such as NaiveBayes or RandomForest
        // Tip:
        // - import weka.classifiers.bayes.NaiveBayes;
        // - import weka.classifiers.trees.RandomForest;
        // - Replace `J48 tree = new J48();` with the classifier of your choice

        // Train the classifier using the dataset
        tree.buildClassifier(data);

        // TODO 2: Add evaluation using 10-fold cross-validation
        // Tip:
        // - Use weka.classifiers.Evaluation
        // - Example: 
        //   Evaluation eval = new Evaluation(data);
        //   eval.crossValidateModel(tree, data, 10, new Random(1));
        //   System.out.println(eval.toSummaryString());

        // Print the trained model (tree structure)
        System.out.println("=== Decision Tree ===");
        System.out.println(tree);

        // TODO 3: Try this with a different dataset (e.g., weather.arff)
        // - Place new .arff files in the datasets folder
        // - Change the file path in DataSource above
    }
}
