package workshop;

import java.util.Scanner;

import workshop.classification.RandomForestClassifier;
import workshop.classification.DecisionTreeClassifier;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Weka Java Workshop ===");
            System.out.println("1. Classification: Random Forest");
            System.out.println("2. Classification: Decision Tree");
            System.out.println("3. Regression");
            System.out.println("4. Clustering");
            System.out.println("5. Recommendation");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    RandomForestClassifier.run();
                    break;
                case 2:
                    DecisionTreeClassifier.run();
                    break;
                case 3:
                    RegressionDemo.run();
                    break;
                case 4:
                    ClusteringDemo.run();
                    break;
                case 5:
                    RecommendationDemo.run();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
            System.out.println();
        }
    }
}
