package WWW.Jixiaoyuan.cn;

import java.util.Random;

public class Bee extends Flyingthing implements Award{
    private  int xSpeed;
    private  int awardtype;

    public Bee() {
        image=FirseJava.bee;
        width=image.getWidth();
        height=image.getHeight();
        Random rand=new Random();
        x=(int)(Math.random()*400-width);
        y=-height;
        speed=3;
        xSpeed=5;
        awardtype=rand.nextInt(2);
    }

    public void move(){
        x+=xSpeed;
        y+=speed;
        awardtype=(int)(Math.random()*3);
        if(x>400-this.width||x<=0){
            xSpeed=-xSpeed;
        }
        }

    @Override
    public void step() {

    }

    public int ScoreAward(){
        return (int)(Math.random()*2000);
    }
    @Override
    public boolean outOfBounds() {
        return false;
    }

    @Override
    public int getType() {
        return awardtype;
    }
}
