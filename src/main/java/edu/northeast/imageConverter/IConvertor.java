package edu.northeast.imageConverter;

public interface IConvertor {

    /**
     * Convert one bufferedImage into a file to be saved.
     */
    void convert();

    /**
     * Save file into local disk. That's a download method.
     */
    boolean saveImage();
}
