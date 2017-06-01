package br.org.fundatec.linhasonibus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinhaOnibus {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("codigo")
    @Expose
    public String codigo;
    @SerializedName("nome")
    @Expose
    public String nome;

    public String toString() {
        StringBuilder linha = new StringBuilder();
        linha.append("ID: ");
        linha.append(id);
        linha.append("\n");
        linha.append("Código: ");
        linha.append(codigo);
        linha.append("\n");
        linha.append("Nome: ");
        linha.append(nome);

        return linha.toString();
    }
}