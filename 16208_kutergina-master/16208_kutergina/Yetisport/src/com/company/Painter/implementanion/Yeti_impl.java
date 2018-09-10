package com.company.Painter.implementanion;

import com.company.Painter.Yeti;

import javax.swing.*;
import java.awt.*;

public class Yeti_impl extends JPanel implements Yeti {
   private Image[] images = {new ImageIcon("src/images/1.png").getImage(),
            new ImageIcon("src/images/2.png").getImage(),
            new ImageIcon("src/images/3.png").getImage(),
            new ImageIcon("src/images/4.png").getImage(),
            new ImageIcon("src/images/5.png").getImage(),
            new ImageIcon("src/images/6.png").getImage(),
            new ImageIcon("src/images/7.png").getImage(),
            new ImageIcon("src/images/8.png").getImage()
    };
    private Image current = images[0];
    private int numberImg = 0;

    public Image getCurrent() {
        return current;
    }

    public void nextImageOneClick(){
        if(numberImg < 3) {
            current = images[numberImg++];
        }
    }

   public void nextImageTwoClick(){
        if(numberImg < 7 ) {
            current = images[numberImg++];
        }
    }

}
