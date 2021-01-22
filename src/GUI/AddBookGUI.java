package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bookrateback.*;

public class AddBookGUI extends JFrame {
    private JPanel addBookGUI;
    private JTextField isbnTX;
    private JTextField titleTX;
    private JTextField authorTX;
    private JTextField genreTX;
    private JRadioButton r0Button;
    private JRadioButton r1Button;
    private JRadioButton r2Button;
    private JRadioButton r3Button;
    private JRadioButton r4Button;
    private JRadioButton r5Button;
    private JRadioButton r6Button;
    private JRadioButton r7Button;
    private JRadioButton r8Button;
    private JRadioButton r9Button;
    private JRadioButton r10Button;
    private JTextField shortInfoTX;
    private JButton addBookButton;
    private JLabel isbnField;
    private JLabel titleField;
    private JLabel authorField;
    private JLabel genreField;
    private JPanel rateField;
    private JLabel shortInfoField;

    public AddBookGUI(User user) {
        setContentPane(addBookGUI);
        DataBase dataBase = new DataBase();
        isbnTX.setTransferHandler(null);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean error = false;
                if (isbnTX.getText().length() == 0) {
                    error = true;
                    isbnTX.setForeground(Color.red);
                }
                if (titleTX.getText().length() == 0) {
                    error = true;
                    titleTX.setForeground(Color.red);
                }
                if (authorTX.getText().length() == 0) {
                    error = true;
                    authorTX.setForeground(Color.red);
                }
                if (genreTX.getText().length() == 0) {
                    error = true;
                    genreTX.setForeground(Color.red);
                }
                if (shortInfoTX.getText().length() == 0) {
                    error = true;
                    shortInfoTX.setForeground(Color.red);
                }
                if (!error) {
                    dataBase.addBook(new Book(Long.parseLong(isbnTX.getText()), titleTX.getText(), authorTX.getText(), genreTX.getText(), shortInfoTX.getText()));
                    dispose();

                    if (r0Button.isSelected()) dataBase.addRate(titleTX.getText(), 0, user.getId());
                    if (r1Button.isSelected()) dataBase.addRate(titleTX.getText(), 1, user.getId());
                    if (r2Button.isSelected()) dataBase.addRate(titleTX.getText(), 2, user.getId());
                    if (r3Button.isSelected()) dataBase.addRate(titleTX.getText(), 3, user.getId());
                    if (r4Button.isSelected()) dataBase.addRate(titleTX.getText(), 4, user.getId());
                    if (r5Button.isSelected()) dataBase.addRate(titleTX.getText(), 5, user.getId());
                    if (r6Button.isSelected()) dataBase.addRate(titleTX.getText(), 6, user.getId());
                    if (r7Button.isSelected()) dataBase.addRate(titleTX.getText(), 7, user.getId());
                    if (r8Button.isSelected()) dataBase.addRate(titleTX.getText(), 8, user.getId());
                    if (r9Button.isSelected()) dataBase.addRate(titleTX.getText(), 9, user.getId());
                    if (r10Button.isSelected()) dataBase.addRate(titleTX.getText(), 10, user.getId());
                }
            }
        });
        isbnTX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
    }

    public AddBookGUI(String title, User user) {
        setContentPane(addBookGUI);
        DataBase dataBase = new DataBase();
        titleTX.setText(title);
        titleTX.setEnabled(false);
        isbnTX.setTransferHandler(null);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean error = false;
                if (isbnTX.getText().length() == 0) {
                    error = true;
                    isbnTX.setForeground(Color.red);
                }
                if (authorTX.getText().length() == 0) {
                    error = true;
                    authorTX.setForeground(Color.red);
                }
                if (genreTX.getText().length() == 0) {
                    error = true;
                    genreTX.setForeground(Color.red);
                }
                if (shortInfoTX.getText().length() == 0) {
                    error = true;
                    shortInfoTX.setForeground(Color.red);
                }
                if (!error) {
                    dataBase.addBook(new Book(Long.parseLong(isbnTX.getText()), titleTX.getText(), authorTX.getText(), genreTX.getText(), shortInfoTX.getText()));
                    dispose();

                    if (r0Button.isSelected()) dataBase.addRate(titleTX.getText(), 0, user.getId());
                    if (r1Button.isSelected()) dataBase.addRate(titleTX.getText(), 1, user.getId());
                    if (r2Button.isSelected()) dataBase.addRate(titleTX.getText(), 2, user.getId());
                    if (r3Button.isSelected()) dataBase.addRate(titleTX.getText(), 3, user.getId());
                    if (r4Button.isSelected()) dataBase.addRate(titleTX.getText(), 4, user.getId());
                    if (r5Button.isSelected()) dataBase.addRate(titleTX.getText(), 5, user.getId());
                    if (r6Button.isSelected()) dataBase.addRate(titleTX.getText(), 6, user.getId());
                    if (r7Button.isSelected()) dataBase.addRate(titleTX.getText(), 7, user.getId());
                    if (r8Button.isSelected()) dataBase.addRate(titleTX.getText(), 8, user.getId());
                    if (r9Button.isSelected()) dataBase.addRate(titleTX.getText(), 9, user.getId());
                    if (r10Button.isSelected()) dataBase.addRate(titleTX.getText(), 10, user.getId());
                }
            }
        });
        isbnTX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
    }

}
