package com.example.amazighquiz;

public class OefenenModel {
    String Foto;
    String Geluid;
    String WordAM;
    String WordNL;


    public OefenenModel(){}

    public OefenenModel(String Foto, String Geluid, String WordNL, String WordAM){
        this.Foto = Foto;
        this.Geluid = Geluid;
        this.WordAM = WordAM;
        this.WordNL = WordNL;
    }

    public String getFoto(){return Foto;}
    public String getGeluid(){return Geluid;}
    public String getWordAM(){return WordAM;}
    public String getWordNL(){return WordNL;}

    public void setFoto(String Foto) { this.Foto = Foto; }
    public void setGeluid(String Geluid) { this.Geluid = Geluid; }
    public void setWordAM(String WordAM) { this.WordAM = WordAM; }
    public void setWordNL(String WordNL) { this.WordNL = WordNL; }


}
