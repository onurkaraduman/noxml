package com.noxml.editor.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

public class MenuBarController {

    @FXML
    private MenuBar menuBar;

    private XmlTabController xmlTabController;

    public void injectXmlTabController(XmlTabController xmlTabController) {
        this.xmlTabController = xmlTabController;
    }

    public void handleOpen() {

    }

    public void handleSaveAs() {
        xmlTabController.saveAs("");
    }

    public void handleSave() {
        xmlTabController.save();
    }

    public void handleDonation() {
    }

    public void handleExit() {
        MainController.editor.close();
    }

    public void handleReportProblem() {
    }

    public void handleAbout() {
    }

    public void handleOnlineManual() {
    }
}
