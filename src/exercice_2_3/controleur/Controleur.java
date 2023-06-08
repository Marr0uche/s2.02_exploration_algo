package controleur;
/** SAE 2.02
  * date : le 06/06/2023
  * @author : Alizéa Lebaron, Mathys Poret, Maximilien Lesterlin ,Mohamad Marrouche, Eleonore Bouloché
  */

import iut.algo.Clavier;
import metier.*;
import ihm.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Controleur
{
	private GrapheT     metier;
	private	FrameGraphe ihm;
	private Manche      manche;

	public Controleur ( )
	{
		this.manche = new Manche      (      );
		this.metier = new GrapheT     (      );
		this.ihm    = new FrameGraphe ( this );

		this.manche.nouvellePartie();
	}

	public static void main ( String[] arg )
	{
		new Controleur ( );
	}

	public List<Arc>   getArcs    ( ) { return this.metier.getArcs    ( ); }
	public List<Noeud> getNoeuds  ( ) { return this.metier.getNoeuds  ( ); }
	public Color       getCouleur ( ) { return this.manche.getCouleur ( ); }
	public int         getNbTours ( ) { return Manche.getNbTour       ( ); }
	public GrapheT     getMetier  ( ) { return this.metier;                }

	public int   getNbRegionsVisite ( ) { return this.metier.getRegionvisite ( ).size ( ); }
	public int   getNbArcsColorie   ( ) { return this.metier.getNbArcsColorie ( );         }

	public Color getCouleurJouer ( ) { return this.manche.getCouleur ( ); }

	public Arc     arcEntre ( Noeud n1, Noeud n2 )     { return this.metier.arcEntre ( n1,n2 ); }

	/** Méthode qui permet de colorier un arc en faisant le lien entre l'IHM et le métier
	 * @param arc est l'arc sélectionner dans la partie graphique et l'envoie à la partie métier pour le colorier ou non
	 * @param couleur est la couleur que l'on veut attribuer à l'arc (couleur du joueur )
	 * @return retourne un boolean qui permet de savoir si l'arc a été colorié ou non
	 */
	public boolean colorier ( Arc arc, Color couleur ) { return this.metier.colorier ( arc , couleur ); }

	/** Méthode qui nous indique si on peut changer de tour ou non et le fait si on peut
	 * @return le score du joueur
	 */
	public boolean augmenterTours ( )
	{
		this.manche.augmenterTour( );

		try
		{
			if ( this.manche.getNumTour ( ) >= Manche.getNbTour ( ) )
				this.manche.changerTour ( );

		}catch ( Exception e )
		{
			this.ihm.resetSelect( );
			return false;
		}

		return true;
	}
	
	/** Méthode qui permet de calculer les points du joueur
	 * @return le score du joueur
	 */
	public int calculPoints ( )
	{
		int points = 0;

		for ( Arc arc : this.metier.getArcs ( ) )
			if ( arc.getEstColorie ( ) )
				points += arc.getValeur ( );
		
		points += this.getNbNoeudMaxVisiteParRegion ( ) * this.metier.getRegionvisite ( ).size ( );

		return points;
	}

	/** Méthode qui permet de calculer le nombre de noeuds maximum visité par région
	 * @return int (le nombre de noeuds maximum visité par région)
	 */
	public int getNbNoeudMaxVisiteParRegion() 
	{
		int max = 0;

		for ( int r : this.metier.getRegionvisite ( ) )
		{
			int nbPoint = 0;

			for ( Noeud n : this.metier.getNoeuds ( ) )
				if ( n.getEstVisite ( ) && n.getRegion ( ) == r)
					nbPoint++;

			if ( max < nbPoint )
				max = nbPoint;
		}

		return max;
	}
}
