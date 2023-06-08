package metier;

import java.awt.Color;

public class Arc
{
	/* -------------------------------------- */
	/*               Attributs                */
	/* -------------------------------------- */
	
	private Noeud  depart;
	private Noeud  arriver;
	private String nom;
	private Color  couleur;

	private int valeur;
	
	/* -------------------------------------- */
	/*              Constructeur              */
	/* -------------------------------------- */

	/** Constructeur arc
	 * @param nom     nom de l'arc
	 * @param depart  noeud par lequel l'arc part
	 * @param arriver noeud par lequel l'arc repart
	 * @param valeur  points de l'arc
	 */

	private Arc ( String nom, Noeud depart, Noeud arriver, int valeur )
	{
		this.nom        = nom;
		this.depart     = depart;
		this.arriver    = arriver;
		this.valeur     = valeur;
		this.couleur    = null;
	}


	/**
	 * Constructeur arc
	 * 
	 * @param nom     nom de l'arc
	 * @param depart  le noeud de départ de l'arc
	 * @param arriver le noeud d'arriver de l'arc
	 */

	private Arc ( String nom, Noeud depart, Noeud arriver )
	{
		this ( nom, depart,  arriver, 1 );
	}


	/**Méthode qui crée un arc
	 * @param nom nom de l'arc
	 * @param depart  noeud de départ de l'arc
	 * @param arriver noeud d'arriver de l'arc
	 * @param valeur score de l'arc
	 */
	public static Arc creerArc ( String nom, Noeud depart, Noeud arriver, int valeur )
	{
		if ( depart == null || arriver == null || valeur < 0 ) return null;

		return new Arc(nom, depart, arriver, valeur);
	}
	

	/** Méthode qui crée un arc

	 * @param nom nom de l'arc
	 * @param depart noeud de depart de l'arc
	 * @param arriver noeud d'arriver de l'arc
	 */

	public static Arc creerArc ( String nom, Noeud depart, Noeud arriver ) 
	{
		if ( depart == null || arriver == null ) return null;
		
		return new Arc ( nom, depart, arriver );
	}

	/* -------------------------------------- */
	/*                Accesseur               */
	/* -------------------------------------- */
	
	/** * @return score */
	public int     getValeur     ( ) { return this.valeur;          }

	/** * @return noeud de départ */
	public Noeud   getNoeudD     ( ) { return this.depart;          }

	/** * @return noeud d'arrivée */
	public Noeud   getNoeudA     ( ) { return this.arriver;         }

	/** * @return true si l'arc est colorie */
	public boolean getEstColorie ( ) { return this.couleur != null; }

	/** * @return couleur de l'arc */
	public Color   getColorArc   ( ) { return this.couleur;         }

	/** * @return nom de l'arc */
	public String  getNom        ( ) { return this.nom;             }

	/* -------------------------------------- */
	/*                Setteur                 */
	/* -------------------------------------- */

	/** * @param valeur change la valeur de l'arc */
	public void   setValeur  ( int valeur ) { this.valeur  =  valeur; }

	/** * @param n change le noeud de départ de l'arc */
	public void   setNoeudD  ( Noeud n    ) { this.depart  = n;       }

	/** * @param n change le noeud d'arriver de l'arc */
	public void   setNoeudA  ( Noeud n    ) { this.arriver = n;       }

	/** * @param coul change la couleur de l'arc */
	public void   setColorie ( Color coul ) { this.couleur = coul;    }

	/* -------------------------------------- */
	/*                 Méthode                */
	/* -------------------------------------- */


	/**Méthode qui permet de savoir si un arc est identique à un autre
	 * @param arc prend l'arc a teste
	 * @return true si l'arc passé en paramètre est identique
	*/
	public boolean estIdentique ( Arc arc )
	{
		return this.getNoeudD() == arc.getNoeudD() ||
		       this.getNoeudD() == arc.getNoeudA() ||
		       this.getNoeudA() == arc.getNoeudD() ||
		       this.getNoeudA() == arc.getNoeudA();
	}

	/** Méthode toString
	 * @return le nom du noeud de départ et d'arrivée et sa valeur
	 */
	public String toString()
	{
		return "Noeud départ " + this.depart.getNom() + " Noeud arrivé " + this.arriver.getNom() + " valeur " + this.valeur;
	}

}