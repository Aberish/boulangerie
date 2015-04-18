import java.util.ArrayList;
import java.io.*;

public class Tabpiece implements Serializable{
    
	private ArrayList <Piece> pieces;
    
    
	public Tabpiece(){
    	this.pieces= new ArrayList<Piece>();
    	this.pieces.add(new Piece(50000, 0));
    	this.pieces.add(new Piece(20000, 0));
    	this.pieces.add(new Piece(10000, 0));
    	this.pieces.add(new Piece(5000, 0));
    	this.pieces.add(new Piece(2000, 0));
    	this.pieces.add(new Piece(1000, 0));
    	this.pieces.add(new Piece(500, 0));
    	this.pieces.add(new Piece(200, 0));
    	this.pieces.add(new Piece(100, 0));
    	this.pieces.add(new Piece(50, 0));
    	this.pieces.add(new Piece(20, 0));
    	this.pieces.add(new Piece(10, 0));
    	this.pieces.add(new Piece(5, 0));
    	this.pieces.add(new Piece(2, 0));
    	this.pieces.add(new Piece(1, 0));
	}

	public Tabpiece(ArrayList<Piece> pieces){
    	this.pieces = new ArrayList<Piece>();
    	for(int i = 0; i<pieces.size(); i++)
        	this.pieces.add(new Piece(pieces.get(i).getValeur(),pieces.get(i).getNbr()));
    	}
    
    
	public ArrayList<Piece> getPieces(){
        	return this.pieces;
    	}
    
	public Piece getPieces(int i){
    	return this.pieces.get(i);
	}
    
	public boolean existe(int verif){
    	for (int i=0; i<this.pieces.size();i++){
        	if (verif ==this.pieces.get(i).getValeur()){
            	return true;
        	}
    	}
        	return false;
	}
    
	public String toString(){
        	String affichage="Montant et pieces/billets disponibles :";
        	Float tot=0f;
       	 
        	if(this.pieces.size()!=0){    
            	for(int i=0;i<this.pieces.size();i++){
                	affichage+=this.pieces.get(i).toString();
                	tot+=this.pieces.get(i).getNbr()*this.pieces.get(i).getValeur();
            	}
        	}
        	tot/=100;
        	affichage += "\n Total dans la caisse "+tot+" euro";
        	return affichage;
    	}	 
    
    
	public int getCaisse(){    
    	int argentCaisse=0;
    	for(int i = 0;i<this.pieces.size();i++){
        	argentCaisse+=this.pieces.get(i).getNbr()*this.pieces.get(i).getValeur();
        	}
    	return argentCaisse;
    	}

	public void supprimer(int valeur, int nbr){
   	 
    	for(int i = 0;i<this.pieces.size();i++){
        	if(pieces.get(i).getValeur()==valeur){
            	if(nbr<=pieces.get(i).getNbr()){
                	pieces.get(i).addPiece(-nbr);
                	return;
            	}
           	 
            	else
                	pieces.get(i).setNbr(0);
                	return;    
        	}    
    	}
	}
    
	public void ajouter(int valeur, int nbr){
   	 if(this.pieces.size()!=0){
   		 for(int i = 0;i<this.pieces.size();i++){
   			 if(pieces.get(i).getValeur()==valeur){
   				 this.pieces.get(i).addPiece(nbr);
   				 return;
   			 }
   		 }
   		 
   		 for(int j = 0;j<this.pieces.size();j++){
   		   if (valeur>this.pieces.get(j).getValeur()){
   			   Piece memo=new Piece(valeur, nbr);
   			   this.pieces.add(j,memo);
   			   return;
   		   }
   		 }
   	 }
     	Piece memo=new Piece(valeur, nbr);
     	this.pieces.add(memo);
	}


	public String rendre(int prix, ArrayList<Piece> p){
   	 //On test si le montant est suffisant
   	 int total=0;
   	 
   	 for(int i = 0;i<p.size();i++){
        	total+=p.get(i).getNbr()*p.get(i).getValeur();
        	}
   	 
   	 if (prix > total){return "Le montant est insuffisant";}
   	 //fin du test
   	 
    	int paiement=0;
    	Tabpiece copie=new Tabpiece(this.pieces);
    	//on fait une copie pour ajouter les pieces du montant et pour pouvoir revenir en arrière si jamais on ne peut pas rembourser
   	 
    	for(int i=0;i<p.size();i++){
        	paiement+=p.get(i).getValeur()*p.get(i).getNbr();
        	this.ajouter(p.get(i).getValeur(), p.get(i).getNbr());
        	}
   	 
    	int monnaie=paiement-prix;//ce qu'il reste à rembourser
    	String affichage="";
   	 
        	if(monnaie>=0){
       		 for(int i = 0;i<this.pieces.size();i++){
                    	int nbrAvant=this.pieces.get(i).getNbr();
                    	int reste =monnaie%this.pieces.get(i).getValeur() ;
                    	int test=(monnaie-reste)/this.pieces.get(i).getValeur();
                   	 
                    	if(test>=this.pieces.get(i).getNbr()){
                        	monnaie-=this.pieces.get(i).getNbr()*this.pieces.get(i).getValeur();
                        	this.supprimer(this.pieces.get(i).getValeur(), this.pieces.get(i).getNbr());
                        	}  
                   	 
                    	else{
                        	monnaie-=test*this.pieces.get(i).getValeur();
                        	this.pieces.get(i).setNbr(this.pieces.get(i).getNbr()-test);
                        	}
                    	if((nbrAvant-this.pieces.get(i).getNbr())!=0)
                        	affichage += "Nous vous rendons"+(nbrAvant-this.pieces.get(i).getNbr())+" piece/billet de "+this.pieces.get(i).getValeur()/100+" euro\n";
       		 }
       	 
            	if(monnaie>0){
                	this.pieces=copie.getPieces();
                	int j=this.pieces.size()-1;
                	boolean test=true;
                	affichage="Avez-vous ";
               	 
                	while(j>0 && test){
                    	if(this.pieces.get(j).getValeur()>monnaie &&this.pieces.get(j).getNbr()>0){
                        	monnaie=this.pieces.get(j).getValeur()-monnaie;
                        	test=false;
                        	j++;
                        	}
                    	j--;
                    	}
               	 
                	while(j<this.pieces.size() && !test){    
                        	if((monnaie%this.pieces.get(j).getValeur())==0){    
                            	if(!affichage.equals("Avez-vous ")){
                                	affichage+=" ou ";}
                                	affichage+=(monnaie/this.pieces.get(j).getValeur())+" pieces/billets de "+(float)this.pieces.get(j).getValeur()/100+"euro\n";
                       		 }
                        	j++;
                    	}
                	affichage+="?";
               	 
                	if(affichage.equals("Avez-vous ?")){
                    	affichage="Avez vous : \n";
                    	monnaie=prix;
                   	 
                    	for(int c=0;c<this.pieces.size();c++){
                        	if(this.pieces.get(c).getValeur()<=monnaie){
                            	int reste =monnaie%this.pieces.get(c).getValeur() ;;
                            	affichage+=(monnaie-reste)/this.pieces.get(c).getValeur()+" pieces/billets de "+(float)this.pieces.get(c).getValeur()/100+"euro\n";
                            	int nbr=(monnaie-reste)/this.pieces.get(c).getValeur();
                            	monnaie-=nbr * this.pieces.get(c).getValeur();
                        	}
                    	}
                        	affichage+="?";
                	}
            	}
        	}
        	if (affichage.equals("")){return "Montant exact";}
    	return affichage;
    }
    
	public Tabpiece charger() {

    	ObjectInputStream input;
    	Tabpiece pieces = new Tabpiece();

    	try {
        	//On ouvre un flux entrant
    	input = new ObjectInputStream(
                 	new BufferedInputStream(
                   	new FileInputStream(
                     	new File("sauvegarde.txt"))));
              	 
         	try {
             	//On insere dans pieces le contenu de la sauvegarde
           	pieces = (Tabpiece)input.readObject();
         	}
         	catch(FileNotFoundException e){
             	e.printStackTrace();
         	}
         	catch(ClassNotFoundException e){
             	e.printStackTrace();
         	}
         	input.close();
         	return (Tabpiece)pieces;
        	 
}
        	 
         	catch(IOException e){
             	e.printStackTrace();
         	}
    	return (Tabpiece)pieces;
   	}
    
	public void sauvegarder(Tabpiece tabpiece) {

    	ObjectOutputStream oos;
    	try {
      	oos = new ObjectOutputStream(
              	new BufferedOutputStream(
                	new FileOutputStream(
                  	new File("sauvegarde.txt"))));
           	 
      	//écrire l’objet tabpiece dans le fichier sauvegarde.txt
      	oos.writeObject(tabpiece);
 	 
      	//fermeture du flux
      	oos.close();
    	}
    	catch (IOException e) {
      	e.printStackTrace();
    	}   
	}
}
