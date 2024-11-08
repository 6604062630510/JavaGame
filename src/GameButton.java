import javax.swing.*;

public class GameButton extends JButton {
    GameButton(String iconPath, int x, int y, int width, int height) {
       super(new ImageIcon(GameButton.class.getResource(iconPath)));
       setBounds(x, y, width, height);
       setContentAreaFilled(false);
       setBorderPainted(false);
       setFocusPainted(false);
   }
   GameButton(ImageIcon Icon, int x, int y, int width, int height) {
       super(Icon);
       setBounds(x, y, width, height);
       setContentAreaFilled(false);
       setBorderPainted(false);
       setFocusPainted(false);
   }
}