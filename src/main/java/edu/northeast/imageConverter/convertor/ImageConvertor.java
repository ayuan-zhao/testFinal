package edu.northeast.imageConverter.convertor;

import edu.northeast.imageConverter.models.FixedImage;
import edu.northeast.imageConverter.models.MessageManager;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ImageConvertor implements IConvertor {

    private BufferedImage imageToWrite;
    private final MessageManager mm = MessageManager.getInstance();
    private final FileChooser fileChooser = new FileChooser();
    private File fileToBeSaved;
    private final String newExtension;
    private final String originalExtension;
    private final FixedImage uploadedImg;
    private String fileSavedPath;

    public ImageConvertor(FixedImage uploadedImg, String extens) {
        this.uploadedImg = uploadedImg;
        this.newExtension = extens;
        this.originalExtension = uploadedImg.getOriginalExt();
    }

    @Override
    public boolean convert() {
        if (this.originalExtension.equalsIgnoreCase(this.newExtension)) {
            mm.alertInfo("Please select different picture Type. Both type is " + this.originalExtension);
            return false;
        }

        BufferedImage bufferedImage = uploadedImg.getBufferedImage();
        imageToWrite = new BufferedImage(bufferedImage.getWidth(),
            bufferedImage.getHeight(),
            BufferedImage.TYPE_INT_RGB);
        imageToWrite.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
        File fileOld = fileChooser.showSaveDialog(new Stage());
        if (fileOld != null) {
            fileToBeSaved = new File(fileOld.getPath() + "." + newExtension);
            fileSavedPath = fileToBeSaved.getPath();
            return true;
        } else {
            mm.alertError("Please choose correct files!");
            return false;
        }
    }

    @Override
    public boolean saveImage() {
        try {
            ImageIO.write(imageToWrite, newExtension, fileToBeSaved);
            return true;
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            mm.alertError(ex.getMessage());
            return false;
        }
    }

    /**
     * get file saved path.
     */
    @Override
    public String getFileSavedPath() {
        return fileSavedPath;
    }
}
