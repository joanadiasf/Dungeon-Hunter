package Main;

import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

import Object.SuperObject;

public class GamePanel extends JPanel implements Runnable { //ela tem as funçoes de JPanel (game screen)

    //screen settings
    final int originalTileSize = 16; //16x16 tile (map tiles e characters)
    final int scale = 3; //para dar scale - um 16x16 vê se como 48x48
    public final int tileSize = originalTileSize * scale; //48x48 tiles
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; //576 px

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //ENTITY AND OBJETC
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //objects display at same time


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH); //recognize key input
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();

        playMusic(0);;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //0.1666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;


            if (delta >= 1) {

                update(); //UPDATE: update info - character position
                repaint();  //DRAW: draw the screen with update --- it is how you call paintComponent method
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //TILES
        tileM.draw(g2);

        //OBJECTS
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();


    }

    public void playMusic(int i){

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){

        sound.stop();
    }

    public void playSoundEffect(int i){

        sound.setFile(i);
        sound.play();
    }
}
