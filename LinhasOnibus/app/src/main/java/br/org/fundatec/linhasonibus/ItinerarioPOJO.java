package br.org.fundatec.linhasonibus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tecnico on 06/06/2017.
 */

public class ItinerarioPOJO {
    @SerializedName("idlinha")
    @Expose
    public String idlinha;

    @SerializedName("nome")
    @Expose
    public String nome;

    @SerializedName("codigo")
    @Expose
    public String codigo;

    //public ArrayList<ItinerarioPos> pos;

    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("idlinha: ");
        string.append(idlinha);
        string.append("\nnome: ");
        string.append(nome);
        string.append("\ncodigo");
        string.append(codigo);

        return string.toString();
    }
}
