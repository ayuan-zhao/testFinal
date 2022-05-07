package edu.northeast.imageConverter.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageManager {

    private static MessageManager _instance = null;
    private final Alert a = new Alert(AlertType.NONE);

    // We are using Singleton Design Pattern to create MessageManager Instance.
    // One instance can handle every alert.
    public static MessageManager getInstance() {
        if (_instance == null) {
            _instance = new MessageManager();
        }
        return _instance;
    }

    /**
     * alert Error Message.
     */
    public void alertError(String msg) {
        a.setAlertType(AlertType.ERROR);
        a.setContentText(msg);
        a.show();
    }

    /**
     * alert Information Message.
     */
    public void alertInfo(String msg) {
        a.setAlertType(AlertType.INFORMATION);
        a.setContentText(msg);
        a.show();
    }
}
