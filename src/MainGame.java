import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MainGame extends JPanel {
    AudioPlayer backgroundMusic;
    private Image background = new ImageIcon(getClass().getResource("MainGameBG.png")).getImage();
    private ArrayList<Rectangle> wall = WallData.getRectangles(1000, 750);
    private ArrayList<Rectangle> pool = PoolData.getRectangles();
    private boolean isBattleActive = false;
    private boolean moreWT = false;
    private boolean winGame = false;
    private boolean loseGame = false;
    private Random random = new Random();
    private Image melon = new ImageIcon(getClass().getResource("Watermalon.png")).getImage();
    private Image complete = new ImageIcon(getClass().getResource("Complete.png")).getImage();
    private Image failed = new ImageIcon(getClass().getResource("GameOver.png")).getImage();
    private Image moreImg = new ImageIcon(getClass().getResource("More.png")).getImage();
    private int wtMelon = 0;
    Timer gameTimer, animationTimer;
    private int countdown = 240;
    private Player pl = new Player();
    private int animationCounter = 0;
    private int animationSpeed = 5;

    private Image stopImage = new ImageIcon(getClass().getResource("stop.png")).getImage().getScaledInstance(60, 60,
            Image.SCALE_SMOOTH);
    private ImageIcon stopIcon = new ImageIcon(stopImage);
    private JButton stopButton;
    private int checkTime = 0;

    public MainGame() {

        setFocusable(true);
        requestFocusInWindow();
        setLayout(null);

        backgroundMusic = new AudioPlayer("game-music.wav");
        backgroundMusic.loop();


        stopButton = new GameButton(stopIcon, 900, 20, 60, 60);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTime == 0) {
                    gameTimer.stop();
                    backgroundMusic.stop();
                    checkTime = 1;
                } else {
                    gameTimer.start();
                    backgroundMusic.loop();
                    checkTime = 0;
                }

                repaint();
            }
        });

        add(stopButton);
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;

                if (!isBattleActive && random.nextInt(300) > 250) {
                    openRandomBattle();

                }

                if (countdown <= 0) {
                    loseGame = true;
                }

                repaint();
            }
        });
        gameTimer.start();

        animationTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        animationTimer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (moreWT) {
                    moreWT = false;
                    gameTimer.start();
                    repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                Rectangle playerBounds = new Rectangle(pl.playerX, pl.playerY, 28, 36);

                if (k == KeyEvent.VK_A && checkTime == 0) {
                    playerBounds.setLocation(pl.playerX - 2, pl.playerY);
                    if (!isColliding(playerBounds) && pl.playerX > 0 && !isBattleActive
                            && !isCollidingPool(playerBounds)) {
                        pl.playerX -= 2;
                    }
                    pl.countR = 0;
                    updateAnimation();

                } else if (k == KeyEvent.VK_D && checkTime == 0) {
                    playerBounds.setLocation(pl.playerX + 2, pl.playerY);
                    if (!isColliding(playerBounds) && pl.playerX < getWidth() && !isBattleActive
                            && !isCollidingPool(playerBounds)) {
                        pl.playerX += 2;
                    }
                    pl.countR = 2;
                    updateAnimation();

                } else if (k == KeyEvent.VK_W && checkTime == 0) {
                    playerBounds.setLocation(pl.playerX, pl.playerY - 2);
                    if (!isColliding(playerBounds) && pl.playerY > 0 && !isBattleActive
                            && !isCollidingPool(playerBounds)) {
                        pl.playerY -= 2;
                    }
                    pl.countR = 3;
                    updateAnimation();

                } else if (k == KeyEvent.VK_S && checkTime == 0) {
                    playerBounds.setLocation(pl.playerX, pl.playerY + 2);
                    if (!isColliding(playerBounds) && pl.playerY < getHeight() && !isBattleActive
                            && !isCollidingPool(playerBounds)) {
                        pl.playerY += 2;
                    }
                    pl.countR = 1;
                    updateAnimation();
                }

                repaint();
            }

            public void keyReleased(KeyEvent e) {
                pl.countC = 0;
                repaint();
            }
        });
    }

    private void openRandomBattle() {
        gameTimer.stop();
        backgroundMusic.stop();
        isBattleActive = true;

        BattleStage battleStage = new BattleStage(this);
        JFrame battleFrame = new JFrame("Battle");
        battleFrame.setSize(1000, 750);
        battleFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        battleFrame.setLocationRelativeTo(null);
        battleFrame.add(battleStage);
        battleFrame.setVisible(true);
    }

    private void updateAnimation() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            pl.countC++;
            animationCounter = 0;
        }
        if (pl.countC > 3) {
            pl.countC = 0;
        }
    }

    private boolean isColliding(Rectangle playerBounds) {
        for (Rectangle w : wall) {
            if (playerBounds.intersects(w)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCollidingPool(Rectangle playerBounds) {
        for (Rectangle p : pool) {
            if (playerBounds.intersects(p)) {
                if (wtMelon >= 3) {
                    winGame = true;
                } else {
                    moreWT = true;
                    gameTimer.stop();
                }
                return true;
            }
        }
        return false;
    }

    public int getMelon() {
        return wtMelon;
    }

    public void changeWTmelon(int n) {
        this.wtMelon += n;
    }

    public boolean getisBattleActive() {
        return isBattleActive;
    }

    public void setisBattleActive(boolean b) {
        isBattleActive = b;
    }

    public boolean getLoseGame() {
        return loseGame;
    }

    public void setLoseGame(boolean b) {
        loseGame = b;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocusInWindow();

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(pl.player[pl.countR][pl.countC].getImage(), pl.playerX, pl.playerY, 28, 36, this);

        g.setColor(Color.WHITE);
        g.fillRect(5, 5, 225, 40);
        g.setFont(new Font("Minecraftia", Font.BOLD, 24));

        g.setColor(Color.BLACK);
        int minutes = countdown / 60;
        int seconds = countdown % 60;
        String secondsText = (seconds < 10 ? "0" : "") + seconds;
        String timeText = "Time Left: " + minutes + ":" + secondsText;
        g.drawString(timeText, 10, 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Minecraftia", Font.BOLD, 24));
        g.drawImage(melon, 10, 50, 60, 60, this);
        g.drawString(": " + getMelon(), 90, 105);

        if (checkTime == 1) {
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (!(isBattleActive || moreWT)) {
            stopButton.setVisible(true);
        } else {
            stopButton.setVisible(false);
        }
        if (isBattleActive) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getWidth());
            g.setFont(new Font("Minecraftia", Font.BOLD, 50));
            g.setColor(Color.WHITE);
            g.drawString("The battle is on", 250, 400);

        }

        if (winGame) {
            gameTimer.stop();
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(complete, (getWidth() - 300) / 2, (getHeight() - 400) / 2, this);
            stopButton.setVisible(false);
        }

        if (loseGame) {
            gameTimer.stop();
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(failed, (getWidth() - 300) / 2, (getHeight() - 400) / 2, this);
            stopButton.setVisible(false);
        }

        if (moreWT) {
            gameTimer.stop();
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(moreImg, (getWidth() - 300) / 2, (getHeight() - 200) / 2, this);

        }
    }
}


