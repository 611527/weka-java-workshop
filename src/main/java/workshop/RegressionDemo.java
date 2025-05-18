package workshop;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.Normalize;

import java.util.Random;

public class RegressionDemo {

    public static void run() throws Exception {

        //"Why do we set the class index manually?"
        //
        //"What happens if we don’t replace missing values?"
        //
        //"Which feature do you think most affects sales?"
        //
        //"How can you improve this model’s accuracy?"

        // TODO 1: Disable normalization and observe how accuracy changes
        // Hint: comment out normalizeNumeric(data) and rerun

        // TODO 2: Try replacing LinearRegression with another model
        // Hint: Use SMOreg (SVM) or M5P (model tree) for comparison
        // import weka.classifiers.functions.SMOreg;
        // Classifier model = new SMOreg(); model.buildClassifier(data);

        // TODO 3: Change the input values of the new instance
        // Hint: Increase/decrease Item_MRP or change Outlet_Type and see the effect


        //Step 1: Load dataset
        DataSource source = new DataSource("datasets/cleaned_train.arff");
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);  // Last column is the target

        printDatasetHead(data, 5);  //View first 5 rows

        //Step 2: Preprocess
        data = replaceMissing(data);   //Fill missing values
        data = normalizeNumeric(data); //Optional: normalize values

        //Step 3: Train a Linear Regression model
        LinearRegression model = new LinearRegression();
        model.buildClassifier(data);

        //Step 4: Evaluate using 10-fold cross-validation
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(model, data, 10, new Random(1));

        //Step 5: Print evaluation summary ===
        System.out.println("\n=== Model Evaluation ===");
        System.out.println(eval.toSummaryString());

        //Optional: show actual vs predicted for each instance
        //TODO: comment this in/out
        //for (int i = 0; i < 10 && i < data.numInstances(); i++) {
        //    double actual = data.instance(i).classValue();
        //    double predicted = model.classifyInstance(data.instance(i));
        //    System.out.printf("Actual: %.2f, Predicted: %.2f%n", actual, predicted);
        // }

        //Step 6: Predict a new custom item (play with it)
        Instance newItem = new DenseInstance(data.numAttributes());
        newItem.setValue(data.attribute("Item_Weight"), 12.0);
        newItem.setValue(data.attribute("Item_Type"), "Fruits and Vegetables");
        newItem.setValue(data.attribute("Item_MRP"), 210.0);
        newItem.setValue(data.attribute("Outlet_Establishment_Year"), 2004);
        newItem.setValue(data.attribute("Outlet_Size"), "Medium");
        newItem.setValue(data.attribute("Outlet_Location_Type"), "Tier 2");
        newItem.setValue(data.attribute("Outlet_Type"), "Supermarket Type1");
        newItem.setDataset(data); //Must set the dataset structure

        double predictedSales = model.classifyInstance(newItem);
        System.out.printf("\nPredicted Item_Outlet_Sales: %.2f%n", predictedSales);
    }

    //Print first N rows of the dataset
    private static void printDatasetHead(Instances data, int rows) {
        System.out.println("\n=== Preview: First " + rows + " Rows ===");
        for (int i = 0; i < Math.min(rows, data.numInstances()); i++) {
            System.out.println(data.instance(i));
        }
    }

    //Replace missing values with Weka defaults
    private static Instances replaceMissing(Instances data) throws Exception {
        ReplaceMissingValues filter = new ReplaceMissingValues();
        filter.setInputFormat(data);
        return Filter.useFilter(data, filter);
    }

    //Normalize numeric attributes (0–1 range)
    private static Instances normalizeNumeric(Instances data) throws Exception {
        Normalize filter = new Normalize();
        filter.setInputFormat(data);
        return Filter.useFilter(data, filter);
    }
}
