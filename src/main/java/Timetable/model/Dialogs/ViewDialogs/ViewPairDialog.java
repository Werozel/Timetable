package Timetable.model.Dialogs.ViewDialogs;

import Timetable.model.Dialogs.AddPairDialog;
import Timetable.model.Pair;
import com.jfoenix.animation.alert.CenterTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ViewPairDialog {
    JFXDialog dialog;
    StackPane container;
    JFXDialogLayout content;
    GridPane rootPane;

    JFXButton okButton, editPairButton;

    Pair pair;

    @Autowired
    AddPairDialog addPairDialog;

    public void show(StackPane container, Pair pair) {
        this.container = container;
        this.pair = pair;

        dialog = new JFXDialog();
        content = new JFXDialogLayout();

        updateContent();

        content.setActions(okButton);
        dialog.setContent(content);

        dialog.show(container);

    }

    private void updateContent() {
        okButton = new JFXButton("Ок");
        okButton.setPrefSize(50, 25);
        okButton.styleProperty().setValue("-fx-font-size: 13pt; -fx-text-fill: green; -fx-background-color: whitesmoke");
        okButton.setOnAction(e -> {
            this.dialog.close();
        });

        rootPane = new GridPane();

        var heading = new Text(pair.formatPair());
        heading.styleProperty().setValue("-fx-font-size: 14pt;");
        content.setHeading(heading);
        content.setBody(rootPane);

        rootPane.setPadding(new Insets(10, 0, 10, 0));
        rootPane.setHgap(10);

        Image editIcon = new Image("/icons/edit.png");
        editPairButton = new JFXButton("", new ImageView(editIcon));
        editPairButton.setOnAction(e -> {
            addPairDialog.showFromPair(pair);
            this.updateContent();
        });
        rootPane.add(editPairButton, 3, 0);

        rootPane.add(new Label("Предмет:"), 0, 0);
        rootPane.add(new Label(pair.getSubject()), 1, 0, 2, 1);

        rootPane.add(new Label("Преподаватель:"), 0, 1);
        rootPane.add(new Label(pair.getTeacher().formatFIO()), 1, 1, 2, 1);

        rootPane.add(new Label("Аудитория:"), 0, 2);
        rootPane.add(new Label(pair.getAuditorium().getName()), 1, 2, 2, 1);

        rootPane.add(new Label("Продолжительность:"), 0, 3);
        rootPane.add(new Label(pair.formatPairTime()), 1, 3, 2, 1);
    }

    public JFXDialog getDialog() {
        return dialog;
    }
}
