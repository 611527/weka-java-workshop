package workshop;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClusteringDemo {
    public static void run() throws Exception {
        Instances data = new DataSource("datasets/iris.arff").getDataSet();

        SimpleKMeans kmeans = new SimpleKMeans();
        kmeans.setNumClusters(3);  // TODO: Make number of clusters configurable
        kmeans.buildClusterer(data);

        System.out.println("=== Clustering Results ===");
        System.out.println(kmeans);
    }
}