

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.fei.jogodigitacao;

import br.edu.fei.controller.Controller;
import br.edu.fei.view.TelaDigitacao;

/**
 *
 * @author andrezanon
 */
public class JogoDigitacao {

    public static void main(String[] args) {
        TelaDigitacao tela = new TelaDigitacao();
        Controller controller = new Controller(tela);
        tela.setController(controller);
        tela.setVisible(true);
    }
}
