import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class App extends JFrame {
    AudioPlayer backgroundMusic;
    private URL imgBgURL = getClass().getResource("BgIntro.png");
    private Image imgBg = new ImageIcon(imgBgURL).getImage();

    private URL imgLogoURL = getClass().getResource("LogoGame.png");
    private Image imgLogo = new ImageIcon(imgLogoURL).getImage();

    private URL imgMdLogoURL = getClass().getResource("MoodengIntro.png");
    private Image imgMdLogo = new ImageIcon(imgMdLogoURL).getImage();

    App() {
        Home h = new Home(imgBg, imgLogo, imgMdLogo);
        add(h);
        backgroundMusic = new AudioPlayer("game-music.wav");
        backgroundMusic.loop();
    }

    public static void main(String[] args) {
        JFrame f = new App();
        f.setTitle("Moodeng's Mission");
        f.setSize(1000, 750);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    class Home extends JPanel {
        private Image imgBg;
        private Image imgLogo;
        private Image imgMdLogo;
        private int xMDLogo = getWidth() / 2 - 270 / 2;
        private int yMDLogo = -20;
        private int check = 0;
        private Timer t = new Timer(50, new Listener());
        private JButton startButton;

        class Listener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }

        Home(Image imgBg, Image imgLogo, Image imgMdLogo) {
            this.imgBg = imgBg;
            this.imgLogo = imgLogo;
            this.imgMdLogo = imgMdLogo;

            startButton = new GameButton("StartButton.png", 1000 / 2 - 310 / 2, 520, 310, 72);

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(Home.this);
                    parentFrame.setContentPane(new MainGame());
                    parentFrame.revalidate();
                    parentFrame.repaint();
                    backgroundMusic.stop();
                }
            });

            setLayout(null);
            add(startButton);
            t.start();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (check == 0) {
                yMDLogo = -15;
                xMDLogo = (getWidth() / 2 - 270 / 2) + 3;
                check = 1;
            } else {
                yMDLogo = -20;
                xMDLogo = (getWidth() / 2 - 270 / 2) - 3;
                check = 0;
            }

            g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(imgLogo, getWidth() / 2 - 500 / 2, getHeight() / 2 - 300 / 2, 500, 300, this);
            g.drawImage(imgMdLogo, xMDLogo, yMDLogo, 270, 270, this);
        }
    }
}

