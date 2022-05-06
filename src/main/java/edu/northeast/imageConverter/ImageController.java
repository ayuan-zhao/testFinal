package edu.northeast.imageConverter;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageController implements Initializable {

    @FXML
    private ImageView imgView1;

    @FXML
    private ImageView imgView2;

    @FXML
    private Button btnUpload;

    @FXML
    private Button btnConvert;

    @FXML
    private Label lblInfo;

    @FXML
    private ChoiceBox cboxType;

    final FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // label displays wrapped text
        lblInfo.setWrapText(true);

        // add options to type conversion select box
        initChoiceBox();

        // default No Image picture
        Image image = new Image(Objects.requireNonNull(
            getClass().getClassLoader().getResourceAsStream("NoImageAvailable.jpeg")));
        setImage(image, url.getPath());
    }

    private void initChoiceBox() {
        cboxType.getItems().add("SVG");
        cboxType.getItems().add("ICO");
        cboxType.getItems().add("JPG");
        cboxType.getItems().add("JPEG");
        cboxType.getItems().add("GIP");
        cboxType.getItems().add("BMP");
        cboxType.getItems().add("PNG");
    }

    private void setImage(Image image, String path) {
        imgView1.setImage(image);
        fixImageDisplay(imgView2, image);
        imgView2.setImage(image);

        StringBuilder sb = new StringBuilder();
        String dimensions = String.format("Dimensions : %s x %s\n", image.getWidth(), image.getHeight());
        sb.append(dimensions);
        String pathInfo = String.format("Path : %s\n", path);
        sb.append(pathInfo);
        lblInfo.setText(sb.toString());
    }


    private void fixImageDisplay(ImageView imgView2, Image image) {
        if (image.getWidth() > 900) {
            imgView2.setFitWidth(900);
            imgView2.setPreserveRatio(true);
        }
        if (image.getHeight() > 600) {
            imgView2.setFitHeight(500);
            imgView2.setPreserveRatio(true);
        }
    }

    public void onUpload(ActionEvent actionEvent) {
        System.out.println("btnUpload");

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {

            final InputStream targetStream;
            try {
                targetStream = new DataInputStream(new FileInputStream(selectedFile));
                Image image = new Image(targetStream);
                setImage(image, selectedFile.getPath());
            } catch(FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    public void onConvert(ActionEvent actionEvent) {

    }
}