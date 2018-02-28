package menu_managers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommandInfo {
	StringProperty name = new SimpleStringProperty();
    StringProperty descrip = new SimpleStringProperty();
    
    public final StringProperty nameProperty() {
        return this.name;
    }

    public final String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String name) {
        this.nameProperty().set(name);
    }
    
    public final StringProperty descripProperty() {
        return this.descrip;
    }

    public final String getDescrip() {
        return this.descripProperty().get();
    }

    public final void setDescrip(final String descrip) {
        this.descripProperty().set(descrip);
    }
    
}
