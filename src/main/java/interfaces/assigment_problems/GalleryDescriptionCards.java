package interfaces.assigment_problems;
public class GalleryDescriptionCards {
 static abstract class ArtPiece {private static int count=0;final String pieceId;ArtPiece(){pieceId="ART-"+(++count);}abstract String describe();String getPieceId(){return pieceId;}}
 static class Painting extends ArtPiece {String title;Painting(String title){this.title=title;}String describe(){return "Painting: "+title+", framed on canvas";}}
 static class Sculpture extends ArtPiece {String title;Sculpture(String title){this.title=title;}String describe(){return "Sculpture: "+title+", carved from stone";}}
 public static void main(String[] args){System.out.println(new Painting("Sunset Fields").describe());System.out.println(new Sculpture("The Thinker II").describe());}
}