package workshop;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;

import java.util.Random;

public class ClassificationDemo {
    public static void run() throws Exception {
        // Load ARFF dataset
        DataSource source = new DataSource("datasets/suspicious_login.arff");
        Instances data = source.getDataSet();

        // Set the class index (IsSuspicious)
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1);

        // Use J48 decision tree classifier (easy to interpret)
        Classifier model = new J48();
        model.buildClassifier(data);

        // Evaluate with 10-fold cross-validation
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(model, data, 20, new Random(1));

        // Output results
        System.out.println(eval.toSummaryString("\nLogin Detection Results\n======\n", false));
        System.out.println("Confusion Matrix:");
        double[][] cmatrix = eval.confusionMatrix();
        for (double[] row : cmatrix) {
            for (double val : row)
                System.out.print((int) val + " ");
            System.out.println();
        }
    }
}
