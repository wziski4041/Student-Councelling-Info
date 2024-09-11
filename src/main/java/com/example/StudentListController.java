package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class StudentListController {

    @FXML
    private void backToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML private TreeView<String> student1;

    @FXML
    private void initialize() {
        student1.setRoot(createItemHierarchy("base new root", new TreeItem<String>("root1"), new TreeItem<String>("root2")));
        student1.setCellFactory(param -> {
            CustomTreeCell<String> treeCell = new CustomTreeCell<>();
            
            return treeCell;
        });
    }

    private TreeItem<String> createItemHierarchy(String baseroot, TreeItem<String>... root1) {
        TreeItem<String> root = new TreeItem<>(baseroot);
        root.getChildren().addAll(root1);
        return root ;
    }


}