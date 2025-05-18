package workshop.classification;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class RandomForestClassifier {
    public static void run() throws Exception {
        // Load dataset
        DataSource source = new DataSource("datasets/suspicious_login.arff");
        Instances data = source.getDataSet();
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1);

        // Split into 80% train, 20% test
        int trainSize = (int) Math.round(data.numInstances() * 0.8);
        int testSize = data.numInstances() - trainSize;

        Instances trainData = new Instances(data, 0, trainSize);
        Instances testData = new Instances(data, trainSize, testSize);

        // Initialize RandomForest
        RandomForest model = new RandomForest();

        //default values for RandomForest
        // numTrees = 100
        // maxDepth = 0 (unlimited)
        // numFeatures = 0 (sqrt of numAttributes)
        // numFolds = 0 (no cross-validation)
        // numIterations = 0 (no bagging)
        // numThreads = 1 (single-threaded)

        // TODO: Optional parameter configurations

//         Option Set 1: Small forest (number of trees to 10), shallow trees (max tree depth, fast, possibly underfits)
//         String[] options = weka.core.Utils.splitOptions("-I 10 -depth 5");
//         model.setOptions(options);
//
//         Option Set 2: Medium forest, limit depth (balanced)
//         String[] options = weka.core.Utils.splitOptions("-I 50 -depth 10");
//         model.setOptions(options);
//
//         Option Set 3: Large forest, deeper trees (slower, possibly overfits small data)
//         String[] options = weka.core.Utils.splitOptions("-I 200 -depth 20");
//         model.setOptions(options);

        // Build model
        model.buildClassifier(trainData);

        // Evaluate model
        Evaluation eval = new Evaluation(trainData);
        eval.evaluateModel(model, testData);

        // Output results
        System.out.println(eval.toSummaryString("\n=== Random Forest Results ===\n", false));

        // Print confusion matrix
        Utils.printConfusionMatrix(data, eval.confusionMatrix());

        // Print model options used
        System.out.println("\nModel options: " + weka.core.Utils.joinOptions(model.getOptions()));
    }
}