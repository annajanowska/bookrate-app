package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bookrateback.*;

public class BookRateGUI extends JFrame {
    private JPanel panelBookRate;
    private JTextPane addReviewTX;
    private JButton addReviewButton;
    private JRadioButton r0Button;
    private JRadioButton r9Button;
    private JRadioButton r2Button;
    private JRadioButton r1Button;
    private JRadioButton r8Button;
    private JRadioButton r3Button;
    private JRadioButton r7Button;
    private JRadioButton r6Button;
    private JRadioButton r5Button;
    private JRadioButton r4Button;
    private JRadioButton r10Button;
    private JButton addRateButton;
    private JSlider avgGradeSlider;
    private JLabel avgGradeLabel;
    private JTextArea shortInfoTX;
    private JTextArea titleTX;
    private JTextArea authorTX;
    private Book book;
    private User user;

    public BookRateGUI(Book book, User user) {
        setContentPane(panelBookRate);
        DataBase dataBase = new DataBase();
        this.book = book;
        this.user = user;

        avgGradeLabel.setText(String.format("%.2f", dataBase.getAvg(book.getTitle())));
        avgGradeSlider.setValue((int) (dataBase.getAvg(book.getTitle()) * 100));
        shortInfoTX.setText(book.getShortInfo());
        titleTX.setText(book.getTitle());
        authorTX.setText(book.getAuthor());

        titleTX.setLineWrap(true);
        authorTX.setLineWrap(true);

        if (user == null || book.getRaters().contains(user.getId().toString())) {
            addRateButton.setEnabled(false);
            r0Button.setEnabled(false);
            r1Button.setEnabled(false);
            r2Button.setEnabled(false);
            r3Button.setEnabled(false);
            r4Button.setEnabled(false);
            r5Button.setEnabled(false);
            r6Button.setEnabled(false);
            r7Button.setEnabled(false);
            r8Button.setEnabled(false);
            r9Button.setEnabled(false);
            r10Button.setEnabled(false);
            if (user == null) addReviewButton.setEnabled(false);
        } else {
            addRateButton.setEnabled(true);
            r0Button.setEnabled(true);
            r1Button.setEnabled(true);
            r2Button.setEnabled(true);
            r3Button.setEnabled(true);
            r4Button.setEnabled(true);
            r5Button.setEnabled(true);
            r6Button.setEnabled(true);
            r7Button.setEnabled(true);
            r8Button.setEnabled(true);
            r9Button.setEnabled(true);
            r10Button.setEnabled(true);
            addReviewButton.setEnabled(true);
        }
        addRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r0Button.isSelected()) dataBase.addRate(book.getTitle(), 0, user.getId());
                if (r1Button.isSelected()) dataBase.addRate(book.getTitle(), 1, user.getId());
                if (r2Button.isSelected()) dataBase.addRate(book.getTitle(), 2, user.getId());
                if (r3Button.isSelected()) dataBase.addRate(book.getTitle(), 3, user.getId());
                if (r4Button.isSelected()) dataBase.addRate(book.getTitle(), 4, user.getId());
                if (r5Button.isSelected()) dataBase.addRate(book.getTitle(), 5, user.getId());
                if (r6Button.isSelected()) dataBase.addRate(book.getTitle(), 6, user.getId());
                if (r7Button.isSelected()) dataBase.addRate(book.getTitle(), 7, user.getId());
                if (r8Button.isSelected()) dataBase.addRate(book.getTitle(), 8, user.getId());
                if (r9Button.isSelected()) dataBase.addRate(book.getTitle(), 9, user.getId());
                if (r10Button.isSelected()) dataBase.addRate(book.getTitle(), 10, user.getId());
                addRateButton.setEnabled(false);
                r0Button.setEnabled(false);
                r1Button.setEnabled(false);
                r2Button.setEnabled(false);
                r3Button.setEnabled(false);
                r4Button.setEnabled(false);
                r5Button.setEnabled(false);
                r6Button.setEnabled(false);
                r7Button.setEnabled(false);
                r8Button.setEnabled(false);
                r9Button.setEnabled(false);
                r10Button.setEnabled(false);
                ;
                avgGradeLabel.setText(String.format("%.2f", dataBase.getAvg(book.getTitle())));
                avgGradeSlider.setValue((int) (dataBase.getAvg(book.getTitle()) * 100));
            }
        });
        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addReviewTX.getText().length() > 0) {
                    dataBase.addMessage(new Review(addReviewTX.getText(), user, addReviewTX.getText().substring(0, addReviewButton.getText().length() / 4), book));
                }
            }
        });
    }

}
