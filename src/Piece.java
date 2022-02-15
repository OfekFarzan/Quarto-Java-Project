import java.awt.*;

public class Piece {

    private Shape shape;
    private Dimension size;
    private boolean isHollow;
    private Color color;
    private String[] properties;

    public Piece(Shape shape, Dimension size, boolean isHollow, Color color, String[] properties) {
        this.shape = shape;
        this.size = size;
        this.isHollow = isHollow;
        this.color = color;
        this.properties = properties;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public boolean isHollow() {
        return isHollow;
    }

    public void setHollow(boolean hollow) {
        isHollow = hollow;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}
