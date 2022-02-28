public class Piece {
    private int id;
    private String properties;

    public Piece(int id) {
        this.id = id;
        this.properties = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
