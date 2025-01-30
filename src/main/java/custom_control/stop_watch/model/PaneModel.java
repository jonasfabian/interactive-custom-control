package custom_control.stop_watch.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaneModel {
    private final StringProperty demoTitle = new SimpleStringProperty("Stopwatch");

    private final LongProperty timeValue = new SimpleLongProperty(0);

    public String getDemoTitle() {
        return demoTitle.get();
    }

    public StringProperty demoTitleProperty() {
        return demoTitle;
    }

    public void setDemoTitle(String demoTitle) {
        this.demoTitle.set(demoTitle);
    }

    public long getTimeValue() {
        return timeValue.get();
    }

    public LongProperty timeValueProperty() {
        return timeValue;
    }

    public void setTimeValue(long timeValue) {
        this.timeValue.set(timeValue);
    }
}
