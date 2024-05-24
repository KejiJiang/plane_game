package WWW.Jixiaoyuan.cn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class FirseJava extends JPanel {
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage airplane;
    public static BufferedImage bullet;
    public static BufferedImage bee;
    public static BufferedImage background;
    public static BufferedImage bigplane;
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    public final int START = 0;
    public final int RUNNING = 1;
    public final int PAUSE = 2;
    public final int GAMEOVER = 3;

    public int state = START;

    static {
        try {
            start = ImageIO.read(FirseJava.class.getResource("pic/start.png"));
            hero0 = ImageIO.read(FirseJava.class.getResource("pic/hero0.png"));
            hero1 = ImageIO.read(FirseJava.class.getResource("pic/hero1.png"));
            airplane = ImageIO.read(FirseJava.class.getResource("pic/airplane.png"));
            bullet = ImageIO.read(FirseJava.class.getResource("pic/bullet.png"));
            bee = ImageIO.read(FirseJava.class.getResource("pic/bee.png"));
            background = ImageIO.read(FirseJava.class.getResource("pic/background.png"));
            bigplane = ImageIO.read(FirseJava.class.getResource("pic/bigplane.png"));
            start = ImageIO.read(FirseJava.class.getResource("pic/start.png"));
            pause = ImageIO.read(FirseJava.class.getResource("pic/pause.png"));
            gameover = ImageIO.read(FirseJava.class.getResource("pic/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Airplane air = new Airplane() {
        @Override
        public void step() {
            Flyingthing l = flyingthings[flyingthings.length + 1];
            flyingthings[index] = l;
            flyingthings = Arrays.copyOf(flyingthings, flyingthings.length + 1);
        }

        @Override
        public boolean outOfBounds() {
            return false;
        }

        @Override
        public int getScore() {
            return 0;
        }
    };
    Bee be = new Bee();
    Hero hero = new Hero() {
        @Override
        public void step() {

        }

        @Override
        public boolean outOfBounds() {
            return false;
        }
    };
    Bigplane big = new Bigplane() {
        @Override
        public void step() {
            Flyingthing l = flyingthings[flyingthings.length + 1];
            flyingthings[index] = l;
            flyingthings = Arrays.copyOf(flyingthings, flyingthings.length + 1);
        }

        @Override
        public boolean outOfBounds() {
            return false;
        }

        @Override
        public int getScore() {
            return 0;
        }
    };
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Flyingthing> flys = new ArrayList<Flyingthing>();
    Flyingthing[] flyingthings = {};
    Bullet[] bulletss = {};
    List<Airplane> airs = new ArrayList<Airplane>();
    List<Bigplane> bigs = new ArrayList<Bigplane>();

    Timer timer = new Timer();

    public void action() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (state == START) {
                    getGraphics().drawImage(start, 0, 0, null);
                }
                if (state == RUNNING) {
                    creatFlyingthing();
                    stepAction();
                    shootBullet();
                    bangAction();
                    gameOverAction();
                    outofBounds();
                    checkGameOverAction();
                    air.move();
                    be.move();
                    big.move();
                    repaint();
                }
                if (state == PAUSE) {
                    getGraphics().drawImage(pause, 0, 0, null);
                }
                if (state == GAMEOVER) {
                    getGraphics().drawImage(gameover, 0, 0, null);
                }
            }
        }, 10, 18);
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == START) {
                    state = RUNNING;
                }
                if (state == GAMEOVER) {
                    score = 0;
                    hero = new Hero() {
                        @Override
                        public void step() {

                        }

                        @Override
                        public boolean outOfBounds() {
                            return false;
                        }
                    };
                    state = START;
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (state == RUNNING) {
                    hero.setX(e.getX() - hero.getWidth() / 2);
                    hero.setY(e.getY() - hero.getHeight() / 2);
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (state == RUNNING) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE) {
                    state = RUNNING;
                }
            }
        };
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == 'q') {
                    System.exit(0);
                }
            }
        };
        this.requestFocus();
        this.addKeyListener(keyAdapter);
    }

    public void checkGameOverAction() {
        if (hero.getLife() <= 0) {
            state = GAMEOVER;
        }
    }

    public void gameover() {
        timer.cancel();
    }

    private int index = 0;

    public void creatFlyingthing() {
        index+=(int) (Math.random()*10);
        if (index % ((int)(Math.random()*10+1))== 0) {
            int random = (int) (Math.random() * 30);
            switch (random) {
                case 0:
                    flys.add(new Bee());
                    break;
                case 1:
                case 2:
                case 3:
                    flys.add(new Bigplane() {
                        @Override
                        public void step() {

                        }

                        @Override
                        public boolean outOfBounds() {
                            return false;
                        }

                        @Override
                        public int getScore() {
                            return 0;
                        }
                    });
                    bigs.add(new Bigplane() {
                        @Override
                        public int getScore() {
                            return 0;
                        }

                        @Override
                        public void step() {

                        }

                        @Override
                        public boolean outOfBounds() {
                            return false;
                        }
                    });
                    break;
                default:
                    flys.add(new Airplane() {
                        @Override
                        public void step() {

                        }

                        @Override
                        public boolean outOfBounds() {
                            return false;
                        }

                        @Override
                        public int getScore() {
                            return 0;
                        }
                    });
                    airs.add(new Airplane() {
                        @Override
                        public int getScore() {
                            return 0;
                        }

                        @Override
                        public void step() {

                        }

                        @Override
                        public boolean outOfBounds() {
                            return false;
                        }
                    });
            }
        }
    }

    public void stepAction() {
        for (Flyingthing fly : flys) {
            fly.move();
        }
        for (Bullet b : bullets) {
            b.move();
        }
        hero.move();
    }

    public void outofBounds() {
        Iterator<Flyingthing> flysIterator = flys.iterator();
        while (flysIterator.hasNext()) {
            Flyingthing fly = flysIterator.next();
            if (fly.getY() > 600 || fly.getX() < 0) {
                flysIterator.remove();
            }
        }
        Iterator<Bullet> bulletsIterator = bullets.iterator();
        while (bulletsIterator.hasNext()) {
            Bullet bullet = bulletsIterator.next();
            if (bullet.getY() < 0) {
                bulletsIterator.remove();
            }
        }
    }


    int indexbullet = 0;

    public void shootBullet() {
        indexbullet++;
        if (indexbullet % (int)(Math.random()*20+1)== 0) {
            bullets.addAll(hero.shoot());
        }
    }

    int score = 0;

    private void bangAction() {
        for (int i = 0; i < bullets.size(); i++) {
            bang(bullets.get(i));
        }
    }

    private void bang(Bullet bullet) {
        for (int i = 0; i < flys.size(); i++) {
            Flyingthing fly = flys.get(i);
            boolean isShoot = fly.shootBy(bullet);
            if (isShoot) {
                bullets.remove(bullet);
                flys.remove(i);
                if (fly instanceof Enemy) {
                    score += ((Enemy) fly).getscore();
                }
                if (fly instanceof Award) {
                    int awardtype = ((Award) fly).getType();
                    switch (awardtype) {
                        case Award.DOUBLE_FIRE:
                            hero.doubleFire();
                            break;
                        case Award.LIFE:
                            hero.addLife();
                            break;
                        case Award.SCOREUP:
                            score+= be.ScoreAward();
                    }
                }
            }
            boolean isCrash = fly.crashBy(hero);
            if (isCrash) {
                hero.reduceLife();
                flys.remove(i);
                score += 10;
                repaint();
                break;
                }
            }
        }

    public void gameOverAction () {
        if (state == GAMEOVER) {
            score = 0;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background,0,0,null);

        g.drawImage(hero.getImage(),hero.getX(),hero.getY(),null);

        for(Flyingthing fly : flys){
            g.drawImage(fly.getImage(),fly.getX(),fly.getY(),null);
        }

        for(Bullet bullet : bullets){
            g.drawImage(bullet.getImage(),bullet.getX(),bullet.getY(),null);
        }
        g.setColor(new Color(0xFF0000));
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        g.drawString("生命值："+hero.getLife(),10,60);
        g.drawString("分数："+ score,10,30);
        g.drawImage(be.getImage(),be.getX(),be.getY(),null);

        g.drawImage(air.getImage(),air.getX(),air.getY(),null);

        g.drawImage(big.getImage(),big.getX(),big.getY(),null);
    }

    public void showMe(){
        JFrame window=new JFrame("飞机大战");
        window.setSize(400,600);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(this);
        window.setVisible(true);
    }
    public static void main(String[] args) throws IOException{
        FirseJava firseJava=new FirseJava();
        firseJava.showMe();
        firseJava.action();
    }
}
