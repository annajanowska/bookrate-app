package GUI;

import javax.swing.*;
import bookrateback.*;

public class NewsGUI extends JFrame{
    private JPanel panelNews;
    private JLabel headerNewsLabel;
    private JTextArea newsArea;

    public NewsGUI(News news) {
        setContentPane(panelNews);
        headerNewsLabel.setText(news.getTitle());
        newsArea.setText(news.getText());
        newsArea.setLineWrap(true);
    }
}
