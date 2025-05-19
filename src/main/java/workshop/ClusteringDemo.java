package workshop;
 
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
 
public class ClusteringDemo {
    public static void run() throws Exception {
        System.out.println("Running ClusteringDemo...");
 
        // Load dataset
        DataSource source = new DataSource("datasets/clustering_data.arff");
        Instances data = source.getDataSet();
 
        // Initialize clusterer
        SimpleKMeans model = new SimpleKMeans();
        model.setNumClusters(3);
        model.setSeed(1);
        model.setPreserveInstancesOrder(true);
 
        // Build the model
        model.buildClusterer(data);
 
        // Output
        System.out.println("\nClustering Results\n======\n");
        System.out.println("Number of clusters: " + model.getNumClusters());
        System.out.println("Cluster centroids:\n" + model);
 
        // Assignments
        int[] assignments = model.getAssignments();
        System.out.println("Instance Cluster Assignments:");
        for (int i = 0; i < assignments.length; i++) {
            System.out.printf("Instance %d -> Cluster %d\n", i, assignments[i]);
        }
    }
}