

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Stack;
import java.util.Iterator;
import org.graphstream.ui.view.Viewer;

public class Graphe
{

	public static SingleGraph genererGraphe ( String nomFichier )
	{
		SingleGraph graph = new SingleGraph( "Graphe" );

		try
		{
			// Prend la première ligne de notre ficher contenant les noeuds du graph
			Scanner sc = new Scanner ( new FileInputStream ( "plan.txt" ) );

			//Pour sauter la ligne "[NOEUDS]"
			sc.nextLine();

			String line="";
			line = sc.nextLine();
			
			do
			{
				String[] lineSplit  = line.split("\t");
				
				//Créer le sommet
				Node n = graph.addNode ( lineSplit[0] ) ;

				// Attribuer des positions x et y pour les sommets
				n.setAttribute ( "x" , Integer.parseInt ( lineSplit[1]));
				n.setAttribute ( "y" , 100-Integer.parseInt ( lineSplit[2] ));
				
				line = sc.nextLine();
				//n.setAttribute ("label",n.getId() + "  (" + n.getAttribute("x") + ";" + n.getAttribute("y")+")");
			}
			while ( !line.equals ( "[REGIONS]" ) && !line.equals ( "" ) );

			/* Regions */
			// Pour passer directement à la ligne de la première région
			sc.nextLine(); // bonne ligne
			line = sc.nextLine();

			do
			{
				String[] lineSplit  = line.split("\t");

				int numRegion = Integer.parseInt(lineSplit[0]);
			
				for (int cpt = 1; cpt < lineSplit.length; cpt++) 
				{
					Node n = graph.getNode ( Integer.parseInt ( lineSplit[cpt] )- 1);
					
					n.setAttribute ( "region" , numRegion );
				}
				line = sc.nextLine();
			}
			while ( !line.equals ( "[ARETES]" ) && !line.equals ( "" ) );
			
			sc.nextLine(); 

			// Parcours toutes les lignes à partir de la deuxième pour faire les arêtes
			while (sc.hasNextLine()) 
			{
				line = sc.nextLine();
				String[] lineSplit  = line.split("\t");

				for (int cpt = 0; cpt < lineSplit.length-1; cpt++)
					graph.addEdge( lineSplit[cpt] + "-" + lineSplit[cpt+1], Integer.parseInt(lineSplit[cpt])-1, Integer.parseInt(lineSplit[cpt+1])-1);
			}

			for (Edge edge : graph.getEdgeSet())
				System.out.println(String.format("%-30s", edge) + "\t[" + String.format("%5s", "" + edge.getAttribute("valeur")) + "]");
			
			sc.close ( );
		}
		catch ( Exception e ){ e.printStackTrace ( ); }

		return graph;
	}

	public static boolean colorier(String coul, Edge e)
	{
		try {
			e.setAttribute("ui.style","fill-color:"+coul+";");
			return true;
		}
		catch (Exception ex) { System.out.println("La couleur n'est pas bonne"); return false; }
	}

	public static int getPosX(Node point) { return point.getAttribute("x"); }
	public static int getPosY(Node point) { return point.getAttribute("y"); }

	public static boolean estConnexe(SingleGraph graph)
	{
	 	Node n = graph.getNode((int) Math.random()*graph.getNodeCount());
	
		Stack<Node> s = new Stack<Node>();

		int cpt = 0;
		s.push(n);
		while (!s.empty()) 
		{
			Node u = s.pop();
			
			if ( !u.hasAttribute("marque"))
			{
				u.setAttribute("marque",true);

				Iterator<Node> lesVoisins = u.getNeighborNodeIterator();
				while (lesVoisins.hasNext()) 
				{
					Node v = lesVoisins.next();
					if (!v.hasAttribute("marque")) {
						s.push(v);
					}
				}
				cpt++;
			}
			
		}

		if (cpt >= graph.getNodeCount())
			return true;
		else 
			return false;
	}

}