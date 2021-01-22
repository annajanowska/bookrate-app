package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bookrateback.*;

public class AdminPanelGUI extends JFrame {
    private JPanel adminPanel;
    private JTextPane userNickTX;
    private JButton adminPermissionBtn;
    private JTextArea enterNickTextArea;
    private JButton removeUserBtn;
    private DataBase dataBase;
    public AdminPanelGUI() {
        dataBase = new DataBase();
        setContentPane(adminPanel);
        removeUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataBase.getUser(userNickTX.getText()) != null) {
                    dataBase.deleteUser(userNickTX.getText());
                    enterNickTextArea.setText("Enter Nick");
                    userNickTX.setText("");
                }
                else enterNickTextArea.setText("There is no bookrateback.User with that nick!");
            }
        });
        adminPermissionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataBase.getUser(userNickTX.getText()) != null) {
                    dataBase.makeAdmin(userNickTX.getText());
                    enterNickTextArea.setText("Enter Nick");
                    userNickTX.setText("");
                }
                else enterNickTextArea.setText("There is no bookrateback.User with that nick!");
            }
        });
    }

}
