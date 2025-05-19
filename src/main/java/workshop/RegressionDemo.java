package workshop;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.DenseInstance;

import java.util.Random;

public class RegressionDemo {
    public static void run() throws Exception {

        //Step 1: Load dataset
        DataSource source = new DataSource("datasets/cleaned_train.arff");
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);  //Last column = target variable

        //Step 2: Train the model
        LinearRegression model = new LinearRegression();
        model.buildClassifier(data);

        //Step 3: Evaluate using 10-fold cross-validation
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(model, data, 10, new Random(1));

        //Step 4: Output results
        System.out.println("\n=== Linear Regression Summary ===");
        System.out.println(model); // Shows equation
        System.out.println("\n=== Evaluation ===");
        System.out.println(eval.toSummaryString());

        // === Step 5: Predict for a single instance ===
        //Instance sample = data.instance(0);  //Just reuse an existing instance
        //double prediction = model.classifyInstance(sample);
        //System.out.printf("\nPredicted value: %.2f (Actual: %.2f)\n",
        //        prediction, sample.classValue());

        // TODO: Create your own custom input below and test different combinations
        Instance newItem = new DenseInstance(data.numAttributes());
        newItem.setValue(data.attribute("Item_Weight"), 12.0);
        //newItem.setValue(data.attribute("Item_Type"), "Fruits and Vegetables");
        //newItem.setValue(data.attribute("Item_MRP"), 210.0);
        //newItem.setValue(data.attribute("Outlet_Establishment_Year"), 2004);
        //newItem.setValue(data.attribute("Outlet_Size"), "Medium");
        //newItem.setValue(data.attribute("Outlet_Location_Type"), "Tier 2");
        //newItem.setValue(data.attribute("Outlet_Type"), "Supermarket Type1");
        //newItem.setDataset(data); //Must set the dataset structure

        //double predictedSales = model.classifyInstance(newItem);
        //System.out.printf("\nPredicted Item_Outlet_Sales: %.2f%n", predictedSales);
        
        // TODO 1: Change model to RandomForest or IBk
        // import weka.classifiers.trees.RandomForest;
        // model = new RandomForest(); model.buildClassifier(data);

    }
}
