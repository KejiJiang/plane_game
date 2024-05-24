package WWW.Jixiaoyuan.cn;

import java.awt.image.BufferedImage;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.List;

public abstract class Hero extends Flyingthing{
    private int life=3;
    private int count=0;
    private int double_fire=0;
    private BufferedImage[] images;

    public Hero() {
        life=3;
        x=200;
        y=300;
        image=FirseJava.hero0;
        width=image.getWidth();
        height=image.getHeight();
        images=new BufferedImage[]{FirseJava.hero0,FirseJava.hero1};
    }

    public List<Bullet> shoot(){
        List<Bullet> list =new ArrayList<Bullet>();
        if(double_fire>0){
            list.add(new Bullet(x+width/4,y));
            list.add(new Bullet(x+width*3/4,y));
            list.add(new Bullet(x+width/2,y));
            list.add(new Bullet(x,y));
            list.add(new Bullet(x+width,y));
            list.add(new Bullet(x-width/4,y));
            list.add(new Bullet(x+width*5/4,y));
            double_fire-=5;
        } else
        {
            list.add(new Bullet(x+width/2,y));
        }

        return list;
    }
    public void moveTo(int x,int y){
        this.x=x-this.width/2;
        this.y=y-this.height/2;
    }
    protected void move(){

        BufferedImage[] images={FirseJava.hero0,FirseJava.hero1};
        image=images[count++ %2];
    }

    public boolean outofBounds(){
        return false;
    }
    public void addLife(){
        life++;
    }
    public void doubleFire(){
        double_fire+=20;
    }

    public void reduceLife(){
        life-=(int) (Math.random()*3+1);
    }

    public void setLife(int life) {
        this.life = life;
    }
    public int getLife() {
        return life;
    }

    public boolean hit(Flyingthing obj){
        int x1=this.x-this.width/2;
        int x2=this.x+this.width/2;
        int y1=this.y-this.height/2;
        int y2=this.y+this.height/2;
        if(obj.x<x2&&obj.x>x1&&obj.y<y2&&obj.y>y1){
            return true;
        }else{
            return false;
        }
    }

    public void zeroDOubleFire(){
        double_fire=0;
    }
}
