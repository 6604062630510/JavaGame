import javax.swing.ImageIcon;

class Player {
    ImageIcon[][] player = new ImageIcon[4][4];

    int playerX, playerY, countR = 2, countC = 0;

    Player() {
        playerX = 15;
        playerY = 200;
        player[0][0] = new ImageIcon(getClass().getResource("Left1.png"));
        player[0][1] = new ImageIcon(getClass().getResource("Left2.png"));
        player[0][2] = new ImageIcon(getClass().getResource("Left3.png"));
        player[0][3] = new ImageIcon(getClass().getResource("Left4.png"));
        player[1][0] = new ImageIcon(getClass().getResource("Down1.png"));
        player[1][1] = new ImageIcon(getClass().getResource("Down2.png"));
        player[1][2] = new ImageIcon(getClass().getResource("Down3.png"));
        player[1][3] = new ImageIcon(getClass().getResource("Down4.png"));
        player[2][0] = new ImageIcon(getClass().getResource("Right1.png"));
        player[2][1] = new ImageIcon(getClass().getResource("Right2.png"));
        player[2][2] = new ImageIcon(getClass().getResource("Right3.png"));
        player[2][3] = new ImageIcon(getClass().getResource("Right4.png"));
        player[3][0] = new ImageIcon(getClass().getResource("Up1.png"));
        player[3][1] = new ImageIcon(getClass().getResource("Up2.png"));
        player[3][2] = new ImageIcon(getClass().getResource("Up3.png"));
        player[3][3] = new ImageIcon(getClass().getResource("Up4.png"));
    }

}