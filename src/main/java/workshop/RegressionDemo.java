package workshop;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class RegressionDemo {
    public static void run() throws Exception {
        Instances data = new DataSource("datasets/housing.arff").getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        LinearRegression model = new LinearRegression();  // TODO: Try SMOreg
        model.buildClassifier(data);

        System.out.println("=== Regression Model ===");
        System.out.println(model);
    }
}