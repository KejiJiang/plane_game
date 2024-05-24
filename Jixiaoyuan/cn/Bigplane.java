package WWW.Jixiaoyuan.cn;

import WWW.Jixiaoyuan.cn.Award;
import WWW.Jixiaoyuan.cn.Enemy;
import WWW.Jixiaoyuan.cn.FirseJava;
import WWW.Jixiaoyuan.cn.Flyingthing;

public abstract class Bigplane extends Flyingthing implements Award, Enemy {
    private int awardType;
    private  int score;
    private int blood;
    public Bigplane() {
        image= FirseJava.bigplane;
        width=image.getWidth();
        height=image.getHeight();
        x=(int)(Math.random()*400-width);
        y=-height;
        speed=5;
        awardType=(int)(Math.random()*2);
        blood=3;
    }

    @Override
    protected void move() {
        y+=speed;
    }

    public void reduceBlood(){
        blood--;
    }
    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = 3;
    }

    @Override
    public int getType() {
        return awardType;
    }

    @Override
    public int getscore() {
        return (int) (Math.random()*1000);
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
}
