package workshop;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.converters.ConverterUtils.DataSource;

// Uncomment these when needed
// import weka.attributeSelection.AttributeSelection;
// import weka.attributeSelection.Ranker;
// import weka.attributeSelection.CorrelationAttributeEval;
// import weka.filters.Filter;
// import weka.filters.unsupervised.attribute.Remove;

import java.util.Random;

public class RegressionDemo {
    public static void run() throws Exception {

        //Step 1: Load dataset
        DataSource source = new DataSource("datasets/cleaned_train.arff");
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1); // Last column = target

        //Step 2: Train a Linear Regression model
        LinearRegression model = new LinearRegression();
        model.buildClassifier(data);

        //Step 3: Evaluate using 10-fold cross-validatio
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(model, data, 10, new Random(1));

        //Step 4: Output model equation & metrics
        System.out.println("\n=== Linear Regression Model (weights) ===");
        System.out.println(model);  // Method 1: Look at large coefficients

        System.out.println("\n=== Evaluation ===");
        System.out.println(eval.toSummaryString());

        //Method 2: Rank features by correlation with target
        //Uncomment this block to try:
        /*
        AttributeSelection selector = new AttributeSelection();
        CorrelationAttributeEval evaluator = new CorrelationAttributeEval();
        Ranker search = new Ranker();
        selector.setEvaluator(evaluator);
        selector.setSearch(search);
        selector.SelectAttributes(data);

        int[] rankedAttributes = selector.selectedAttributes();
        System.out.println("\n=== Ranked Features by Correlation ===");
        for (int index : rankedAttributes) {
            if (index != data.classIndex()) {
                System.out.println("- " + data.attribute(index).name());
            }
        }
        */

        //Try removing a feature (e.g., Item_MRP) and compare accuracy ===
        //Uncomment this to experiment with feature removal:
        /*
        Remove remove = new Remove();
        remove.setAttributeIndices("3");  // Example: 3rd attribute (1-based index)
        remove.setInputFormat(data);
        Instances reduced = Filter.useFilter(data, remove);

        LinearRegression reducedModel = new LinearRegression();
        reducedModel.buildClassifier(reduced);

        Evaluation reducedEval = new Evaluation(reduced);
        reducedEval.crossValidateModel(reducedModel, reduced, 10, new Random(1));

        System.out.println("\n=== Model after Removing Attribute 3 ===");
        System.out.println(reducedEval.toSummaryString());
        */

        //Predict for a sample & change one value at a time
        Instance newItem = new DenseInstance(data.numAttributes());
        newItem.setDataset(data); //Needed to attach structure

        newItem.setValue(data.attribute("Item_Weight"), 12.0);
        newItem.setValue(data.attribute("Item_Type"), "Fruits and Vegetables");
        newItem.setValue(data.attribute("Item_MRP"), 210.0);
        newItem.setValue(data.attribute("Outlet_Establishment_Year"), 2004);
        newItem.setValue(data.attribute("Outlet_Size"), "Medium");
        newItem.setValue(data.attribute("Outlet_Location_Type"), "Tier 2");
        newItem.setValue(data.attribute("Outlet_Type"), "Supermarket Type1");

        double predicted = model.classifyInstance(newItem);
        System.out.printf("\nPredicted Item_Outlet_Sales: %.2f\n", predicted);

        // === TODO 1: Change Item_MRP to 50.0 or 300.0 and observe change ===
        // newItem.setValue(data.attribute("Item_MRP"), 300.0);

        // === TODO 2: Try a different model (e.g., RandomForest) and compare ===
        // import weka.classifiers.trees.RandomForest;
        // model = new RandomForest(); model.buildClassifier(data);
    }
}
