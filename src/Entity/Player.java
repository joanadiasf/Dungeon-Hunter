package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey=0;
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 7;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {


        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile007.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile008.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile000.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile002.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile016.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile018.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile011.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/tile013.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            //sprite doesnt chance unless u press a key

            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";

            }

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
           int objIndex = gp.cChecker.checkObject(this,true);
           pickUpObject(objIndex);
            //if collision is false, player can move
            if (collisionOn == false) {

                switch (direction) {

                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {

                if (spritwNum == 1) {
                    spritwNum = 2;
                } else if (spritwNum == 2) {
                    spritwNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){

        if (i != 999){

            String objectName = gp.obj[i].name;

            switch (objectName){

                case "Key":

                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You got a key!");
                break;

                case "Door":
                    if (hasKey > 0){
                        gp.playSoundEffect(3);
                        gp.obj[i]=null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                break;

                case "Boots":

                    gp.playSoundEffect(2);
                    speed +=1;
                    gp.obj[i]=null;

                    gp.ui.showMessage("Speed up!");
                    break;

                case "Chest":
                    gp.ui.gameFinished=true;
                    gp.stopMusic();
                    gp.playSoundEffect(4);
                    break;
            }
        }
    }
    public void draw(Graphics2D g2) {

        //g2.setColor(Color.white);
        //    g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {

            case "up":
                if (spritwNum == 1) {
                    image = up1;
                }
                if (spritwNum == 2) {
                    image = up2;
                }

                break;

            case "down":

                if (spritwNum == 1) {
                    image = down1;
                }
                if (spritwNum == 2) {
                    image = down2;
                }

                break;

            case "left":
                if (spritwNum == 1) {
                    image = left1;
                }
                if (spritwNum == 2) {
                    image = left2;
                }

                break;

            case "right":
                if (spritwNum == 1) {
                    image = right1;
                }
                if (spritwNum == 2) {
                    image = right2;
                }

                break;

        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
