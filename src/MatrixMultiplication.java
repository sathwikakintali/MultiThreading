class MatrixThread extends Thread {
    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int row;
    private int col;

    public MatrixThread(int[][] A, int[][] B, int[][] C, int row, int col) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.row = row;
        this.col = col;
    }

    public void run() {
        for (int i = 0; i < B.length; i++) {
            C[row][col] += A[row][i] * B[i][col];
        }
    }
}

public class MatrixMultiplication {
    public static void main(String[] args) throws InterruptedException {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int[][] C = new int[A.length][B[0].length];

        MatrixThread[][] threads = new MatrixThread[C.length][C[0].length];

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                threads[i][j] = new MatrixThread(A, B, C, i, j);
                threads[i][j].start();
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                threads[i][j].join();
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
