package controleur;
/** SAE 2.02
  * date : le 06/06/2023
  * @author : Alizéa Lebaron, Mathys Poret, Maximilien Lesterlin ,Mohamad Marrouch
  */
  
  //javac @compile.list -d "%REDIR_JAVA%"
  

import iut.algo.Clavier;
import metier.*;
import ihm.*;
import java.util.List;
import java.awt.Color;

public class Controleur
{
	private GrapheT     metier;
	private	FrameGraphe ihm;
	private Manche      manche;

	public Controleur ( )
	{
		this.metier = new GrapheT     (      );
		this.manche = new Manche      (      );
		this.ihm    = new FrameGraphe ( this );
	}

	public static void main ( String[] arg )
	{
		new Controleur ( );
	}

	public List<Arc>   getArcs    ( ) { return this.metier.getArcs    ( ); }
	public List<Noeud> getNoeuds  ( ) { return this.metier.getNoeuds  ( ); }
	public Color       getCouleur ( ) { return this.manche.getCouleur ( ); }
	public int         getNbTours ( ) { return this.manche.getNbTours ( ); }

	public boolean colorier ( Arc arc, Color couleur ) { return this.metier.colorier ( arc , couleur ); }

	public Arc arcEntre ( Noeud n1, Noeud n2)
	{
		return this.metier.arcEntre(n1,n2);
	}

	public Color getCouleurJouer()
	{
		return this.manche.getCouleur();
	}

	public GrapheT getMetier() { return this.metier; }
}
