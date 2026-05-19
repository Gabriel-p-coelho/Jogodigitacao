/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Frase;
import br.edu.fei.view.TelaDigitacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gabip
 */
public class Controller {

    private TelaDigitacao tela;
    private ArrayList<Frase> frases;
    private int indiceAtual;
    private int score;
    private int scoreMaximo;

    public Controller(TelaDigitacao tela) {
        this.tela = tela;
        this.frases = new ArrayList<>();
        indiceAtual = 0;
        score = 0;
        carregarFrases();
        scoreMaximo = lerScoreMaximo();
        tela.getFraseLabel().setText(getFraseAtual());
    }

    public int getScore() {
        return score;
    }

    public int getScoreMaximo() {
        return scoreMaximo;
    }

    public int getTotalFrases() {
        return frases.size();
    }

    public String getFraseAtual() {
        return frases.get(indiceAtual).getFrase();
    }

    public void confirmar() {
        String digitado = tela.getTextoDigitadoArea().getText();
        boolean correto = frases.get(indiceAtual).comparar(digitado.trim());
        if (correto) {
            score++;
        }
        indiceAtual++;

        if (correto) {
            JOptionPane.showMessageDialog(tela, "Correto!");
        } else {
            JOptionPane.showMessageDialog(tela, "Errado!");
        }
        tela.getTextoDigitadoArea().setText("");

        if (indiceAtual < frases.size()) {
            tela.getFraseLabel().setText(getFraseAtual());
        } else {
            salvarScore();
            JOptionPane.showMessageDialog(tela,
                    "Fim de jogo!\nSeu score: " + score + "/" + frases.size()
                    + "\nScore máximo: " + scoreMaximo);
            tela.dispose();
        }
    }

    private void carregarFrases() {
        try {
            FileReader arquivo = new FileReader("frases.txt");
            BufferedReader br = new BufferedReader(arquivo);
            String linha;
            while ((linha = br.readLine()) != null) {
                frases.add(new Frase(linha));
            }
        } catch (IOException e) {
            System.out.println("Arquivo frases.txt não encontrado.");
        }
    }

    private int lerScoreMaximo() {
        try {
            FileReader arquivo = new FileReader("score.txt");
            BufferedReader br = new BufferedReader(arquivo);
            String linha = br.readLine();
            if (linha != null) {
                return Integer.parseInt(linha.trim());
            }
        } catch (IOException | NumberFormatException e) {

        }
        return 0;
    }

    public void salvarScore() {
        if (score > scoreMaximo) {
            scoreMaximo = score;
        }
        try (FileWriter arquivo = new FileWriter("score.txt")) {
            arquivo.write(String.valueOf(scoreMaximo));
        } catch (IOException e) {
            System.out.println("Erro ao salvar score.");
        }
    }

    public boolean verificar(String digitado) {
        boolean correto = frases.get(indiceAtual).comparar(digitado.trim());
        if (correto) {
            score++;
        }
        indiceAtual++;
        return correto;
    }

}
