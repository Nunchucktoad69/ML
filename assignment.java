import java.util.Scanner;

class NN {
    private int x;
    private int[] y;
    private double[][][] z;

    public NN(int a, int[] b) {
        this.x = a;
        this.y = b;
        this.z = new double[a - 1][][];
        for (int i = 0; i < a - 1; i++) {
            z[i] = new double[b[i]][b[i + 1]];
        }
    }

    public void m1(int c, int d, int e, double f) {
        if (c < 1 || c >= x || d < 0 || d >= y[c - 1]
                || e < 0 || e >= y[c]) {
            System.out.println("Invalid layer or node index!");
            return;
        }
        z[c - 1][d][e] = f;
    }

    public double m2(int c, int d, int e) {
        if (c < 1 || c >= x || d < 0 || d >= y[c - 1]
                || e < 0 || e >= y[c]) {
            System.out.println("Invalid layer or node index!");
            return 0.0;
        }
        return z[c - 1][d][e];
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of layers: ");
        int a = s.nextInt();

        int[] b = new int[a];
        for (int i = 0; i < a; i++) {
            System.out.print("Enter number of nodes in layer " + (i + 1) + ": ");
            b[i] = s.nextInt();
        }

        NN nn = new NN(a, b);

        for (int i = 1; i < a; i++) {
            for (int j = 0; j < b[i - 1]; j++) {
                for (int k = 0; k < b[i]; k++) {
                    System.out.print("Enter weight for edge from node " + j + " to node " + k + " in layer " + i + ": ");
                    double w = s.nextDouble();
                    nn.m1(i, j, k, w);
                }
            }
        }

        System.out.print("Enter layer index: ");
        int l = s.nextInt();
        System.out.print("Enter node index from: ");
        int n1 = s.nextInt();
        System.out.print("Enter node index to: ");
        int n2 = s.nextInt();

        double w = nn.m2(l, n1, n2);
        System.out.println("Weight of edge from node " + n1 + " to node " + n2 + " in layer " + l + " is " + w);

        s.close();
    }
}