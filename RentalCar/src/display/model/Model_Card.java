package display.model;

import javax.swing.Icon;


public class Model_Card {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model_Card() {
    }

    public Model_Card(Icon icon, String title, String value, String description) {
        this.icon = icon;
        this.title = title;
        this.value = value;
        this.description = description;
    }
    
    
    private Icon icon;
    private String title;
    private String value;
    private String description;
}
