package workshop;

import workshop.classification.RandomForestLoginDetection;
import workshop.classification.SMOLoginDetection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Weka Java Workshop ===");
            System.out.println("1. Classification");
            System.out.println("2. Regression");
            System.out.println("3. Clustering");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 11 -> RandomForestLoginDetection.run();
                case 12 -> SMOLoginDetection.run();
                case 2 -> RegressionDemo.run();
                case 3 -> ClusteringDemo.run();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
            System.out.println();
        }
    }
}