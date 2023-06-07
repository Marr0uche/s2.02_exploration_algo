package metier;

import java.util.ArrayList;
import java.util.List;

public class Noeud
{
	/* -------------------------------------- */
	/*               Attributs                */
	/* -------------------------------------- */
	
	private Integer 	rgn;
	private String  	nom;
	private int     	x;
	private int     	y;
	private List<Arc> 	lstArc;

	/* -------------------------------------- */
	/*              Constructeur              */
	/* -------------------------------------- */

	public Noeud ( String nom, int x, int y )
	{
		this.nom     = nom;
		this.x       = x;
		this.y       = y;
		this.rgn     = null;
		this.lstArc  = new ArrayList<Arc>();
	}

	/* -------------------------------------- */
	/*                Accesseur               */
	/* -------------------------------------- */

	public String  			getNom        ( ) { return this.nom;             }
	public int     			getX          ( ) { return this.x  ;             }
	public int     			getY          ( ) { return this.y  ;             }
	public int     			getRegion     ( ) { return this.rgn ;            }
	public List<Arc>	    getLstArc     ( ) { return this.lstArc ;         }

	public void setRegion     ( int rgn    ) { this.rgn = rgn;      }

	/* -------------------------------------- */
	/*                 Méthode                */
	/* -------------------------------------- */

	public String toString ( )
	{
		return "Noeud : " + this.nom + "(" + this.x + " : " + this.y + ")";
	}

	public void ajouterArc (Arc a)
	{
		this.lstArc.add(a);
	}
}
