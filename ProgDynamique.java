package project;

import java.util.HashSet;
import java.util.Set;
/**
 * @author katiènin
 * @author brahim
 *
 */
public class ProgDynamique {
	private int n;
	private double c=1.5;
	private double tab[];
	private int solOpt[];
	private Set <Point> points = new HashSet<Point>();
	private int nbAppel=0;
	
	public ProgDynamique(int _n,Set <Point> _points) {
		n=_n;
		tab=new double[n+1];
		solOpt=new int[n];
		points=_points;
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
		nbAppel++;
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
	
	/**
	 * @param  l'abscisse du point où finit la ligne 
	 * @return SD de (1,j)
	 * c=1.5 ici
	 */
	public double somme(int i,int j){
    	return distance(i,j)+(j-i)*c;
    }
	
	/**
	 * @param : debut=1 et j l'ordre de la récurrence 
	 *  @return le minimum cout 
	 * 
	 */
	public double approx_opt(int debut,int j){
		double min=100000;
		for(int i=1;i<j;i++){
			if(distance(i,j)+tab[i]+c<min){
				min=distance(i,j)+tab[i]+c;
				if(j==n){
					solOpt[i-1]=1;
				}
			}
		}
		
		solOpt[n-1]=1;
		return min;

	}
	/**
	 * 
	 * @param i taille du tableau
	 */
	public void  remplir(){
		tab[0]=0;
		tab[1]=0;
		for(int k=2;k<=n;k++){
			tab[k]=approx_opt(1,k);			
		}
		
	}
	
	public void afficherStrucTab(){
		for(int i=0;i<=n;i++){
			System.out.println(tab[i]);
		}
		for(int i=0;i<n;i++){
			System.out.println(solOpt[i]);
		}
	}
	/**
	 * @return the points
	 */
	public Set<Point> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Set<Point> points) {
		this.points = points;
	}
	
	/**
	 * @return the nbAppel
	 */
	public int getNbAppel() {
		return nbAppel;
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
	
	public static void main(String[] args) {
		
		ProgDynamique p=new ProgDynamique(Parser.recuperePoints().size(),Parser.recuperePoints());
		p.remplir();
		p.buildSolOpt(p.solOpt);
		
	
	}

}