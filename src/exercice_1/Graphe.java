import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Stack;
import java.util.Iterator;

public class Graphe
{
	public static void main(String[] args)
	{
		SingleGraph graph = genererGraphe( "graphe" );

		graph.display();
	}

	public static SingleGraph genererGraphe ( String nomFichier )
	{

		SingleGraph graph = new SingleGraph( "Graphe" );

		try
		{
			// Prend la première ligne de notre ficher contenant les noeuds du graph
			Scanner sc = new Scanner ( new FileInputStream ( nomFichier + ".data" ) );

			String[] noeuds = sc.nextLine( ).split ( "\t" );

			for ( String noeud : noeuds )
			{

				int indParOuv  = noeud.indexOf ('(');

				Node n = graph.addNode ( noeud.substring(0, indParOuv) );
				
				int[] coord = Graphe.getCoordonnees ( noeud );
				
				// Attribuer des positions x et y pour les sommets
				n.setAttribute ( "x" , coord[0]);
				n.setAttribute ( "y" , coord[1] );
				n.setAttribute("label",n.getId() + "  (" + n.getAttribute("x") + ";" + n.getAttribute("y")+")");
			}
			
			for (Node node : graph.getNodeSet())
				System.out.println(node);
			
				
			
			// Parcours toutes les lignes à partir de la deuxième pour faire les arêtes
			while (sc.hasNextLine()) 
			{
				String[] attribution = sc.nextLine().split("\t");

				Edge e = graph.addEdge(attribution[0],attribution[1],attribution[2],false);
				
				if(attribution.length > 3)
				{
					e.setAttribute("valeur", Integer.parseInt(attribution[3])); 
					e.setAttribute("label", attribution[3]);
				}

				Graphe.colorier ( "red", e );
			}

			for (Edge edge : graph.getEdgeSet())
				System.out.println(String.format("%-30s", edge) + "\t[" + String.format("%5s", "" + edge.getAttribute("valeur")) + "]");
				
			sc.close ( );
		}
		catch ( Exception e ){ e.printStackTrace ( ); }

		return graph;
	}

	public static int[] getCoordonnees(String point)
	{
		int[]	coordonnees = new int[2];

		//System.out.println(point); //Ligne de débug

		int indParOuv  = point.indexOf ('(');
		int indParFer  = point.indexOf (')');
		int indPoint   = point.indexOf (';');
		
		int x = Integer.parseInt(point.substring (indParOuv  + 1 , indPoint )); 
		int y = Integer.parseInt(point.substring (indPoint   + 1 , indParFer));
		
		coordonnees[0] = x;
		coordonnees[1] = y;
		
		return coordonnees;
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