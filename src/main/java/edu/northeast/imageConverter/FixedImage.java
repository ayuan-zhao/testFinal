package edu.northeast.imageConverter;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

/**
 * inheritance from Image, customize my own Image class.
 *
 * Also we can easily get BufferedImage ;
 */
public class FixedImage extends Image {

    public static final int MAX_DISPLAY_WIDTH = 900;
    public static final int MAX_DISPLAY_HEIGHT = 600;
    public static final String NO_IMAGE_AVAILABLE = "NoImageAvailable.jpeg";


    // encapsulation for hasPicture
    private final boolean hasPicture;

    // encapsulation for picture extension.
    private String originalExt;

    // encapsulation for bufferedImage.
    private BufferedImage bufferedImage;

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public FixedImage(InputStream is, String url, BufferedImage bufferedImg) {
        super(is);
        if (StringUtils.isBlank(url) || url.contains("NoImageAvailable.jpeg")) {
            this.hasPicture = false;
        } else {
            int idx = url.lastIndexOf(".");
            this.originalExt = url.substring(idx + 1);
            this.setBufferedImage(bufferedImg);
            this.hasPicture = true;
        }
    }

    public boolean hasPicture() {
        return hasPicture;
    }

    public String getOriginalExt() {
        return originalExt;
    }

}
