# Weka Java Workshop Starter Project

This is a simple console-based Java project using the Weka API to demonstrate:

- Classification (J48)
- Regression (LinearRegression)
- Clustering (KMeans)

## How to Run

### Option 1: Native Java + Maven (Recommended)

1. Install Java and Maven.
2. Open this folder in IntelliJ, Eclipse, or VS Code.
3. Run `Main.java` to start the app.

### Option 2: Docker

1. Install Docker Desktop.
2. Build the image:
   docker build -t weka-workshop .
3. Run the app:
   docker run -it weka-workshop

## Datasets

Place these files in the `datasets/` folder:
- `iris.arff`
- `housing.arff`

## Student Tasks

- Replace J48 with RandomForest or NaiveBayes
- Add evaluation (e.g., 10-fold cross-validation)
- Make number of clusters configurable
- Add logging or support for loading other datasets