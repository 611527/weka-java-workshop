package workshop.classification;

public class DecisionTreeClassifier {
    public static void run() throws Exception {
        // TODO: Load the dataset (same as RandomForest)

        // TODO: Initialize the J48 Decision Tree classifier
        // J48 model = new J48();

        // Optional: Set J48 parameters here
        // Try different decision tree configurations by uncommenting one of these:

        // Option 1: Use unpruned tree (could overfit)
        // String[] options = weka.core.Utils.splitOptions("-U");
        // model.setOptions(options);

        // Option 2: Set confidence factor for pruning to 0.25 (default), Minimum Instances per Leaf to 2
        // String[] options = weka.core.Utils.splitOptions("-C 0.25 -M 2");
        // model.setOptions(options);

        // Option 3: Lower confidence factor (more pruning, simpler tree)
        // String[] options = weka.core.Utils.splitOptions("-C 0.1 -M 4");
        // model.setOptions(options);

        // TODO: Build the classifier

        // TODO: Evaluate with 20-fold cross-validation

        // TODO: Output evaluation summary and confusion matrix
    }
}