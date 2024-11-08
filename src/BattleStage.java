import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

class BattleStage extends JPanel {
    private int yMoodeng = 370;
    private int yMonImage = 70;
    private boolean moveUp = true;
    private boolean move2Up = true;
    private int draw = 0;
    private int battleTimeLeft = 120;
    Timer battleTimer;
    private int userLife = 3;
    private int monsterLife;
    private Monster mon;
    private int Scene = -1;

    private boolean CantDefenseMai = false;
    private boolean shieldActive = false;
    private boolean doubleDamageActive = false;

    private boolean showWin = false;
    private Image win = new ImageIcon(getClass().getResource("Win.png")).getImage();
    private Image Tired = new ImageIcon(getClass().getResource("Tired.png")).getImage();
    private boolean showTired = false;

    private Image monImage;
    private Random random = new Random();
    private Image imgBg;

    private String monsterChoice;
    private String userChoice;

    private Image heart = new ImageIcon(getClass().getResource("heart.png")).getImage();
    private Image rockUserTell = new ImageIcon(getClass().getResource("rockUsertell.png")).getImage();
    private Image paperUserTell = new ImageIcon(getClass().getResource("paperUsertell.png")).getImage();
    private Image sisUserTell = new ImageIcon(getClass().getResource("sisUsertell.png")).getImage();
    private Image Moodeng = new ImageIcon(getClass().getResource("Moodeng.png")).getImage();
    private Image rockMonTell = new ImageIcon(getClass().getResource("rockMonTell.png")).getImage();
    private Image paperMonTell = new ImageIcon(getClass().getResource("paperMonTell.png")).getImage();
    private Image sisMonTell = new ImageIcon(getClass().getResource("sisMonTell.png")).getImage();

    private boolean showUserChoice = false;
    private boolean showMonsterChoice = false;

    private String monsterSound = "";
    private boolean showMonsterSound = false;

    private JButton rockButton;
    private JButton paperButton;
    private JButton sisButton;

    private Image shImage = new ImageIcon(getClass().getResource("Lo.png")).getImage().getScaledInstance(100, 60,
            Image.SCALE_SMOOTH);
    private ImageIcon shIcon = new ImageIcon(shImage);
    private JButton shieldButton;

    private Image dbImage = new ImageIcon(getClass().getResource("dbDamage.png")).getImage().getScaledInstance(100, 60,
            Image.SCALE_SMOOTH);
    private ImageIcon dbIcon = new ImageIcon(dbImage);
    private JButton doubleDamageButton;

    private Image runImage = new ImageIcon(getClass().getResource("run.png")).getImage().getScaledInstance(100, 60,
            Image.SCALE_SMOOTH);
    private ImageIcon runIcon = new ImageIcon(runImage);
    private JButton runButton;

    private Image melon = new ImageIcon(getClass().getResource("Watermalon.png")).getImage();

    private MainGame mainGame;
    private JFrame battleFrame;

    BattleStage(MainGame mainGame) {
        this.mainGame = mainGame;
        this.battleFrame = battleFrame;

        int randomScene = random.nextInt(2);
        int randomMonster = random.nextInt(3);

        if (randomScene == 0) {
            imgBg = new ImageIcon(getClass().getResource("Scene1.png")).getImage();
            Scene = 0;
        } else {
            imgBg = new ImageIcon(getClass().getResource("Scene2.png")).getImage();
            Scene = 1;
        }

        if (randomMonster == 0) {
            mon = new Monster1(3);
            monImage = ((Monster1) mon).getMonsIcon().getImage();
        } else if (randomMonster == 1) {
            mon = new Monster2(5);
            monImage = ((Monster2) mon).getMonsIcon().getImage();
        } else {
            mon = new Monster3(2);
            monImage = ((Monster3) mon).getMonsIcon().getImage();
            CantDefenseMai = ((Monster3) mon).CantDefense();
        }

        monsterLife = mon.life;

        setLayout(null);
        setSize(1000, 750);
        setLocation(0, 0);

        rockButton = new GameButton("BTrock.png", 600, 375, 150, 100);
        paperButton = new GameButton("BTpaper.png", 500, 500, 150, 100);
        sisButton = new GameButton("BTsis.png", 700, 500, 150, 100);

        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userChoice = "rock";
                monsterChoice = getMonsterChoice();
                showChoices();
            }
        });
        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userChoice = "paper";
                monsterChoice = getMonsterChoice();
                showChoices();
            }
        });
        sisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userChoice = "sis";
                monsterChoice = getMonsterChoice();
                showChoices();
            }
        });

        add(rockButton);
        add(paperButton);
        add(sisButton);

        shieldButton = new GameButton(shIcon, 875, 475, 100, 60);
        doubleDamageButton = new GameButton(dbIcon, 875, 550, 100, 60);
        runButton = new GameButton(runIcon, 875, 20, 100, 60);

        shieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainGame.getMelon() >= 1) {

                    if (!shieldActive && !CantDefenseMai) {
                        mainGame.changeWTmelon(-1);
                        shieldActive = true;

                    }
                }
                repaint();
            }
        });

        doubleDamageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainGame.getMelon() >= 1) {
                    if (!doubleDamageActive) {
                        mainGame.changeWTmelon(-1);
                        doubleDamageActive = true;
                    }

                }
                repaint();
            }
        });

        add(shieldButton);
        add(doubleDamageButton);

        runButton.setBounds(875, 20, 100, 60);
        runButton.setContentAreaFilled(false);
        runButton.setBorderPainted(false);
        runButton.setFocusPainted(false);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Scene == 0) {
                    if (mainGame.getMelon() >= 2) {
                        mainGame.changeWTmelon(-2);
                    } else if (mainGame.getMelon() == 1) {
                        mainGame.changeWTmelon(-1);
                    }
                }
                endBattle();

            }
        });
        add(runButton);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (showWin || showTired) {
                    showWin = false;
                    showTired = false;
                    endBattle();
                    repaint();
                }
            }
        });

        battleTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleTimeLeft--;
                if (battleTimeLeft <= 0) {
                    mainGame.setLoseGame(true);
                    endBattle();
                }
                repaint();
            }
        });
        battleTimer.start();

        Thread moveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (moveUp) {
                            yMoodeng -= 2;
                            if (yMoodeng <= 360) {
                                moveUp = false;
                            }
                        } else {
                            yMoodeng += 2;
                            if (yMoodeng >= 370) {
                                moveUp = true;
                            }
                        }

                        repaint();

                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread move2Thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (move2Up) {
                            yMonImage -= 2;
                            if (yMonImage <= 60) {
                                move2Up = false;
                            }
                        } else {
                            yMonImage += 2;
                            if (yMonImage >= 70) {
                                move2Up = true;
                            }
                        }
                        repaint();

                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        moveThread.start();

        move2Thread.start();
    }

    private String getMonsterChoice() {
        int choice = random.nextInt(3);
        if (choice == 0) {
            return "rock";
        } else if (choice == 1) {
            return "paper";
        } else {
            return "sis";
        }
    }

    private void showChoices() {
        showUserChoice = true;
        showMonsterChoice = true;

        monsterSound = mon.sound();
        showMonsterSound = true;

        Thread showThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    showUserChoice = false;
                    showMonsterChoice = false;
                    showMonsterSound = false;
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        showThread.start();
        updateBattleResult();
    }

    private void updateBattleResult() {
        if (userChoice.equals(monsterChoice)) {
            draw++;
        } else if ((userChoice.equals("rock") && monsterChoice.equals("sis")) ||
                (userChoice.equals("sis") && monsterChoice.equals("paper")) ||
                (userChoice.equals("paper") && monsterChoice.equals("rock"))) {
            int damage;
            if (doubleDamageActive) {
                damage = 2;
            } else {
                damage = 1;
            }
            monsterLife -= damage;
            doubleDamageActive = false;

        } else {
            if (shieldActive) {
                shieldActive = false;

            } else {
                userLife--;

            }
        }
        checkGameOver();
    }

    private void checkGameOver() {
        if (userLife <= 0) {
            showWin = false;
            showTired = true;
            mainGame.setLoseGame(true);
            battleTimer.stop();
        } else if (monsterLife <= 0) {
            showWin = true;
            showTired = false;
            if (Scene == 0) {
                mainGame.changeWTmelon(2);
            } else if (Scene == 1) {
                mainGame.changeWTmelon(1);
            }
            battleTimer.stop();
        }
    }

    private void endBattle() {
        battleTimer.stop();
        mainGame.setisBattleActive(false);
        mainGame.gameTimer.start();
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);

        g.drawImage(monImage, 600, yMonImage, 200, 200, this);
        g.drawImage(Moodeng, 170, yMoodeng, 200, 200, this);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Minecraftia", Font.BOLD, 24));
        g.drawString("Battle time: " + battleTimeLeft, 130, 50);

        for (int i = 0; i < userLife; i++) {
            g.drawImage(heart, 150 + (i * 85), 630, 70, 70, this);
        }

        for (int i = 0; i < monsterLife; i++) {
            g.drawImage(heart, 775 - (i * 85), 20, 70, 70, this);
        }

        if (showUserChoice) {
            if (userChoice.equals("rock")) {
                g.drawImage(rockUserTell, 350, 350, 150, 100, this);
            } else if (userChoice.equals("paper")) {
                g.drawImage(paperUserTell, 350, 350, 150, 100, this);
            } else if (userChoice.equals("sis")) {
                g.drawImage(sisUserTell, 350, 350, 150, 100, this);
            }
        }

        if (showMonsterChoice) {
            if (monsterChoice.equals("rock")) {
                g.drawImage(rockMonTell, 500, 220, 150, 100, this);
            } else if (monsterChoice.equals("paper")) {
                g.drawImage(paperMonTell, 500, 220, 150, 100, this);
            } else if (monsterChoice.equals("sis")) {
                g.drawImage(sisMonTell, 500, 220, 150, 100, this);
            }
        }

        if (showMonsterSound) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Minecraftia", Font.BOLD, 24));
            g.drawString(monsterSound, 550, 150);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Minecraftia", Font.BOLD, 24));
        g.drawImage(melon, 130, 50, 60, 60, this);
        g.drawString(": " + mainGame.getMelon(), 210, 105);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Minecraftia", Font.BOLD, 20));

        if (!showTired && !showWin) {
            g.drawString("(-1          ) ", 875, 660);
            g.drawImage(melon, 925, 620, 40, 40, this);

        }

        g.setColor(new Color(220, 45, 45));
        if (Scene == 0) {
            g.setColor(Color.BLACK);
            g.fillRect(125, 120, 290, 30);
            g.setColor(Color.RED);
            g.drawString("! if Win +2 but if Run -2 !", 135, 155);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Minecraftia", Font.BOLD, 20));

        if (shieldActive && !showTired && !showWin) {
            g.drawString("Shield activated", 649, 660);
        }

        if (doubleDamageActive && !showTired && !showWin) {
            g.drawString("Double Damage activated", 545, 700);
        }

        if (showWin || showTired) {

            rockButton.setVisible(false);
            paperButton.setVisible(false);
            sisButton.setVisible(false);
            shieldButton.setVisible(false);
            doubleDamageButton.setVisible(false);
            runButton.setVisible(false);
        } else {
            if (CantDefenseMai) {
                shieldButton.setVisible(false);
            } else {
                shieldButton.setVisible(true);
            }
            rockButton.setVisible(true);
            paperButton.setVisible(true);
            sisButton.setVisible(true);
            doubleDamageButton.setVisible(true);
            runButton.setVisible(true);
        }

        if (showWin) {
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(win, (getWidth() - 300) / 2, (getHeight() - 200) / 2, this);
        }

        if (showTired) {
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(Tired, (getWidth() - 300) / 2, (getHeight() - 200) / 2, this);
        }
    }
}
