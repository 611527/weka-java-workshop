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

        // === NEW SECTION: Split dataset into train and test sets ===
        data.randomize(new Random(1)); // Ensure random order

        int trainSize = (int) Math.round(data.numInstances() * 0.8);
        int testSize = data.numInstances() - trainSize;

        Instances train = new Instances(data, 0, trainSize);
        Instances test = new Instances(data, trainSize, testSize);

        //Step 2: Train a Linear Regression model
        LinearRegression model = new LinearRegression();
        model.buildClassifier(train); // Now trained only on train set

        //Step 3: Evaluate using 10-fold cross-validation (still on full data)
        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(model, train, 10, new Random(1));

        //Step 4: Output model equation & metrics
        System.out.println("\n=== Linear Regression Model (weights) ===");
        System.out.println(model);  // Method 1: Look at large coefficients

        System.out.println("\n=== Evaluation on Training Set ===");
        System.out.println(eval.toSummaryString());

        // === NEW SECTION: Evaluate on the separate test set ===
        Evaluation testEval = new Evaluation(train);
        testEval.evaluateModel(model, test);

        System.out.println("\n=== Evaluation on Test Set (hold-out) ===");
        System.out.println(testEval.toSummaryString());

        // === NEW SECTION: Predicted vs Actual on test set ===
        System.out.println("\n=== Predicted vs Actual (first 20 test instances) ===");
        for (int i = 0; i < Math.min(20, test.numInstances()); i++) {
            Instance inst = test.instance(i);
            double actual = inst.classValue();
            double predicted = model.classifyInstance(inst);
            System.out.printf("Instance %2d: Predicted = %.2f, Actual = %.2f\n", i + 1, predicted, actual);
        }
        //“Looking at these predictions, where do you think the model did well? Where did it fail?”
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

        newItem.setValue(data.attribute("Item_MRP"), 50.0);  // Low price
        double lowPricePrediction = model.classifyInstance(newItem);

        newItem.setValue(data.attribute("Item_MRP"), 300.0); // High price
        double highPricePrediction = model.classifyInstance(newItem);

        System.out.printf("Low MRP Prediction: %.2f\n", lowPricePrediction);
        System.out.printf("High MRP Prediction: %.2f\n", highPricePrediction);

        // === TODO 1: Change Item_MRP to 50.0 or 300.0 and observe change ===
        // newItem.setValue(data.attribute("Item_MRP"), 300.0);

        // === TODO 2: Try a different model (e.g., RandomForest) and compare ===
        // import weka.classifiers.trees.RandomForest;
        // model = new RandomForest(); model.buildClassifier(data);
    }
}
