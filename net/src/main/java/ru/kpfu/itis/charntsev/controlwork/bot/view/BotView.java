package ru.kpfu.itis.charntsev.controlwork.bot.view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class BotView extends BaseView {

    private TextArea input;
    private TextArea conversation;
    private AnchorPane pane;

    private final EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String message = input.getText() + "\n";

                conversation.appendText("you: " + message);

                String command = input.getText();

                String result = getBotApplication().getBotClient().executeCommand(command);

                conversation.appendText("bot: " + result + "\n");

                input.clear();
                event.consume();
            }
        }
    };

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    public void appendCommand(String command) {
        if (command != null) {
            conversation.appendText(command);
        }
    }

    private void createView() {
        pane = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);


        AnchorPane.setTopAnchor(conversation, 10.0);
        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setBottomAnchor(input, 10.0);
        AnchorPane.setLeftAnchor(input, 10.0);
        AnchorPane.setRightAnchor(input, 10.0);

        input.addEventHandler(KEY_PRESSED,eventHandler);
        pane.getChildren().addAll(input,conversation);
    }
}
