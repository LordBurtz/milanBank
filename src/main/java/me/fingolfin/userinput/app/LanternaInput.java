package me.fingolfin.userinput.app;


import java.io.IOException;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class LanternaInput {
    public void testWindow() throws IOException, InterruptedException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        Label label = new Label("test");

        BasicWindow window = new BasicWindow();
        window.setComponent(label);

        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
                new EmptySpace(TextColor.ANSI.BLACK));
        gui.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
        gui.addWindowAndWait(window);
    }

    public void testWindow2() throws Exception {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        Label lbloutput = new Label("");

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("Name"));
        TextBox namebox = new TextBox().addTo(panel);

        panel.addComponent(new Label("Vorname"));
        panel.addComponent(new TextBox());

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        new Button("Submit!", new Runnable() {
            @Override
            public void run() {
                String name = namebox.getText();
                lbloutput.setText(name);
            }
        }).addTo(panel);

        panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        panel.addComponent(lbloutput);

        BasicWindow window = new BasicWindow();
        window.setComponent(panel);

        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE_BRIGHT));
        gui.addWindowAndWait(window);
    }

    public static void main(String[] args) throws Exception {
        //new Input().testWindow();
        new LanternaInput().testWindow2();
    }
}
