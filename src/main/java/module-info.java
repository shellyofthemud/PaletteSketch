module org.csi3370.palettesketch {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.csi3370.palettesketch to javafx.fxml;
    exports org.csi3370.palettesketch;
}