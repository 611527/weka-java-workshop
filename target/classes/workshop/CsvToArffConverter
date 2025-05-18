package workshop;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.Resample;  //Unsupervised version!

import java.io.File;

public class CsvToArffConverter {
    public static void main(String[] args) throws Exception {
        // Step 1: Load the original CSV
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("datasets/Train.csv"));  //Replace with your actual path
        Instances data = loader.getDataSet();

        //Step 2: Remove unwanted columns
        Remove remove = new Remove();
        //Remove 1=Item_Identifier, 3=Item_Type, 4=Item_Fat_Content, 7=Outlet_Identifier
        remove.setAttributeIndices("1,3,4,7");
        remove.setInputFormat(data);
        Instances cleaned = Filter.useFilter(data, remove);

        //Step 3: Set class index for regression target
        cleaned.setClassIndex(cleaned.numAttributes() - 1);  //Usually Item_Outlet_Sales

        //Step 4: Sample down to 10% of rows using unsupervised Resample
        Resample resample = new Resample(); //Unsupervised
        resample.setSampleSizePercent(10);  //Take 10% of the data
        resample.setNoReplacement(true);    //Avoid duplicates
        resample.setInputFormat(cleaned);
        Instances sampled = Filter.useFilter(cleaned, resample);

        //(Optional) Confirm class index again
        sampled.setClassIndex(sampled.numAttributes() - 1);

        //Step 5: Save result to ARFF
        ArffSaver saver = new ArffSaver();
        saver.setInstances(sampled);
        saver.setFile(new File("datasets/cleaned_train.arff"));
        saver.writeBatch();

        System.out.println("Cleaned and sampled ARFF saved to: datasets/cleaned_train.arff");
        System.out.printf("Final row count: %d, columns: %d%n", sampled.numInstances(), sampled.numAttributes());
    }
}
