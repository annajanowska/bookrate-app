package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bookrateback.*;

public class AddNewsGUI extends JFrame {
    private JPanel panelNews;
    private JTextPane addNewsTX;
    private JButton addNewsButton;
    private JTextField addNewsHeaderTX;

    public AddNewsGUI(User user) {
        setContentPane(panelNews);
        DataBase dataBase = new DataBase();
        addNewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataBase.addMessage(new News(addNewsTX.getText(), user, addNewsHeaderTX.getText()));
                dispose();
            }
        });
    }

}
