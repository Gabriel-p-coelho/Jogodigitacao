/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Frase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gabip
 */
public class Controller {

    private ArrayList<Frase> frases;
    private int indiceAtual;
    private int score;
    private int scoreMaximo;

    public Controller() {
        frases = new ArrayList<>();
        indiceAtual = 0;
        score = 0;
        carregarFrases();
        scoreMaximo = lerScoreMaximo();
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

    private void carregarFrases() {
        try{
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
        try{
            FileReader arquivo = new FileReader("frases.txt");
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
        try (FileWriter fw = new FileWriter("score.txt")) {
            fw.write(String.valueOf(scoreMaximo));
        } catch (IOException e) {
            System.out.println("Erro ao salvar score.");
        }
    }

    public String getFraseAtual() {
        return frases.get(indiceAtual).getFrase();
    }

    public boolean verificar(String digitado) {
        boolean correto = frases.get(indiceAtual).comparar(digitado.trim());
        if (correto) {
            score++;
        }
        indiceAtual++;
        return correto;
    }

    public boolean temProxima() {
        return indiceAtual < frases.size();
    }

}
