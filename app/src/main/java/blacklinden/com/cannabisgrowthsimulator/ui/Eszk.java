package blacklinden.com.cannabisgrowthsimulator.ui;

public class Eszk {
    private String name, type, prop;

    public Eszk() {
    }

    public Eszk(String name, String type, String prop) {
        this.name = name;
        this.type = type;
        this.prop = prop;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
