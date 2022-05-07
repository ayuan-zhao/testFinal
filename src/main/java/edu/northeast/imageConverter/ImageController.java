package edu.northeast.imageConverter;

import static edu.northeast.imageConverter.models.FixedImage.MAX_DISPLAY_HEIGHT;
import static edu.northeast.imageConverter.models.FixedImage.MAX_DISPLAY_WIDTH;
import static edu.northeast.imageConverter.models.FixedImage.NO_IMAGE_AVAILABLE;

import edu.northeast.imageConverter.convertor.IConvertor;
import edu.northeast.imageConverter.convertor.ImageConvertor;
import edu.northeast.imageConverter.models.FixedImage;
import edu.northeast.imageConverter.models.MessageManager;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
import javax.imageio.ImageIO;

public class ImageController implements Initializable {

    private static final String NEW_LINE = "\n";
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

    private FixedImage uploadedImg;

    // MessageManager instance for alert.
    MessageManager mm = MessageManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // label displays wrapped text
        lblInfo.setWrapText(true);

        // add options to type conversion select box
        initChoiceBox();

        // default No Image picture
        uploadedImg = new FixedImage(
            getClass().getClassLoader().getResourceAsStream(NO_IMAGE_AVAILABLE), NO_IMAGE_AVAILABLE, null);
        displayImage(url.getPath());
    }

    private void initChoiceBox() {
        cboxType.getItems().add("JPG");
        cboxType.getItems().add("JPEG");
        cboxType.getItems().add("BMP");
        cboxType.getItems().add("PNG");
    }

    /**
     * Display Images
     */
    private void displayImage(String path) {
        imgView1.setImage(uploadedImg);

        // If the picture is too large (width is larger than MAX_DISPLAY_WIDTH, height is larger than
        // MAX_DISPLAY_HEIGHT). We need to fix the display.
        fixImageDisplay(uploadedImg);
        imgView2.setImage(uploadedImg);

        if (uploadedImg.hasPicture()) {
            displayMsg(String.format("Dimensions : %s x %s", uploadedImg.getWidth(), uploadedImg.getHeight()),
                false);
            displayMsg(String.format("Path : %s\n", path), true);
        }
    }

    /**
     * Display messages in label.
     */
    private void displayMsg(String msg, boolean append) {
        StringBuilder sb = new StringBuilder();
        if (append) {
            sb.append(lblInfo.getText());
        }
        sb.append(msg).append(NEW_LINE);
        lblInfo.setText(sb.toString());
    }


    /**
     * If the picture is too large (width is larger than MAX_DISPLAY_WIDTH, height is larger than
     * MAX_DISPLAY_HEIGHT). We need to fix the display.
     */
    private void fixImageDisplay(Image image) {
        if (image.getWidth() > MAX_DISPLAY_WIDTH) {
            imgView2.setFitWidth(MAX_DISPLAY_WIDTH);
            imgView2.setPreserveRatio(true);
        }
        if (image.getHeight() > MAX_DISPLAY_HEIGHT) {
            imgView2.setFitHeight(MAX_DISPLAY_HEIGHT);
            imgView2.setPreserveRatio(true);
        }
    }

    /**
     * upload one picture to UI. Triggered when upload button clicked.
     */
    public void onUpload(ActionEvent actionEvent) {
        System.out.println("btnUpload");

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                uploadedImg = new FixedImage(new DataInputStream(new FileInputStream(selectedFile)),
                    selectedFile.getPath(), bufferedImage);
                displayImage(selectedFile.getPath());
            } catch(IOException fileNotFoundException) {
                // Using catch block to handle file selection error.
                mm.alertError(fileNotFoundException.getMessage());
                fileNotFoundException.printStackTrace();
            }
        }
    }

    /**
     * Trigged when "convert and download" button click.
     */
    public void onConvert(ActionEvent actionEvent) {
        if (!uploadedImg.hasPicture()) {
            mm.alertError("Please upload picture first");
            return;
        }

        Object selectedItem = cboxType.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            // create a alert
            mm.alertError("Please select one type to convert");
            return;
        }

        String newExt = selectedItem.toString();

        // create a new converter and convert between types.
        IConvertor converter = new ImageConvertor(uploadedImg, newExt);
        if (converter.convert() && converter.saveImage()) {
            displayMsg("File is saved in " + converter.getFileSavedPath(), true);
        }
    }


}