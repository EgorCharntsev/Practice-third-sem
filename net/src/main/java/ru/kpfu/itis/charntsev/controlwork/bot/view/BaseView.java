package ru.kpfu.itis.charntsev.controlwork.bot.view;

import javafx.scene.Parent;
import ru.kpfu.itis.charntsev.controlwork.bot.BotApplication;

public abstract class BaseView {

    private static BotApplication botApplication;

    public static BotApplication getBotApplication() {
        if (botApplication != null) {
            return botApplication;
        }
        throw new RuntimeException("No app in base view");
    }

    public static void setBotApplication(BotApplication botApplication) {
        BaseView.botApplication = botApplication;
    }

    public abstract Parent getView();
}
