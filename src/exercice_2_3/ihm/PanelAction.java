package ihm;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import controleur.*;
import metier.Arc;



public class PanelAction extends JPanel implements ActionListener
{
	/*-------------*/
	/*--Attributs--*/
	/*-------------*/
	private Controleur   ctrl;
	private FrameGraphe  frame;

	private JButton      btnColorier;

	/*----------------*/
	/*--Constructeur--*/
	/*----------------*/
	public PanelAction(Controleur ctrl,FrameGraphe frame)
	{

		this.ctrl = ctrl;
		this.frame = frame;
		
		/*Création des composants*/
		this.btnColorier  = new JButton("Colorier");
	

		/*Placement des composants*/

		this.add(btnColorier);

		/*Activation des composants*/
		this.btnColorier.addActionListener(this);
	}


	/* ActionListener */
	public void actionPerformed(ActionEvent e)
	{
		Color couleurJoueur = this.ctrl.getCouleurJouer();
		Arc arcAColorier    = this.frame.getArcAColorier();

		if (this.frame.estSelectionne() && this.ctrl.colorier(arcAColorier,couleurJoueur))
		{
			// System.out.println("ON VA COLORIER L'ARC");
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Essaie encore bg", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		this.frame.resetSelect();
	}
}

