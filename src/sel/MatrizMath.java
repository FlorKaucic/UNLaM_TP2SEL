package sel;

public class MatrizMath {
	
	private static final double errTol = 0.00001;
	
	private double[][] mat = null;
	private int dimF = 0;
	private int dimC = 0;

	public MatrizMath(int f, int c) {
		dimF = f;
		dimC = c;
		this.mat = new double[f][c];
	}

	public MatrizMath() {
		this.dimF = 0;
		this.dimC = 0;
		this.mat = null;
	}

	public MatrizMath(double[][] m){
		this.dimF = m.length;
		this.dimC = m[0].length;
		this.mat = new double[this.dimF][this.dimC];
		for (int i = 0; i < this.dimF; i++) {
			for(int j = 0; j < this.dimC; j++){
				this.mat[i][j] = m[i][j];
			}
		}
	}
	
	public int getDimF(){
		return this.dimF;
	}
	
	public int getDimC(){
		return this.dimC;
	}
	
	public double[][] getMat(){
		return this.mat;
	}
	
	public double getValue(int f, int c){
		return this.mat[f][c];
	}
	
	public double getErrTol(){
		return MatrizMath.errTol;
	}
	
	public void setValue(int f, int c, double value){
		this.mat[f][c] = value;
	}
	
	public MatrizMath sumar(MatrizMath obj) throws DistDimException {
		if (this.dimF != obj.dimF || this.dimC != obj.dimC)
			throw new DistDimException(" Distinta Dimension ");
		MatrizMath aux = new MatrizMath(this.dimF, this.dimC);
		for (int i = 0; i < dimF; i++)
			for (int j = 0; j < dimC; j++)
				aux.mat[i][j] = this.mat[i][j] + obj.mat[i][j];
		return aux;
	}

	public MatrizMath restar(MatrizMath obj) throws DistDimException {
		if (this.dimF != obj.dimF || this.dimC != obj.dimC)
			throw new DistDimException(" Distinta Dimension ");
		MatrizMath aux = new MatrizMath(this.dimF, this.dimC);
		for (int i = 0; i < dimF; i++)
			for (int j = 0; j < dimC; j++)
				aux.mat[i][j] = this.mat[i][j] - obj.mat[i][j];
		return aux;
	}

	public MatrizMath multiplicar(MatrizMath obj) throws DistDimException {
		if (this.dimC != obj.dimF)
			throw new DistDimException(" Distinta Dimension ");
		MatrizMath aux = new MatrizMath(this.dimF, obj.dimC);
		for (int i = 0; i < this.dimF; i++)
			for (int j = 0; j < obj.dimC; j++)
				for (int k = 0; k < this.dimC; k++)
					aux.mat[i][j] += this.mat[i][k] * obj.mat[k][j];
		return aux;
	}

	public VectorMath multiplicar(VectorMath obj) throws DistDimException { 
		if (this.dimC != obj.getDim())
			throw new DistDimException(" Distinta Dimension ");
		double[] aux = new double[this.dimF];
		for (int i = 0; i < this.dimF; i++)
			for (int j = 0; j < this.dimC; j++)
				aux[i] += this.mat[i][j] * obj.vec[j];
		return new VectorMath(aux);
	}
	
	
	public MatrizMath multiplicar(double real) {
		MatrizMath aux = new MatrizMath(this.dimF, this.dimC);
		for (int i = 0; i < this.dimF; i++)
			for (int j = 0; j < this.dimC; j++)
				aux.mat[i][j] += this.mat[i][j] * real;
		return aux;
	}

	public double normaUno()
	{
		double norma1=0;
		for(int i=0;i<this.dimF;i++)
			for(int j=0;j<this.dimC;j++)
				norma1+= this.mat[i][j]>0?this.mat[i][j]:-this.mat[i][j];
		return norma1;
	}
	
	public double normaDos()
	{
		double norma2=0;
		for(int i=0;i<this.dimF;i++)
			for(int j=0;j<this.dimC;j++)
				norma2+= this.mat[i][j]*this.mat[i][j];
		return Math.sqrt(norma2);
	}
	
	public double normaInfinito()
	{
		double aux, normaInf = 0;
		for(int i=0;i<this.dimF;i++)
			for(int j=0;j<this.dimC;j++)
			{	
				aux = this.mat[i][j]>0?this.mat[i][j]:-this.mat[i][j];
				normaInf = normaInf>aux?normaInf:aux;
			}
		return normaInf;
	}
	
	public boolean equals (MatrizMath obj)
	{
		// podria haber creado una matriz auxiliar, pero si necesito ||A-A'||2 <E, entonces
		// al ser la norma, estaria haciendo la raiz cuadrada de la diferencia de los Aij de ambas matrices
		double normaDif = 0;
		for(int i=0;i<this.dimF;i++)
			for(int j=0;j<this.dimC;j++)
				normaDif+= (this.mat[i][j] - obj.mat[i][j])*(this.mat[i][j] - obj.mat[i][j]);
		return Math.sqrt(normaDif)<errTol; //si la norma es mas chica que el error tolerado, son "iguales"
	}
	
	
	@Override
	public String toString() {
		String cadena = "";
		for (int i = 0; i < this.dimF; i++) {
			cadena += "\n";
			for (int j = 0; j < this.dimC; j++)
				cadena += (this.mat[i][j] + "\t");
		}
		return cadena;
	}

}