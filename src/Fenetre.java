import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {
    
	private Container c;
	private Tabpiece pieces, montant; //piece est le tableau qui réprésente la caisse, montant représente ce que l'acheteur va payer
	private JLabel messageAjout, messageRetrait, montantSimulation;//le texte des différents onglets qui informent sur le déroulement des actions
	private JPanel jPanSouth;
	private JTextField qteAjout,valAjout,qteSuppr,valSuppr, prix;//Les champs textes éditables par l'utilisateur
	private String caisse;
	private JTextArea textAreaCaisse, message;//Ces champs informent sur la caisse et les message de déroulement de la simulation, on utilise des textarea pour une raison de lisibilité (sauts de lignes)
	private JTextField tabJTextField[];
    
	public Fenetre(String titre){
    	super(titre);
    	pieces = new Tabpiece();
    	montant = new Tabpiece();
    	this.initialise();
    	this.setBounds(200,200,800,700);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setVisible(true);
    	}
    
	public void initialise(){
   	 
     	this.c= this.getContentPane();
     	c.setLayout(new BorderLayout());
    	 
     	//Creation de la barre menu
     	JMenuBar menuBar = new JMenuBar();

     	JMenuItem bouttonSauvegarder = new JMenuItem("Sauvegarder");
     	menuBar.add(bouttonSauvegarder);
     	bouttonSauvegarder.addActionListener(this);

     	JMenuItem bouttonCharger = new JMenuItem("Charger");
     	menuBar.add(bouttonCharger);
     	bouttonCharger.addActionListener(this);

     	JMenuItem bouttonZero = new JMenuItem("Braquer la boulangerie (Mettre à zéro)");
     	menuBar.add(bouttonZero);
     	bouttonZero.addActionListener(this);
    	 
     	setJMenuBar(menuBar);
    	 
     	caisse = pieces.toString();

     	//Création des onglets
     	JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);

     	JPanel ongletAjoutPiece = new JPanel();
     	JPanel ongletSuppressionPiece = new JPanel();
     	JPanel ongletSimulation = new JPanel();

     	onglets.addTab("Ajouter Pièces", ongletAjoutPiece);
     	onglets.addTab("Retirer Pièces", ongletSuppressionPiece);
     	onglets.addTab("Simulation", ongletSimulation);
    	 
     	c.add(onglets);
    	 

     	//Création de l'onglet Ajouter pieces
     	ongletAjoutPiece.setLayout(new GridLayout(4,1));
    	 
     	qteAjout = new JTextField();
     	valAjout = new JTextField();
     	qteAjout.setColumns(10);
     	valAjout.setColumns(10);
    	 
     	JLabel qeAjout = new JLabel("En quelle quantité ?");
     	JLabel varAjout = new JLabel("Quelle pièce/billet voulez vous ajouter à la caisse (en euro) ?");
     	messageAjout = new JLabel("");
             	 
     	JPanel ajoutPiece1= new JPanel();
     	JPanel ajoutPiece2 = new JPanel();
     	JPanel ajoutPiece3 = new JPanel();
    	 
     	ongletAjoutPiece.add(ajoutPiece1);
     	ongletAjoutPiece.add(ajoutPiece2);
     	ongletAjoutPiece.add(ajoutPiece3);
     	ongletAjoutPiece.add(messageAjout);
    	 
     	JButton bouttonAjouterPiece = new JButton("Ajouter des pièces");
     	bouttonAjouterPiece.addActionListener(this);
    	 
     	ajoutPiece1.add(varAjout);
     	ajoutPiece1.add(valAjout);
     	ajoutPiece2.add(qeAjout);
     	ajoutPiece2.add(qteAjout);
     	ajoutPiece3.add(bouttonAjouterPiece);
    	 
    	 
     	//Création de l'onglet Retirer pieces
     	ongletSuppressionPiece.setLayout(new GridLayout(4,1));
     	qteSuppr = new JTextField();
     	valSuppr = new JTextField();
     	qteSuppr.setColumns(10);
     	valSuppr.setColumns(10);
     	JLabel qeSuppr = new JLabel("En quelle quantité ?");
     	JLabel varSuppr = new JLabel("Quelle pièce/billet voulez vous retirer de la caisse (en euro) ?");
     	messageRetrait = new JLabel("");
             	 
     	JPanel supprPiece1= new JPanel();
     	JPanel supprPiece2 = new JPanel();
     	JPanel supprPiece3 = new JPanel();
    	 
     	ongletSuppressionPiece.add(supprPiece1);
     	ongletSuppressionPiece.add(supprPiece2);
     	ongletSuppressionPiece.add(supprPiece3);
     	ongletSuppressionPiece.add(messageRetrait);
    	 
     	JButton bouttonSupprPiece = new JButton("Retirer des pièces");
     	bouttonSupprPiece.addActionListener(this);
    	 
     	supprPiece1.add(varSuppr);
     	supprPiece1.add(valSuppr);
     	supprPiece2.add(qeSuppr);
     	supprPiece2.add(qteSuppr);
     	supprPiece3.add(bouttonSupprPiece);
    	 
     	//Création de l'onglet Simulation
     	ongletSimulation.setLayout(new GridLayout(4,1));
     	JPanel jpanSim2 = new JPanel();
    	 
     	JTextArea dialogue = new JTextArea("-Bonjour monsieur et bienvenue à la baguette du béret.\n"+"-Bonjour! Je voudrais un croissant et deux pains au chocolat.\n Mettez y aussi un paquet de Dragibus pour Monsieur Hebert.\n"+"-Voilà, ça vous fait : ");
     	dialogue.setEditable(false);
    	 
     	prix = new JTextField();
     	prix.setColumns(10);
    	 
     	montantSimulation = new JLabel("");
     	JLabel montantTxt = new JLabel("Montant actuelle : ");
     	JLabel euro = new JLabel("euro,");
    	 
     	jpanSim2.add(prix);
     	jpanSim2.add(euro);
     	jpanSim2.add(montantTxt);
     	jpanSim2.add(montantSimulation);
    	 
     	ongletSimulation.add(dialogue);
     	ongletSimulation.add(jpanSim2);
    	 
     	//boutons et messages de la simulation
     	JButton ajouterPaiement = new JButton("Ajouter au montant");
     	JButton payer = new JButton("Payer");
     	JButton cancel = new JButton("Annuler le montant");
    	 
     	cancel.addActionListener(this);
     	ajouterPaiement.addActionListener(this);
     	payer.addActionListener(this);
    	 
     	JPanel rendreArgent = new JPanel();
     	rendreArgent.setLayout(new GridLayout(3,15));
    	 
     	jpanSim2.add(ajouterPaiement);
     	jpanSim2.add(payer);
     	jpanSim2.add(cancel);
    	 
     	message = new JTextArea("");
     	message.setEditable(false);
    	 
     	ongletSimulation.add(message);
     	ongletSimulation.add(rendreArgent);
    	 
     	//Dans l'onglet Simulation, toutes les pieces/billets ...
     	JPanel piece1 = new JPanel();
     	JPanel piece2 = new JPanel();
     	JPanel piece5 = new JPanel();
     	JPanel piece10 = new JPanel();
     	JPanel piece20 = new JPanel();
     	JPanel piece50 = new JPanel();
     	JPanel piece100 = new JPanel();
     	JPanel piece200 = new JPanel();
     	JPanel piece500 = new JPanel();
     	JPanel piece1000 = new JPanel();
     	JPanel piece2000 = new JPanel();
     	JPanel piece5000 = new JPanel();
     	JPanel piece10000 = new JPanel();
     	JPanel piece20000 = new JPanel();
     	JPanel piece50000 = new JPanel();
    	 
     	JTextField piece1txt = new JTextField();
     	JTextField piece2txt = new JTextField();
     	JTextField piece5txt = new JTextField();
     	JTextField piece10txt = new JTextField();
     	JTextField piece20txt = new JTextField();
     	JTextField piece50txt = new JTextField();
     	JTextField piece100txt = new JTextField();
     	JTextField piece200txt = new JTextField();
     	JTextField piece500txt = new JTextField();
     	JTextField piece1000txt = new JTextField();
     	JTextField piece2000txt = new JTextField();
     	JTextField piece5000txt = new JTextField();
     	JTextField piece10000txt = new JTextField();
     	JTextField piece20000txt = new JTextField();
     	JTextField piece50000txt = new JTextField();
    	 
     	JLabel jlab1 = new JLabel ("1 centime");
     	JLabel jlab2 = new JLabel ("2 centime");
     	JLabel jlab5 = new JLabel ("5 centime");
     	JLabel jlab10 = new JLabel ("10 centime");
     	JLabel jlab20 = new JLabel ("20 centime");
     	JLabel jlab50 = new JLabel ("50 centime");
     	JLabel jlab100 = new JLabel ("1 euro");
     	JLabel jlab200 = new JLabel ("2 euro");
     	JLabel jlab500 = new JLabel ("5 euro");
     	JLabel jlab1000 = new JLabel ("10 euro");
     	JLabel jlab2000 = new JLabel ("20 euro");
     	JLabel jlab5000 = new JLabel ("50 euro");
     	JLabel jlab10000 = new JLabel ("100 euro");
     	JLabel jlab20000 = new JLabel ("200 euro");
     	JLabel jlab50000 = new JLabel ("500 euro");
    	 
     	JLabel jlabTab[] = {jlab1,jlab2,jlab5,jlab10,jlab20,jlab50,jlab100,jlab200,jlab500,jlab1000,jlab2000,jlab5000,jlab10000,jlab20000,jlab50000,};
     	tabJTextField = new JTextField[] {piece1txt,piece2txt,piece5txt,piece10txt,piece20txt,piece50txt,piece100txt,piece200txt,piece500txt,piece1000txt,piece2000txt,piece5000txt,piece10000txt,piece20000txt,piece50000txt};
     	JPanel tabJPanel[] = {piece1,piece2,piece5,piece10,piece20,piece50,piece100,piece200,piece500,piece1000,piece2000,piece5000,piece10000,piece20000,piece50000};
     	for (int i=0;i<tabJPanel.length;i++){
         	rendreArgent.add(tabJPanel[i]);
         	tabJTextField[i].setColumns(10);
         	tabJPanel[i].add(jlabTab[i]);
         	tabJPanel[i].add(tabJTextField[i]);     	 
     	}
     	//fin de la création des pieces/billets dans l'onglet Simulation
    	 
    	 
     	//Création de la caisse
    	 
     	textAreaCaisse = new JTextArea(caisse);
     	textAreaCaisse.setEditable(false);
    	 
     	jPanSouth = new JPanel();
     	jPanSouth.add(textAreaCaisse);
    	 
     	c.add("South", jPanSouth);
	}
    
//Les écouteurs
 	public void actionPerformed(ActionEvent e){
     	String s=e.getActionCommand();

     	switch (s){
   	 
     	case "Sauvegarder" :
   		  pieces.sauvegarder(pieces);
   		  JOptionPane.showMessageDialog(null,"L'état de la caisse a été sauvegardé");
     	break;

     	case "Charger" :
   		  pieces=pieces.charger();
         	caisse = pieces.toString();
         	textAreaCaisse.setText(caisse);
         	JOptionPane.showMessageDialog(null,"L'état de la caisse a été chargé");
     	break;
    	 
     	case "Ajouter des pièces" ://On ajoute des pieces au tableau pieces
         	int quantiteAjout;
         	float valeurAjout;
        	 
         	try{
             	quantiteAjout = Integer.parseInt(qteAjout.getText());
             	valeurAjout = Float.parseFloat(valAjout.getText());
             	valeurAjout *= 100;
             	}
        	 
         	catch(NumberFormatException nfe){
             	messageAjout.setText("Les valeurs entrés ne sont pas correctes");
             	qteAjout.setText("");
             	valAjout.setText("");
             	break;
             	}
        	 
         	if (pieces.existe((int)valeurAjout)){
             	pieces.ajouter((int) valeurAjout, quantiteAjout);
             	messageAjout.setText("Les pieces/billets ont été ajoutés");
             	caisse = pieces.toString();
             	textAreaCaisse.setText(caisse);
             	qteAjout.setText("");
             	valAjout.setText("");
             	break;
         	}
         	else{
       		  messageAjout.setText("Cette piece ou billet n'existe pas");
       		  break;
        		 }

       	 
       	 
     	case "Retirer des pièces" ://On retire x pieces du tableau pieces, si x est superieur aux pieces présentes, automtiquement mis à 0
         	int quantiteRetrait;
         	float valeurRetrait;
         	try{
             	quantiteRetrait = Integer.parseInt(qteSuppr.getText());
             	valeurRetrait = Float.parseFloat(valSuppr.getText());
             	valeurRetrait *= 100;
             	}
       	 
         	catch(NumberFormatException nfe){
             	messageRetrait.setText("Les valeurs entrés ne sont pas correctes");
             	qteSuppr.setText("");
             	valSuppr.setText("");
             	break;
             	}
       	 
         	if (pieces.existe((int)valeurRetrait)){
             	pieces.supprimer((int) valeurRetrait, quantiteRetrait);
             	messageRetrait.setText("Les pieces/billets ont été retirés");
             	caisse = pieces.toString();
             	textAreaCaisse.setText(caisse);
             	qteSuppr.setText("");
             	valSuppr.setText("");
             	break;}
       	 
         	else{
       		  messageRetrait.setText("Cette piece ou billet n'existe pas");
       		  break;
       		  }
       	 
     	case "Ajouter au montant" ://On ajoute les pieces/billets au tableau montant et on affiche le total de ce tableau dans montantSimulation
         	for (int i=0;i<tabJTextField.length;i++){
             	int quantiteMontant = 0 ;
             	if (tabJTextField[i].getText()==""){}
             	else {
                 	try{
                     	quantiteMontant = Integer.parseInt(tabJTextField[i].getText());
                 	}
                 	catch(NumberFormatException nfe){}
                 	montant.ajouter(montant.getPieces(14-i).getValeur(), quantiteMontant);
                 	montant.getCaisse();
                 	tabJTextField[i].setText("");
             	}
         	}
         	float res = (float)montant.getCaisse()/100;
         	String totalMontant = Float.toString(res);
         	montantSimulation.setText(totalMontant);
         	break;
       	 
     	case "Payer" ://On envoit les informations prix et l'arrayList montant à la fonction rendre et on reçoit le résultat dans message
         	float prixTot=0;
         	try{
             	prixTot = Float.parseFloat(prix.getText());
             	prixTot*=100;
         	}
         	catch(NumberFormatException nfe){
             	message.setText("Le prix est invalide");
             	break;}
         	if (prixTot<0){
       		  message.setText("Le prix est invalide");
       		  break;}
         	else{
             	message.setText(pieces.rendre((int)prixTot,montant.getPieces()));
             	textAreaCaisse.setText(pieces.toString());
             	montant= new Tabpiece();
             	montantSimulation.setText("0");
             	break;
         	}
       	 
     	case "Braquer la boulangerie (Mettre à zéro)" ://On remet tous les champs text à null et on réinitialise les tableaux
         	pieces = new Tabpiece();
         	montant = new Tabpiece();
         	textAreaCaisse.setText(pieces.toString());
         	message.setText("");
         	messageAjout.setText("");
         	messageRetrait.setText("");
         	montantSimulation.setText("");
         	qteAjout.setText("");
         	valAjout.setText("");
         	qteSuppr.setText("");
         	valSuppr.setText("");
         	prix.setText("");
         	for (int i=0;i<tabJTextField.length;i++){
             	tabJTextField[i].setText("");
         	}
         	break;
       	 
     	case ("Annuler le montant") ://on réinitialise le tableau montant et on vide le champ text
         	montantSimulation.setText("");
          	montant = new Tabpiece();
          	break;
     	}
 	}
}
