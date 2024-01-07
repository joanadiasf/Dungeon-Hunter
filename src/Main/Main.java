package Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame(); //criar window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para ela fechar
        window.setResizable(false); //para ficar tamanho fixo
        window.setTitle("MyGame"); //titulo

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); //para ficar centrada
        window.setVisible(true); //para a window ser visivel

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
