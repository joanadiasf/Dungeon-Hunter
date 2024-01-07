package Main;

import Object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 11 * gp.tileSize;
        gp.obj[0].worldY = 33 * gp.tileSize;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 41 * gp.tileSize;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 36 * gp.tileSize;
        gp.obj[1].worldY = 39 * gp.tileSize;

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX = 9 * gp.tileSize;
        gp.obj[3].worldY = 29 * gp.tileSize;

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX = 14 * gp.tileSize;
        gp.obj[3].worldY = 28 * gp.tileSize;

        gp.obj[4] = new Obj_Chest();
        gp.obj[4].worldX = 11 * gp.tileSize;
        gp.obj[4].worldY = 10 * gp.tileSize;

    }
}
