package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import controleur.*;
import metier.*;

public class FrameGraphe extends JFrame
{
	/*-------------*/
	/*--Attributs--*/
	/*-------------*/

	private Controleur  ctrl;
	private PanelGraph  panelGraph;
	private PanelAction panelAction;

	/*----------------*/
	/*--Constructeur--*/
	/*----------------*/

	public FrameGraphe(Controleur ctrl)
	{
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int l = ( tailleEcran.width  - 825 ) / 2;
		int h = ( tailleEcran.height - 825 ) / 2;
		
		this.setSize     ( 825, 825 );
		this.setLocation ( l , h  );
		this.setTitle    ( "Graphe" );

		this.ctrl = ctrl;

		/*Création des composants*/

		this.panelGraph  = new PanelGraph  ( this.ctrl );
		this.panelAction = new PanelAction ( this.ctrl,this );

		/*Placement des composants*/
		this.add ( this.panelGraph                     );
		this.add ( this.panelAction,BorderLayout.SOUTH );

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public Arc getArcAColorier()
	{
		return this.panelGraph.getArcAColorier();
	}

	public boolean estSelectionne()
	{
		return this.panelGraph.estSelectionne();
	}

	public void resetSelect()
	{
		this.panelGraph.resetSelect();
	}
}