import java.awt.*;

public class Square implements Shape {
    protected int length;
    protected Color color;
    protected double x;
    protected double y;

    public Square(Point center, Color color) {
        this.x = center.x;
        this.y = center.y;
        this.color = color;
        length = 8;
    }

    public Point getCenter() {
        return new Point((int)x, (int)y);
    }

    public void setCenter(Point p){
        x = p.x;
        y = p.y;
    }

    public int getTop(){
        return (int) y - length;
    }
    public int getBottom(){
        return (int) y + length ;
    }
    public int getLeft(){
        return (int) x - length;
    }
    public int getRight(){
        return (int) x +length;
    }

    @Override
    public Rectangle getRegion(){
        return  new Rectangle(getTop(),getLeft(), 2*length, 2 *length);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(getLeft(), getTop(), length*2, length*2 );
        g.setColor(Color.BLACK);
        g.drawRect(getLeft(), getTop(), length*2, length*2);
    }

    public boolean isInside(Point p){
        Point center = new Point((int)x,(int)y);
        return p.distance(center)<length;
    }

    public void setColor(Color c){
        this.color = c;
    }
}
