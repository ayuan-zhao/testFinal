module edu.northeast.imageConverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.apache.commons.lang3;
    requires java.desktop;

    opens edu.northeast.imageConverter to javafx.fxml;
    exports edu.northeast.imageConverter;
    exports edu.northeast.imageConverter.convertor;
    opens edu.northeast.imageConverter.convertor to javafx.fxml;
    exports edu.northeast.imageConverter.models;
    opens edu.northeast.imageConverter.models to javafx.fxml;
}