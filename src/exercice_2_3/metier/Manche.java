package metier;

import java.awt.Color;

public class Manche
{
	private static boolean estPremierTour = true;

	private int   nbTours;
	private Color couleur;

	public Manche ( )
	{
		this.nbTours = ( int ) ( Math.random ( ) * 6 ) + 5;
		this.couleur = new Color ( ( int ) ( Math.random ( ) * 255 ), ( int ) ( Math.random ( ) * 255 ),( int ) ( Math.random ( ) * 255 ) );
	}

	public int   getNbTours ( )         { return this.nbTours;         }
	public Color getCouleur ( )         { return this.couleur;         }

	public static boolean getEstPremierTour( ) { return Manche.estPremierTour;}

	public static void setEstPremierTour( ) { Manche.estPremierTour = false;}
}