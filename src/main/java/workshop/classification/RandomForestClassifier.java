package workshop.classification;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Evaluation;

import java.util.Random;

public class RandomForestClassifier {
    public static void run() throws Exception {
        // Load dataset
        DataSource source = new DataSource("datasets/suspicious_login.arff");
        Instances data = source.getDataSet();
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1);

        // Initialize RandomForest
        RandomForest model = new RandomForest();

        // TODO: Optional parameter configurations

        // Option Set 1: Small forest (number of trees to 10), shallow trees (max tree depth, fast, possibly underfits)
        // String[] options = weka.core.Utils.splitOptions("-I 10 -depth 5");
        // model.setOptions(options);

        // Option Set 2: Medium forest, limit depth (balanced)
        // String[] options = weka.core.Utils.splitOptions("-I 50 -depth 10");
        // model.setOptions(options);

        // Option Set 3: Large forest, deeper trees (slower, possibly overfits small data)
        // String[] options = weka.core.Utils.splitOptions("-I 200 -depth 20");
        // model.setOptions(options);

        // Build model
        model.buildClassifier(data);

        // Evaluate model
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(model, data, 20, new Random(1));

        // Output results
        System.out.println(eval.toSummaryString("\n=== Random Forest Results ===\n", false));

        // Print confusion matrix
        Utils.printConfusionMatrix(data, eval.confusionMatrix());

        // Print model options used
        System.out.println("\nModel options: " + weka.core.Utils.joinOptions(model.getOptions()));
    }
}