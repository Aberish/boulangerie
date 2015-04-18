import java.io.*;
public class Piece implements Serializable{
	int valeur;
	int nbr;

	Piece() {}
   
	Piece(int val, int nbr){   	 
	this.valeur=val;
	this.nbr=nbr;
	}
    
	public int getValeur(){
    	return this.valeur;
	}
    
	public void setValeur(int val){
    	this.valeur=val;
	}
    
	public int getNbr(){
    	return this.nbr;
	}
    
	public void setNbr(int nbr){
    	this.nbr=nbr;
	}
    
	public String toString(){
   	 float val = (float)this.valeur/100;
   	 if (this.nbr==0){return "";}
    	return ("\n" +this.nbr +" pieces/billets de "+val+" euro");
	}
    
	public void addPiece(int nbr){
	this.nbr+=nbr;
	}

}

