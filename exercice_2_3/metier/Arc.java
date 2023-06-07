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
	
	private Arc ( String nom, Noeud depart, Noeud arriver, int valeur )
	{
		this.nom        = nom;
		this.depart     = depart;
		this.arriver    = arriver;
		this.valeur     = valeur;
		this.couleur    = null;
	}

	private Arc ( String nom, Noeud depart, Noeud arriver )
	{
		this ( nom, depart,  arriver, 0 );
	}

	public static Arc creerArc ( String nom, Noeud depart, Noeud arriver, int valeur )
	{
		if ( depart == null || arriver == null || valeur < 0 ) return null;

		return new Arc(nom, depart, arriver, valeur);
	}
	
	public static Arc creerArc ( String nom, Noeud depart, Noeud arriver ) 
	{
		if ( depart == null || arriver == null ) return null;
		
		return new Arc ( nom, depart, arriver );
	}

	/* -------------------------------------- */
	/*                Accesseur               */
	/* -------------------------------------- */
	
	public int     getValeur     ( ) { return this.valeur;          }
	public Noeud   getNoeudD     ( ) { return this.depart;          }
	public Noeud   getNoeudA     ( ) { return this.arriver;         }
	public boolean getEstColorie ( ) { return this.couleur != null; }
	public Color   getColorArc   ( ) { return this.couleur;         }
	public String  getNom        ( ) { return this.nom;             }

	/* -------------------------------------- */
	/*                Setteur                 */
	/* -------------------------------------- */

	public void   setValeur  ( int valeur ) { this.valeur  =  valeur; }
	public void   setNoeudD  ( Noeud n    ) { this.depart  = n ;      }
	public void   setNoeudA  ( Noeud n    ) { this.arriver = n;       }
	public void   setColorie ( Color coul ) { this.couleur = coul;    }

	/* -------------------------------------- */
	/*                 Méthode                */
	/* -------------------------------------- */

	public boolean estIdentique ( Arc arc )
	{
		return this.getNoeudD() == arc.getNoeudD() ||
		       this.getNoeudD() == arc.getNoeudA() ||
		       this.getNoeudA() == arc.getNoeudD() ||
		       this.getNoeudA() == arc.getNoeudA();
	}

	public String toString()
	{
		return "Noeud départ " + this.depart.getNom() + " Noeud arrivé " + this.arriver.getNom() + " valeur " + this.valeur;
	}

}