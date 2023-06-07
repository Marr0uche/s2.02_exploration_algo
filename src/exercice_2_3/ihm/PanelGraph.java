package ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.List;

import javax.swing.JOptionPane;

import controleur.*;
import metier.*;

public class PanelGraph extends JPanel implements MouseListener 
{

	private Color[] couleurs = {Color.BLACK,Color.BLUE,Color.GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.YELLOW,Color.RED,Color.PINK}; 

	private Controleur  ctrl;
	private List<Arc>   lstArc;
	private List<Noeud> lstNoeud;

	private Noeud actif1;
	private Noeud actif2;

	private boolean selectionne;
	private Arc arcAColorier;

	public PanelGraph(Controleur ctrl) 
	{
		this.ctrl = ctrl;
		this.lstArc = this.ctrl.getArcs();
		this.lstNoeud = this.ctrl.getNoeuds();

		this.actif1 = this.actif2 = null;
		this.selectionne = false;
		this.arcAColorier = null;

		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		Color couleurJouer = this.ctrl.getCouleurJouer();

		g2.drawRect(750, 670, 20, 20);
		g2.setColor(couleurJouer);
		g2.fillRect(750, 670, 20, 20);

		this.dessinerArcs(g2);
		this.dessinerNoeuds(g2);
		this.dessinerNoeudsActifs(g2);
		this.dessinerArcSelectionne(g2);
	}

	private void dessinerArcs(Graphics2D g2) 
	{
		for (Arc arc : lstArc) 
		{
			Color arcColor = arc.getColorArc() == null ? Color.BLACK : arc.getColorArc();
			g2.setColor(arcColor);
			g2.setStroke(new BasicStroke(arc.getColorArc() == null ? 2 : 3));

			Noeud depart = arc.getNoeudD();
			Noeud arrivee = arc.getNoeudA();
			g2.drawLine(depart.getX(), depart.getY() - 100, arrivee.getX(), arrivee.getY() - 100);
		}
	}

	private void dessinerNoeuds(Graphics2D g2) 
	{
		for (Noeud noeud : lstNoeud) 
		{
			dessinerOval(g2, noeud.getX(), noeud.getY(), this.couleurs[noeud.getRegion()-1], Color.BLACK);
		}
		
	}

	private void dessinerNoeudsActifs(Graphics2D g2) 
	{
		drawNode(g2, actif1, Color.RED);
		drawNode(g2, actif2, Color.RED);
	}

	private void drawNode(Graphics2D g2, Noeud node, Color color) 
	{
		if (node != null)
			dessinerOval(g2, node.getX(), node.getY(), Color.WHITE, color);
		
	}

	private void dessinerArcSelectionne(Graphics2D g2) 
	{
		if (actif1 != null && actif2 != null) 
		{
			Arc a = ctrl.arcEntre(actif1, actif2);

			if (a != null) {
				Noeud depart = a.getNoeudD();
				Noeud arrivee = a.getNoeudA();
				g2.drawLine(depart.getX(), depart.getY() - 100, arrivee.getX(), arrivee.getY() - 100);

				drawNode(g2, actif1, Color.RED);
				drawNode(g2, actif2, Color.RED);

				selectionne = true;
				arcAColorier = a;
			} 
			else
			{
				reset(g2); 
			}
			   
			
		}
	}

	public void reset(Graphics2D g2) 
	{
		drawNode(g2, actif1, Color.BLACK);
		drawNode(g2, actif2, Color.BLACK);
		selectionne = false;
		arcAColorier = null;
		actif1 = null;
		actif2 = null;
		repaint();

		SwingUtilities.invokeLater(() -> 
		{
							JOptionPane.showMessageDialog(this,"Essaie encore bg", "Erreur", JOptionPane.ERROR_MESSAGE);
		});
	}

	public void resetSelect() 
	{
		actif1 = null;
		actif2 = null;
		repaint();
	}

	public void dessinerOval(Graphics2D g2, int x, int y, Color coulFond, Color coulTour) 
	{
		g2.setColor(coulFond);
		g2.fillOval(x - 4, y - 104, 10, 10);
		g2.setColor(coulTour);
		g2.drawOval(x - 4, y - 104, 10, 10);
	}

	public boolean estSelectionne() 
	{
		return selectionne;
	}

	public Arc getArcAColorier() 
	{
		return arcAColorier;
	}

	public void mouseClicked(MouseEvent e) 
	{
		for (Noeud noeud : lstNoeud)
			if (e.getX() <= noeud.getX() + 6 && e.getX() >= noeud.getX() - 14 &&
				e.getY() <= noeud.getY() - 94 && e.getY() >= noeud.getY() - 114) 
			{
				if (actif1 == null || (actif1 != null && actif2 != null)) 
				{
					actif1 = noeud;
					actif2 = null;
					repaint();
					return;
				}

				if (actif1 != null && actif2 == null) 
				{
					actif2 = noeud;
					repaint();
					return;
				}
			}
		
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}


// /*package ihm;

// import java.awt.*;
// import java.awt.Color;
// import java.awt.event.*;

// import javax.swing.JPanel;
// import java.util.List;

// import controleur.*;
// import metier.*;


// public class PanelGraph extends JPanel implements MouseListener
// {
// 	/*-------------*/
// 	/*--Attributs--*/
// 	/*-------------*/
// 	/*private Controleur   ctrl;
// 	private List<Arc>    lstArc;
// 	private List<Noeud>  lstNoeud;

// 	private Noeud actif1;
// 	private Noeud actif2;

// 	private boolean selectionne;
// 	private Arc     arcAColorier;*/

// 	/*----------------*/
// 	/*--Constructeur--*/
// 	/*----------------*/
// 	public PanelGraph(Controleur ctrl)
// 	{
// 		this.ctrl = ctrl;

// 		this.lstArc   = this.ctrl.getArcs();
// 		this.lstNoeud = this.ctrl.getNoeuds();

// 		this.actif1 = this.actif2 = null;
// 		this.selectionne  = false;
// 		this.arcAColorier = null;

// 		this.addMouseListener(this);
// 	}


// 	public void paintComponent(Graphics g)
// 	{
// 		super.paintComponent ( g );
// 		Graphics2D g2 = (Graphics2D) g;

// 		Color couleurJouer = this.ctrl.getCouleurJouer();

		
// 		g2.drawRect(750,670,20,20);
// 		g2.setColor(couleurJouer); 
// 		g2.fillRect(750,670,20,20);
// 		g2.drawString("" + this.ctrl.getNbTours(),750,710);

// 		//Dessiner les arêtes en premier 
// 		for (Arc arc : this.lstArc)
// 		{
// 			if (arc.getColorArc() == null)
// 			{
// 				g2.setColor(Color.BLACK);
// 				g2.setStroke(new BasicStroke(2));
// 			}
				
// 			else
// 			{
// 				g2.setColor(arc.getColorArc());
// 				g2.setStroke(new BasicStroke(3));
// 			}
				
			
// 			Noeud depart  = arc.getNoeudD();
// 			Noeud arrivee = arc.getNoeudA();
// 			g2.drawLine(depart.getX(),depart.getY()-100,arrivee.getX(),arrivee.getY()-100);	
// 		}

// 		//Dessiner les sommets 
// 		for (Noeud noeud : this.lstNoeud) 
// 		{
// 			dessinerOval(g2,noeud.getX(),noeud.getY(),Color.WHITE,Color.BLACK);
// 		}

// 		if (this.actif1 != null)
// 		{
// 			System.out.println("actif 1 n'est pas null");
// 			dessinerOval(g2,this.actif1.getX(),this.actif1.getY(),Color.WHITE,Color.RED);
// 		}

// 		if (this.actif2 != null)
// 		{
// 			System.out.println("actif 2 n'est pas null");
// 			dessinerOval(g2,this.actif2.getX(),this.actif2.getY(),Color.WHITE,Color.RED);
// 		}

// 		if (this.actif1 != null && this.actif2 != null) 
// 		{
// 			Arc a = this.ctrl.arcEntre(this.actif1,this.actif2);

// 			if (a != null)
// 			{
// 				Noeud depart  = a.getNoeudD();
// 				Noeud arrivee = a.getNoeudA();
// 				g2.drawLine(depart.getX(),depart.getY()-100,arrivee.getX(),arrivee.getY()-100);

// 				dessinerOval(g2,this.actif1.getX(),this.actif1.getY(),Color.WHITE,Color.RED);

// 				dessinerOval(g2,this.actif2.getX(),this.actif2.getY(),Color.WHITE,Color.RED);

// 				this.selectionne  = true;
// 				this.arcAColorier = a;
// 			}
// 			else
// 			{
// 				reset(g2);
// 			}

			
			

// 		}
// 	}

// 	public void reset(Graphics2D g2)
// 	{
// 		System.out.println("dans reset marche pas");
// 		dessinerOval(g2,this.actif1.getX(),this.actif1.getY(),Color.WHITE,Color.BLACK);
// 		dessinerOval(g2,this.actif2.getX(),this.actif2.getY(),Color.WHITE,Color.BLACK);
// 		this.selectionne  = false;
// 		this.ctrl.getMetier().setArcPrecedent(this.arcAColorier);
// 		this.arcAColorier =  null;
// 		this.actif1 = null;
// 		this.actif2 = null;
// 		this.repaint();
// 	}

// 	public void resetSelect()
// 	{
// 		System.out.println("dans reset marche bon");
// 		this.actif1 = null;
// 		this.actif2 = null;
// 		this.repaint();
// 	}

	
	
// 	public void dessinerOval(Graphics2D g2,int x,int y,Color coulFond,Color coulTour)
// 	{
// 		g2.setColor(coulFond);
// 		g2.fillOval(x-4,y-104,10,10);
// 		g2.setColor(coulTour);
// 		g2.drawOval(x-4,y-104,10,10);
// 	}

// 	public boolean estSelectionne(){return this.selectionne;}
	
// 	public Arc getArcAColorier()
// 	{
// 		return this.arcAColorier;
// 	}

// 	public void mouseClicked(MouseEvent e) 
// 	{
// 		for (Noeud noeud : this.lstNoeud) 
// 		{
// 			if (e.getX() <= noeud.getX()+6 && e.getX() >= noeud.getX()-14 &&
// 				e.getY() <= noeud.getY()-94 && e.getY() >= noeud.getY()-114)
// 			{
// 				System.out.println("trouvé un noeud");
// 				if (this.actif1 == null || (this.actif1 != null && this.actif2 != null))
// 				{
// 					System.out.println("activé le 1");
// 					this.actif1 = noeud;
// 					this.actif2 = null;
// 					this.repaint();
// 					return;
// 				} 

// 				if (this.actif1 != null && this.actif2 == null ) 
// 				{
// 					System.out.println("activé le 2");
// 					this.actif2 = noeud;
// 					this.repaint();
// 					return;	
// 				}

// 			}
				
// 		}
// 	}


// 	public void mousePressed(MouseEvent e) {}


// 	public void mouseReleased(MouseEvent e){}

// 	public void mouseEntered(MouseEvent e) {}

// 	public void mouseExited(MouseEvent e) {}


// }*/
