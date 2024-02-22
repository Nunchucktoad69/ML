import java.util.Arrays;

public class CustomNeuralNetwork {

    public static void main(String[] args) {
        double[] weights = {0.5, 0.5}; // Initial weights
        double learningRate = 0.01;
        int epochs = 1000;

        double[][] inputData = {
                {1, 0, 1},
                {2, 1, 9},
                {0, 1, 1},
                {-2, 1, 7}
        };

        for (int epoch = 0; epoch < epochs; epoch++) {
            double meanSquaredError = computeMeanSquaredError(inputData, weights);
            System.out.println("Epoch " + (epoch + 1) + ": MSE = " + meanSquaredError);

            double[] gradients = computeGradient(inputData, weights);
            updateWeights(weights, gradients, learningRate);
        }

        printResults(weights);
    }

    private static double computeMeanSquaredError(double[][] inputData, double[] weights) {
        int numSamples = inputData.length;
        double sumSquaredError = 0;

        for (int i = 0; i < numSamples; i++) {
            double x1 = inputData[i][0];
            double x2 = inputData[i][1];
            double trueOutput = inputData[i][2];
            double predictedOutput = weights[0] * x1 + weights[1] * x2;

            sumSquaredError += Math.pow(trueOutput - predictedOutput, 2);
        }

        return sumSquaredError / numSamples;
    }

    private static double[] computeGradient(double[][] inputData, double[] weights) {
        int numSamples = inputData.length;
        double[] gradients = new double[weights.length];

        for (int i = 0; i < numSamples; i++) {
            double x1 = inputData[i][0];
            double x2 = inputData[i][1];
            double trueOutput = inputData[i][2];
            double predictedOutput = weights[0] * x1 + weights[1] * x2;

            gradients[0] += -2 * x1 * (trueOutput - predictedOutput);
            gradients[1] += -2 * x2 * (trueOutput - predictedOutput);
        }

        for (int i = 0; i < weights.length; i++) {
            gradients[i] /= numSamples;
        }

        return gradients;
    }

    private static void updateWeights(double[] weights, double[] gradients, double learningRate) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] -= learningRate * gradients[i];
        }
    }

    private static void printResults(double[] weights) {
        System.out.println("Final weights: " + Arrays.toString(weights));
    }
}
