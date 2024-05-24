package WWW.Jixiaoyuan.cn;

import com.sun.tools.javac.Main;

public abstract class Airplane extends Flyingthing implements Enemy{
    private int blood;
    private int score=0;

    public Airplane() {
        image=FirseJava.airplane;
        width=image.getWidth();
        height=image.getHeight();
        x=(int)(Math.random()*400-width);
        y=-height;
        speed=5;
    }

    protected void move(){
        y+=speed;
    }

    @Override
    public int getscore() {
        return (int) (Math.random()*100);
    }

    @Override
    public void setScore(int score) {
        score=5;
    }

}


