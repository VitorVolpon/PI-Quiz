/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Batalha;

/**
 *
 * @author victor
 */
import java.util.List;

public class Pergunta {
    private int id;
    private String texto;

    public Pergunta(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Pergunta(String texto) {
        this.texto = texto;
    }

    public Pergunta(String texto, List<Alternativa> alternativas) {
        this.texto = texto;
        this.alternativas = alternativas;
    }



    private List<Alternativa> alternativas;

    public List <Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List <Alternativa> alternativas) {
        this.alternativas = alternativas;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
