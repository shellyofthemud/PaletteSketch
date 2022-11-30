module org.csi3370.palettesketch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;



    opens org.csi3370.palettesketch to javafx.fxml;
    exports org.csi3370.palettesketch;
    exports org.csi3370.palettesketch.tools;
    opens org.csi3370.palettesketch.tools to javafx.fxml;
}