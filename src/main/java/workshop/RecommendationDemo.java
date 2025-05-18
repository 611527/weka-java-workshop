package workshop;

import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Evaluation;

import java.util.Random;

public class RecommendationDemo {
    public static void main(String[] args) throws Exception {
        // Load ARFF dataset (user-item-rating)
        DataSource source = new DataSource("datasets/user_item_ratings.arff");
        Instances data = source.getDataSet();

        // Set the target attribute (e.g., rating)
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1);

        // Use KNN (IBk) for regression-based recommendation
        IBk knn = new IBk(3); // K = 3
        knn.buildClassifier(data);

        // Evaluate using 10-fold cross-validation
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(knn, data, 10, new Random(1));

        // Output results
        System.out.println(eval.toSummaryString("\nRecommendation Evaluation Results\n======\n", false));
        System.out.println("Correlation Coefficient: " + eval.correlationCoefficient());
        System.out.println("Mean Absolute Error: " + eval.meanAbsoluteError());
        System.out.println("Root Mean Squared Error: " + eval.rootMeanSquaredError());
    }
}
