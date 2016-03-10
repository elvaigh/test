package project;
/**
 * 
 * @author Equipe p�dagogique d'algorithmique avanc�es 2013-2014
 * 
 */
public class Point {
	/**
	 * Un point est compos� d'une abscisse de type flottant et d'une ordonn�e de type double
	 */
	private double x, y; 

	/**
	 * Constructeur de la classe Point.
	 * @param abs Valeur de l'abscisse du point � cr�er.
	 * @param ord Valeur de l'ordonn�e du point � cr�er.
	 */
	public Point(double abs, double ord){ 
		x = abs;
		y = ord;
	}


	/**
	 * R�cup�ration de la valeur de l'abscisse du point.
	 * @return la valeur de l'abscisse du point.
	 */
	public double getx(){return x;}

	/**
	 * R�cup�ration de la valeur de l'ordonn�e du point.
	 * @return la valeur de l'ordonn�e du point.
	 */
	public double gety(){return y;}


	/**
	 * R�initialisation de l'abscisse.
	 * @param xIn La nouvelle valeur de l'abscisse.
	 */
	public void setx(double xIn){x=xIn;}
	
	/**
	 * R�initialisation de l'ordonn�e.
	 * @param yIn La nouvelle valeur de l'ordonn�e.
	 */
	public void sety(double yIn){x=yIn;}

	/**
	 * R�initialisation du point.
	 * @param abs La nouvelle valeur de l'abscisse.
	 * @param ord La nouvelle valeur de l'ordonn�e.
	 */
	public void initialise(double abs, double ord) {
		x = abs;
		y = ord;
	}

	@Override
	public String toString(){
		return "("+x+","+y+")";
	}

	/**
	 * Deux points sont �gaux s'ils ont la m�me valeur pour abscisse et la m�me valeur pour ordonn�e.
	 * @param obj Objet avec lequel comparer le point courant.
	 * @return vrai si les points sont �gaux, faux sinon.
	 */
	@Override
	public boolean equals(Object obj){
		if (obj==this){return true;}
		if (!(obj instanceof Point)){return false;}
		Point p= (Point) obj;
		return ((x == p.x) && (y == p.y));
	}
	
	
	/**
	 * Affichage de la valeur de this.toString sur la sortie standard.
	 */
	public void affiche() {
		System.out.println(this.toString());
	}
	public Point clone(){
		Point p=new Point(this.x,this.y);
		return p;
	}
}
