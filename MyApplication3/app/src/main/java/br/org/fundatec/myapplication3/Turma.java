package br.org.fundatec.myapplication3;

/**
 * Created by tecnico on 24/05/2017.
 */

class Turma {
    public String nome;
    public String sigla;
    public String professor;
    public Integer qtdAlunos;

    @Override
    public String toString(){
        StringBuilder turma = new StringBuilder();
        turma.append("Turma: ");
        turma.append(nome);
        turma.append(" / ");
        turma.append(sigla);
        turma.append("\nProfessor: ");
        turma.append(professor);
        turma.append("\nQtd. Alunos: ");
        turma.append(qtdAlunos);
        return turma.toString();
    }
}
