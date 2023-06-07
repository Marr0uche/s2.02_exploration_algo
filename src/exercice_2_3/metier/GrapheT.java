package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.io.FileInputStream;

public class GrapheT
{
	private List<Noeud>  lstNoeuds;
	private List<Arc>    lstArc;

	public GrapheT ( )
	{
		this.lstNoeuds = new ArrayList<> ( );
		this.lstArc    = new ArrayList<> ( );

		this.genererGraphe ( );
	}

	public void genererGraphe ( )
	{
		try
		{
			// Prend la première ligne de notre ficher contenant les noeuds du graph
			Scanner sc = new Scanner ( new FileInputStream ( "./metier/plan.txt" ) );

			//Pour sauter la ligne "[NOEUDS]"
			sc.nextLine ( );

			String line = sc.nextLine ( );
			
			do
			{
				String[] lineSplit  = line.split ( "\t" );
				
				//Créer le sommet
				this.lstNoeuds.add ( new Noeud ( lineSplit[0], Integer.parseInt ( lineSplit[1] )*7, 100-Integer.parseInt ( lineSplit[2] ) *-7) );
	
				line = sc.nextLine ( );
			}
			while ( !line.equals ( "[REGIONS]" ) && !line.equals ( "" ) );

			/* Regions */
			// Pour passer directement à la ligne de la première région
				   sc.nextLine ( ); // bonne ligne
			line = sc.nextLine ( );

			do
			{
				String[] lineSplit  = line.split ( "\t" );

				int numRegion = Integer.parseInt ( lineSplit[0] );
			
				for ( int cpt = 1 ; cpt < lineSplit.length ; cpt++ ) 
				{
					Noeud n = this.lstNoeuds.get ( Integer.parseInt ( lineSplit[cpt] )- 1 );

					n.setRegion ( numRegion );
				}
				line = sc.nextLine ( );
			}
			while ( !line.equals ( "[ARETES]" ) && !line.equals ( "" ) );
			
			sc.nextLine ( ); 

			// Parcours toutes les lignes à partir de la deuxième pour faire les arêtes
			while ( sc.hasNextLine ( ) ) 
			{
				line = sc.nextLine ( );
				String[] lineSplit  = line.split ( "\t" );

				for ( int cpt = 0 ; cpt < lineSplit.length-1 ; cpt++ )
				{
					int point1 = Integer.parseInt ( lineSplit[cpt]   ) - 1;
					int point2 = Integer.parseInt ( lineSplit[cpt+1] ) - 1;

					Arc a = ( Arc.creerArc ( lineSplit[cpt] + "-" + lineSplit[cpt+1] , this.lstNoeuds.get ( point1 ), this.lstNoeuds.get ( point2 ) ));
					
					this.lstArc.add ( a );
					this.lstNoeuds.get ( point1 ).ajouterArc ( a );
					this.lstNoeuds.get ( point2 ).ajouterArc ( a );
				}
					
			}

			sc.close ( );
		}
		catch ( Exception e ){ e.printStackTrace ( ); }

	}

	public boolean colorier ( Arc arc, Color couleur )
	{
		boolean enCours = true;
		
		// On ne peut pas prendre une arête déjà colorié
		if ( arc.getEstColorie ( ) ) return false;
		
		// On ne peut pas colorier une arête qui croise une autre arête coloriée
		for ( Arc a : this.lstArc ) 
			if ( intersection ( arc, a ) && ( a.getColorArc ( ) != null ) ) enCours = false;

		// On ne peut pas former un cycle
		//if ( cyclique(arc)) enCours = false;
		
		//On ne peut pas tromper mille fois une personne... Non attends.. On ne peut pas..
		if ( !estRelie ( arc ) && !Manche.getEstPremierTour ( ) ) enCours = false; 

		// On retourne true et on applique la couleur
		if ( enCours )
		{
			if ( Manche.getEstPremierTour ( ) )
				Manche.setEstPremierTour ( );
				
			arc.setColorie ( couleur );
			return true;
		}
		else
			return false;

	}
	
	public List<Arc>   getArcs   ( ) { return this.lstArc;    }
	public List<Noeud> getNoeuds ( ) { return this.lstNoeuds; }

	public Arc arcEntre ( Noeud noeudDep, Noeud noeudArr )
	{
		for ( Arc arc : this.lstArc ) {
			if ( noeudDep == arc.getNoeudA ( ) && noeudArr == arc.getNoeudD ( ) ||
				 noeudDep == arc.getNoeudD ( ) && noeudArr == arc.getNoeudA ( ) ) return arc;
		}
		return null;
	}

	public boolean intersection ( Arc arcOg, Arc arcATester )
	{
		Noeud depart  = arcOg.getNoeudD ( );
		Noeud arrivee = arcOg.getNoeudA ( );

		Noeud depart2  = arcATester.getNoeudD ( );
		Noeud arrivee2 = arcATester.getNoeudA ( );

		Line2D lineOg      = new Line2D.Double ( depart .getX ( ), depart. getY ( ), arrivee. getX ( ), arrivee. getY ( ) );
		Line2D lineATester = new Line2D.Double ( depart2.getX ( ), depart2.getY ( ), arrivee2.getX ( ), arrivee2.getY ( ) );

		// Vérification si les lignes se croisent
		if ( lineOg.intersectsLine ( lineATester ) )
		{ 
			// Les arêtes se croisent
			// Vérification si les arêtes sont adjacentes
			if ( arcOg.estIdentique ( arcATester ) ) return false; // Les arêtes sont adjacentes, elles ne se croisent pas
			
			return true; // Les arêtes se croisent
		}
		return false; // Les arêtes ne se croisent pas
	}

	/** Méthode qui indique si l'arc prit en paramètre est rataché aux autres arcs déja colorer
	 * @param a est l'arc qui est sélectionner par l'utilisateur
	 * @return renvoie un booléen qui indique si l'arc fait partie du réseaux des autres arcs colorer
	 */
	private boolean estRelie ( Arc a )
	{
		for ( Arc arc : a.getNoeudD ( ).getLstArc ( ) ) 
			if ( arc != a && arc.getEstColorie ( ) ) return true;

		for ( Arc arc : a.getNoeudA ( ).getLstArc ( ) ) 
			if ( arc != a && arc.getEstColorie ( ) ) return true;

		return false;
	}

	public boolean cyclique ( Arc arcPrincipal )
	{
		List<Arc> ensArcDepart;
		List<Arc> ensArcArrivee;

		List<Arc> ensArcAParcourir;

		List<Arc> ensArcNoeudParcoursD;
		List<Arc> ensArcNoeudParcoursA;
		List<Arc> ensArcNoeud;

		List<Integer> resArc;


		Noeud noeudDepart  = arcPrincipal.getNoeudD();
		Noeud noeudArrivee = arcPrincipal.getNoeudA();

		int cpt = 0;

		ensArcNoeud = new ArrayList<Arc>();


		ensArcAParcourir = new ArrayList<Arc>();
		

		ensArcDepart  = noeudDepart .getLstArc();
		ensArcArrivee = noeudArrivee.getLstArc();


		if ( ensArcDepart .size() <= 1 ) return false;
		if ( ensArcArrivee.size() <= 1 ) return false;


		for ( Arc arc : ensArcDepart )
			ensArcAParcourir.add(arc);


		while ( ensArcAParcourir.size() != 0 )
		{
			
			for ( Arc arc : ensArcAParcourir )
			{
				ensArcNoeudParcoursD = arc.getNoeudA().getLstArc();
				ensArcNoeudParcoursA = arc.getNoeudD().getLstArc();


				for ( Arc a : ensArcNoeudParcoursD )
					ensArcNoeud.add(a);

				for ( Arc a : ensArcNoeudParcoursA )
					ensArcNoeud.add(a);


				resArc = this.parcoursLstArcs( arcPrincipal, arc );

				for ( Integer res : resArc )
				{
					cpt ++;

					if ( res == 1 ) return true;
					if ( res == 0 ) ensArcAParcourir.add(ensArcNoeud.get(cpt));

					ensArcAParcourir.remove(cpt); 
				}

			}

		}


		return false;
	}


	private List<Integer> parcoursLstArcs ( Arc arcPrincipal, Arc arcSecondaire )
	{
		List<Integer> ensRes;
		List<Integer> ensTmp;


		ensRes = new ArrayList<Integer>();

 
		/*
			Retour :
			0 → le cycle continue
			1 → arc principal
			2 → couleur différente de l'arc principal, donc, le cycle est coupé
		*/

		
		// Parcour du graphe en partant du noeud secondaire de départ
		ensTmp = this.parcoursGraphe( arcPrincipal, arcPrincipal.getNoeudD() );

		for ( Integer i : ensTmp )
			ensRes.add(i);


		// Parcour du graphe en partant du noeud secondaire de départ
		ensTmp = this.parcoursGraphe( arcPrincipal, arcPrincipal.getNoeudA() );

		for ( Integer i : ensTmp )
			ensRes.add(i);


		return ensRes;
	}


	private List<Integer> parcoursGraphe ( Arc arcPrincipal, Noeud noeud )
	{
		List<Integer> ensRes = new ArrayList<Integer>();


		for ( Arc arc : noeud.getLstArc() )
		{
			ensRes.add(0);

			if ( arc == arcPrincipal )                             ensRes.add(1);
			if ( arc.getColorArc() != arcPrincipal.getColorArc() ) ensRes.add(2);
		}


		return ensRes;
	}

}
