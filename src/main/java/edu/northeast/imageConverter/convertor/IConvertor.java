package edu.northeast.imageConverter.convertor;

public interface IConvertor {

    /**
     * Convert one bufferedImage into a file to be saved.
     */
    boolean convert();

    /**
     * Save file into local disk. That's a download method.
     */
    boolean saveImage();

    String getFileSavedPath();
}
