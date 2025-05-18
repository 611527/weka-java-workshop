package workshop.classification;

import weka.core.Instances;

public class Utils {
    public static void printConfusionMatrix(Instances data, double[][] confusionMatrix) {

        String[] classLabels = new String[data.numClasses()];
        for (int i = 0; i < data.numClasses(); i++) {
            classLabels[i] = data.classAttribute().value(i);
        }
        System.out.print("Predicted →\t");
        for (String label : classLabels)
            System.out.print(label + "\t");
        System.out.println();

        for (int i = 0; i < confusionMatrix.length; i++) {
            System.out.print("Actual " + classLabels[i] + ":\t");
            for (int j = 0; j < confusionMatrix[i].length; j++) {
                System.out.print((int) confusionMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
