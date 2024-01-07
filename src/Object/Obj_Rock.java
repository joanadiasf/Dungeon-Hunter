package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Rock extends SuperObject{

    public Obj_Rock(){

        name = "Key";

        try{

            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/rock1.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
