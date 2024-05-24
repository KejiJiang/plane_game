package WWW.Jixiaoyuan.cn;

public class Bullet extends Flyingthing {
    public Bullet(int x, int y) {
        image = FirseJava.bullet;
        width = image.getWidth();
        height = image.getHeight();
        this.x = x;
        this.y = y;
        speed = 15;
    }

    protected void move() {
        y-=speed;
    }

    @Override
    public void step() {

    }

    @Override
    public boolean outOfBounds() {
        return false;
    }

    public boolean outofBounds() {
        if (y < -this.height) {
            return true;
        } else {
            return false;
        }
    }
}
