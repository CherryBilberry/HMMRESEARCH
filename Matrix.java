package hiddenMarkovChain;

public class Matrix {
	double[][] Matrix;
	
	public Matrix(){	
		Matrix = new double[2][2];
		Matrix[0][0] = 0.5;
		Matrix[0][1] = 0.5;
		Matrix[1][0] = 0.5;
		Matrix[1][1] = 0.5;
		
	}
	
	public Matrix(int zz, int zo, int oz, int oo){
		Matrix = new double[2][2];
		Matrix[0][0] = zz;
		Matrix[0][1] = zo;
		Matrix[1][0] = oz;
		Matrix[1][1] = oo;
	}
	
	public double getEntry(int x, int y) throws IndexOutOfBoundsException{
		if( x > 1 || y > 1 || x < 0 || y < 0){
			if( x > 1 || x < 0) System.out.println("x out of boundary");
			if( y > 1 || y < 0) System.out.println("y out of boundary");
			throw new IndexOutOfBoundsException();
		}
		return Matrix[x][y];
	}
	public void renew(int x, int y, double prob) throws IndexOutOfBoundsException{
		if( x > 1 || y > 1 || x < 0 || y < 0){
			if( x > 1 || x < 0) System.out.println("x out of boundary");
			if( y > 1 || y < 0) System.out.println("y out of boundary");
			throw new IndexOutOfBoundsException();
		}		
		Matrix[x][y] = prob;
		Matrix[1 - x][y] = 1 - prob;
	}

	public void printMatrix(){
		System.out.println();
		System.out.println(" __             __");
		System.out.println("|                 |");
		System.out.printf("| %.4f   %.4f |\n", Matrix[0][0], Matrix[0][1]);
		System.out.println("|                 |");
		System.out.println("|                 |");
		System.out.printf("| %.4f   %.4f |\n", Matrix[1][0], Matrix[1][1]);
		System.out.println("|                 |");
		System.out.println("--               -- ");
	}
}
