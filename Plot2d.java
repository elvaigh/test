package project;

import java.awt.*;
import java.awt.geom.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

@SuppressWarnings("serial")
public class Plot2d extends JPanel {

	// Ensemble de points � afficher
	Set <Point> points;

	// Ensemble de lignes � afficher, une ligne est caract�ris�e par deux points repr�sentant ses extremit�s.
	Set <Ligne> lignes;

	public Plot2d(Set <Point> points, Set <Ligne> lignes) {
		super();
		setBackground(Color.white);
		System.out.println("contruction plot");
		this.points = points; System.out.println(points.toString());
		this.lignes = lignes; System.out.println(lignes.toString());
	}

	public Plot2d(Set <Point> points) {
		super();
		setBackground(Color.white);
		this.points = points;
		this.lignes = new HashSet<Ligne>();
	}

	final int PAD = 20;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		// Draw ordinate
		g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
		// Draw abcissa.
		g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
		double xInc = (double)(w - 2*PAD)/(getMaxx());
		double scale = (double)(h - 2*PAD)/getMaxy();

		g2.setPaint(Color.black);

		// Affichage des points
		for (Point p : points){
			double x = PAD + p.getx()*xInc;
			double y = h - PAD - scale*p.gety();
			g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
			g2.drawString(p.toString(), (int) (x-xInc/2.0), (int) (y-3));
		}
	
	// Affichage des lignes
	for (Ligne l : lignes){
		double x1 = PAD + l.getp1().getx()*xInc;
		double y1 = h - PAD - scale*l.getp1().gety();
		double x2 = PAD + l.getp2().getx()*xInc;
		double y2 = h - PAD - scale*l.getp2().gety();
		g2.draw(new Line2D.Double(x1,y1,x2,y2));
	};

	}
	
	
	private double getMaxx() {
		double max = 0;
		for (Point p : points){if (p.getx()>max) max = p.getx();};
		return max;
	}

	private double getMaxy() {
		double max = 0;
		for (Point p : points){if (p.gety()>max) max = p.gety();};
		return max;
	}

	public static void main(String[] args) {
		
		// EXEMPLE d'UTILISATION DE LA CLASSE
		
		// Cr�ation frame
		JFrame f = new JFrame();
		f.setTitle("Algorithmique avanc�e - Visualiseur points et lignes bris�es");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Cr�ation des points
		
		Set <Point> points = new HashSet<Point>();
		
		// Les points peuvent �tre obtenus par lecture d'un fichier de donn�es par :
		Set  <Point> points_lus ;
		//points_lus = Parser.recuperePoints();

		
		// Cr�ation des lignes composanr la ligne bris�e (A VOUS DE JOUER pour trouver la meilleure approximation)
		// Encore une fois, les structues donn�es ici sont � adapter � votre programme.
		Set <Ligne> lignes = new HashSet<Ligne>();
		
		f.add(new Plot2d(points,lignes));
		// si pas de ligne � afficher :
		//f.add(new Plot2d(points)); 

		f.setSize(500,500);
		f.setLocation(200,200);
		f.setVisible(true);
	}
}