package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import  Object.Obj_Key;

public class UI {

    GamePanel gp;
    Font arial_40,arial_60B;
    BufferedImage keyImage;

    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    public  boolean gameFinished=false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp=gp;
        arial_40= new Font("Arial",Font.PLAIN,40);//font name, font style, font size
        arial_60B= new Font("Arial",Font.BOLD,60);
        Obj_Key key = new Obj_Key();
        keyImage=key.image;
    }

    public void draw(Graphics2D g2){

        if (gameFinished==true){

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;



            text= "Your time is "+ dFormat.format(playTime)+ "!!";
            textLenght= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); // returns text lenght
            x= gp.screenWidth/2-textLenght/4;
            y = gp.screenHeight/2+(gp.tileSize*4);
            g2.drawString(text,x,y);


            g2.setFont(arial_60B);
            g2.setColor(Color.yellow);
            text= "CONGRATULATIONS";
            textLenght= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); // returns text lenght
            x= gp.screenWidth/2-textLenght/2;
            y = gp.screenHeight/2+(gp.tileSize*2);
            g2.drawString(text,x,y);

            gp.gameThread=null; //stop game

        }else {

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hasKey,74,65);


            //TIME
            playTime +=(double) 1/60;
            g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*11,65);

            //MESSAGE
            if (messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,24,gp.tileSize*5);

                messageCounter++;

                if (messageCounter>120){ //2seconds
                    messageCounter=0;
                    messageOn=false;
                }
            }
        }


    }

    public void showMessage(String text){

        message=text;
        messageOn=true;
    }
}
