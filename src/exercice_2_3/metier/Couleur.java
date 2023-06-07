package metier;

public enum Couleur
{
   ROUGE ( "carmin",255,0,0 ),
   VERT  ( "amande",0,255,0),
   BLEU  ( "azur",0,0,255),
   JAUNE ( "citron",255,255,0),
   VIOLET( "pervenche",255,0,255),
   CYAN  ( "turquoise",0,255,255),
   BLANC ( "immaculé",255,255,255),
   NOIR  ( "ténébreux",0,0,0);

   private String libelle;
   private int    r,v,b;

   Couleur ( String libelle, int r, int v, int b){
      this.libelle = libelle;
      this.r = r;
      this.v = v;
      this.b = b;
   }

   public String getLibelle(){ return libelle; }
   public String getValeur (){
      return   String.format("%-9s",this.libelle) + 
               " (" + String.format("%7s",this) + ") " + 
               String.format("%,15d",this.r * 65536 + this.v * 256 + this.b) + 
               " [" + this.r + "," + this.v + "," + this.b + "]";
   }
}