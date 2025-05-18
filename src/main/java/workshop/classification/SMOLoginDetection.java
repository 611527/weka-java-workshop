package workshop.classification;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.SMO;
import weka.classifiers.Evaluation;

import java.util.Random;

public class SMOLoginDetection {
    public static void run() throws Exception {
        // TODO: Load the dataset (same as RandomForest)

        // TODO: Replace the classifier with SMO

        // Optional: Set SMO parameters here
        // TODO: Try different SVM configurations by uncommenting one of these:

        // Option 1: RBF Kernel with gamma = 0.01
        // String[] options = weka.core.Utils.splitOptions("-C 1.0 -K \"weka.classifiers.functions.supportVector.RBFKernel -G 0.01\"");
        // model.setOptions(options);

        // Option 2: Polynomial kernel with exponent 2, C=0.5
        // String[] options = weka.core.Utils.splitOptions("-C 0.5 -K \"weka.classifiers.functions.supportVector.PolyKernel -E 2\"");
        // model.setOptions(options);

        // Option 3: Polynomial kernel with higher exponent (more complexity)
        // String[] options = weka.core.Utils.splitOptions("-C 1.0 -K \"weka.classifiers.functions.supportVector.PolyKernel -E 4\"");
        // model.setOptions(options);

        // Option 4: RBF Kernel with smaller gamma (smoother boundary)
        // String[] options = weka.core.Utils.splitOptions("-C 1.0 -K \"weka.classifiers.functions.supportVector.RBFKernel -G 0.001\"");
        // model.setOptions(options);

        // TODO: Build the classifier

        // TODO: Cross-validate with 20 folds

        // TODO: Output evaluation and confusion matrix
    }
}