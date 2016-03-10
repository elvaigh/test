package project;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author katiènin
 * @author brahim
 *
 */
public class EssaisSuccessifs {
	private int n;
	private double c=1.5;
	//solution optimale
	private int solOpt[];
	//vecteur solutioon
	private int X[];
	private int Imp[];
	double somcour=0;
	int dernierpris=1;
	int nbpris=1;
	static List<int []> solutions=new ArrayList<int []>();
	//private int Xcour[];
	double coutOpt=0;
	private Set <Point> points = new HashSet<Point>();
	private int nbAppel=0;
	public EssaisSuccessifs (int _n) {
		this.n=_n;
		nbAppel=1;
		points= Parser.recuperePoints();
		initSolOpt();
		initX();
	}
	/**
	 * initialise la solution optimale
	 */
	public void initSolOpt(){
		Imp=new int[n];
		for(int i=0;i<n;i++){
			if(i%2==0){
				Imp[i]=1;
			}
		}
		solOpt=new int[n];
		solOpt=Imp;
		coutOpt=cout(Imp);
		//coutOpt=distance(1,n)+c;
		solOpt[0]=1;
		solOpt[n-1]=1;
		
		
	}
	
	public void initX(){
		X=new int[n];
		for(int i=1;i<n;i++)
		{
			X[i]=0;
		}
		X[0]=1;
	}
	
	public double cout(int[] x){
		double cout=0;
		int drp=1;
		int i=2;
		while(i<=n){
			if(x[i-1]==1){
				cout=cout+distance(drp,i)+c;
				drp=i;
			}
			i++;
		}
		return cout;
	}
	/**
	 * @param  l'abscisse des points d'extrémités du segment 
	 * @return la somme des distance des point dont les abscisse sont entre i,j de la ligne
	 * c=1.5 ici
	 */
	public double distance(double i ,double j){
		Point p1=null,p2=null;
		Ligne l;
		double a,b,c,distance=0;
		if(points.size()>1){
			
			for(Point p : points){
				if(p.getx()==i){
					p1=p;
				}
				if(p.getx()==j){
					p2=p;
				}
			}
			if(p1!=null&&p2!=null){
				a=p2.gety()-p1.gety();
				b=j-i;
				c=p2.gety()*b+i*a;
				l=new Ligne(p1,p2);
				for(Point p : points){
					if(!l.appartient(p)){
						if(l.horsLigne(p)){
							distance=Math.abs(a*p.getx()+b*p.gety()+c)/Math.sqrt(a*a+b*b)+distance;
						}
					}
				}
			}
		}
		return distance;
	}
	void enregistrer(int i,int xi){
		this.X[i-1]=xi;
		if(xi==1){
			somcour=somcour+distance(dernierpris,i);
			dernierpris=i;
			nbpris++;
		}
			
	}
	public boolean solTrouvee(){
		return((X[0]==1)&&(X[n-1]==1));
	}
	public void optimal(int[] x){
		if(cout(x)<coutOpt){
			coutOpt=cout(x);
			solOpt=x;
		}
	}
	//&&(c*(nbpris-1)+somcour<coutOpt)
	public boolean optEncorePosssible(int i){
		if(cout(X)<coutOpt){
			return true;
		}else{
			return false;
		}
	}
	boolean satisfaisant(int xi){
		return X[0]==1;
	}
	public void defaire(int xi,int i){
		
		if(xi==1){
			int j=i-1;
			while(j>1 && X[j-1]!=1){
				j--;
			}
			somcour=somcour-distance(dernierpris,i);
			dernierpris=j;
			nbpris--;
			X[i-1]=0;
		}
			
	} 
	
	public void appligbri(int i){
	
		for(int xi=0;xi<2;xi++){
				enregistrer(i,xi);
				if (solTrouvee()){
					solutions.add(X.clone());
					optimal(X.clone()); 
				}else{
					if(i<n){
						if(optEncorePosssible(i)){
							nbAppel++;
							appligbri(i+1);	
						}
					}
						
				}
				defaire(xi,i);
		}
	}
	
	
	/**
	 * @return the solutions
	 */
	public List<int[]> getSolutions() {
		return solutions;
	}
	public void touteSol(int i){
		for(int xi=0;xi<2;xi++){
				enregistrer(i,xi);
				afficheSols(X);
				if (solTrouvee()){
					solutions.add(X.clone());
					optimal(X.clone()); 
				}else{
					if(i<n){
							touteSol(i+1);	
					}
						
				}
				defaire(xi,i);
		}
				
				
	}
	
	void afficheSols(int[] x){
		for(int i=0;i<n;i++){
			System.out.print(x[i]+" ");
		}
		System.out.println("");
	}
	public int[] getSolOpt() {
		return solOpt;
	}
	public void setSolOpt(int[] solOpt) {
		this.solOpt = solOpt;
	}
	public double getCoutOpt() {
		return coutOpt;
	}
	public void setCoutOpt(double coutOpt) {
		this.coutOpt = coutOpt;
	}
	
	/**
	 * construit la solution optimale
	 * @param sol : tableau contenant la solution optimale
	 */
	
	public void buildSolOpt(int[] sol){
		
		System.out.print("La solution optimale est : ");
		System.out.print("(");
		for(int i=1;i<this.n;i++){
			if (sol[i-1]==1){
				System.out.print(i+", ");
			}
		}
		System.out.print(n+")");
	}
	
	/**
	 * @return the nbAppel
	 */
	public int getNbAppel() {
		return nbAppel;
	}
	
	/**
	 * @return the x : vecteur solution
	 */
	public int[] getX() {
		return X;
	}
	
	public static void main(String[] args) {
		
		EssaisSuccessifs test=new EssaisSuccessifs(Parser.recuperePoints().size());
		test.appligbri(1);

		test.buildSolOpt(test.getSolOpt());
	}
}
