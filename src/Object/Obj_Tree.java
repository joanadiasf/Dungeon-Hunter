package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Tree extends SuperObject{

    public Obj_Tree(){

        name = "Key";

        try{

            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/tree1.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
