import java.awt.MouseInfo;

import javax.swing.ImageIcon;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
class scene{

    
    public scene(){
        //add the classes that extend MonoBehaviour here( just call the constructor)
        gameloop.__inst__.Instantiate( new TextCounter(),0);
        gameloop.__inst__.Instantiate(new movableComponent(new ImageIcon("assets/75519.png"), MouseInfo.getPointerInfo().getLocation()), 0);
        
    }



}