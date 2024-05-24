package WWW.Jixiaoyuan.cn;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Flyingthing {
    protected int x;
    protected int y;
    protected BufferedImage image;
    protected int speed;
    protected abstract void move();
    protected int width;
    protected int height;

    public boolean shootBy(Bullet bullet){
        int x=bullet.getX();
        int y=bullet.getY();
        boolean isShoot=this.x < x && x<this.x+this.width && this.y < y && y < this.y+this.height;
        return isShoot;
    }
    public boolean crashBy(Hero hero){
        int x=hero.getX();
        int y=hero.getY();
        boolean isCrash=this.x-hero.width< x && x<this.x+this.width && this.y-hero.height < y && y < this.y+this.height;
        return isCrash;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void step();

    public abstract boolean outOfBounds();
}
