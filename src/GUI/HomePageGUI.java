package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import bookrateback.*;

public class HomePageGUI extends Thread {
    private JPanel panelHome;
    private JButton logInButton;
    private JButton addNewsButton;
    private JButton adminButton;
    private JTextField searchBookTX;
    private JButton searchBookButton;
    private JButton addBookButton;
    private JLabel newsHeaderLabel;
    private JPanel firstBookPanel;
    private JPanel secondBookPanel;
    private JPanel thirdBookPanel;
    private JPanel fourthBookPanel;
    private JLabel firstBookAvgRate;
    private JLabel secondBookAvgRate;
    private JLabel thirdBookAvgRate;
    private JLabel fourthBookAvgRate;
    private JPanel newsTransferPanel;
    private JTextArea firstBookTitle;
    private JTextArea firstBookAuthor;
    private JTextArea firstBookName;
    private JTextArea secondBookTitle;
    private JTextArea secondBookAuthor;
    private JTextArea thirdBookTitle;
    private JTextArea thirdBookAuthor;
    private JTextArea fourthBookTitle;
    private JTextArea fourthBookAuthor;
    private JTextArea secondBookName;
    private JTextArea thirdBookName;
    private JTextArea fourthBookName;
    private JTextArea newsText;
    private DataBase dataBase;
    private ActiveUser activeUser;
    private News[] lastFiveNews;
    private int actualNews;

    public HomePageGUI() {
        dataBase = new DataBase();
        activeUser = new ActiveUser(null);
        start();
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeUser.getUser() == null) {
                    JFrame logInFrame = new LoginGUI(activeUser);
                    logInFrame.setSize(750, 375);
                    logInFrame.setResizable(false);
                    logInFrame.setVisible(true);
                }
                else{
                    activeUser.setUser(null);
                    logInButton.setText("LOG IN");
                    addBookButton.setEnabled(false);
                    addNewsButton.setEnabled(false);
                    adminButton.setEnabled(false);
                    searchBookButton.setText("SEARCH BOOK");
                }
            }
        });
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addBookFrame = new AddBookGUI(activeUser.getUser());
                addBookFrame.setSize(500, 410);
                addBookFrame.setResizable(false);
                addBookFrame.setVisible(true);
            }
        });
        addNewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addNewsFrame = new AddNewsGUI(activeUser.getUser());
                addNewsFrame.pack();
                addNewsFrame.setVisible(true);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminPanelFrame = new AdminPanelGUI();
                adminPanelFrame.pack();
                adminPanelFrame.setVisible(true);
            }
        });
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dataBase.getBook(searchBookTX.getText()) != null) {
                    JFrame bookRateGUI = new BookRateGUI(dataBase.getBook(searchBookTX.getText()), activeUser.getUser());
                    bookRateGUI.pack();
                    bookRateGUI.setVisible(true);
                    searchBookTX.setText("");
                } else if (activeUser.getUser() != null && searchBookTX.getText().length() >= 1) {
                    JFrame addBookGUI = new AddBookGUI(searchBookTX.getText(), activeUser.getUser());
                    addBookGUI.pack();
                    addBookGUI.setVisible(true);
                    searchBookTX.setText("");
                }
            }
        });
        secondBookTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame bookRateGUI = new BookRateGUI(dataBase.getBook(secondBookTitle.getText()), activeUser.getUser());
                bookRateGUI.pack();
                bookRateGUI.setVisible(true);
            }
        });
        refresh();
        refreshBooks();
        lastFiveNews = dataBase.getLastNews();
        refreshNews(4);
        firstBookTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame bookRateGUI = new BookRateGUI(dataBase.getBook(firstBookTitle.getText()), activeUser.getUser());
                bookRateGUI.pack();
                bookRateGUI.setVisible(true);
            }
        });
        thirdBookTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame bookRateGUI = new BookRateGUI(dataBase.getBook(thirdBookTitle.getText()), activeUser.getUser());
                bookRateGUI.pack();
                bookRateGUI.setVisible(true);
            }
        });
        fourthBookTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame bookRateGUI = new BookRateGUI(dataBase.getBook(fourthBookTitle.getText()), activeUser.getUser());
                bookRateGUI.pack();
                bookRateGUI.setVisible(true);
            }
        });
        newsTransferPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame newsGUI = new NewsGUI(lastFiveNews[actualNews]);
                newsGUI.pack();
                newsGUI.setVisible(true);
            }
        });
        newsText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame newsGUI = new NewsGUI(lastFiveNews[actualNews]);
                newsGUI.pack();
                newsGUI.setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        int i = 0;
        actualNews = 4;
        while (true) {
            super.run();
            try {
                refresh();
                sleep(200);
                i++;
                if (i >= 100) {
                    refreshBooks();
                    if (actualNews <= 0) {
                        actualNews = 4;
                        lastFiveNews = dataBase.getLastNews();
                    } else actualNews -= 1;
                    refreshNews(actualNews);
                    i = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void refresh() {
        if (activeUser.getUser() != null) {
            addBookButton.setEnabled(true);
            logInButton.setText("LOG OUT");
            searchBookButton.setText("SEARCH & RATE BOOK");
            if (activeUser.getUser() instanceof Admin) {
                adminButton.setEnabled(true);
                addNewsButton.setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BookRate");
        frame.setContentPane(new HomePageGUI().panelHome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void refreshNews(int i) {
        newsText.setLineWrap(true);
        News[] news = dataBase.getLastNews();
        newsHeaderLabel.setText(news[i].getTitle());
        newsText.setText((news[i].getText()));
        newsText.setLineWrap(true);
    }

    private void refreshBooks(){
        Book[] books = dataBase.getRandomBooks();

        firstBookTitle.setLineWrap(true);
        firstBookAuthor.setLineWrap(true);
        firstBookName.setLineWrap(true);

        firstBookTitle.setText(books[0].getTitle());
        firstBookAuthor.setText(books[0].getAuthor());
        firstBookAvgRate.setText(String.format("%.2f",dataBase.getAvg(books[0].getTitle())));
        firstBookName.setText(String.format(  "%1$S - %2$s", books[0].getTitle(), books[0].getAuthor()));

        secondBookTitle.setLineWrap(true);
        secondBookAuthor.setLineWrap(true);
        secondBookName.setLineWrap(true);

        secondBookTitle.setText(books[1].getTitle());
        secondBookAuthor.setText(books[1].getAuthor());
        secondBookAvgRate.setText(String.format("%.2f",dataBase.getAvg(books[1].getTitle())));
        secondBookName.setText(String.format(  "%1$S - %2$s", books[1].getTitle(), books[1].getAuthor()));

        thirdBookTitle.setLineWrap(true);
        thirdBookAuthor.setLineWrap(true);
        thirdBookName.setLineWrap(true);

        thirdBookTitle.setText(books[2].getTitle());
        thirdBookAuthor.setText(books[2].getAuthor());
        thirdBookAvgRate.setText(String.format("%.2f",dataBase.getAvg(books[2].getTitle())));
        thirdBookName.setText(String.format(  "%1$S - %2$s", books[2].getTitle(), books[2].getAuthor()));

        fourthBookTitle.setLineWrap(true);
        fourthBookAuthor.setLineWrap(true);
        fourthBookName.setLineWrap(true);

        fourthBookTitle.setText(books[3].getTitle());
        fourthBookAuthor.setText(books[3].getAuthor());
        fourthBookAvgRate.setText(String.format("%.2f",dataBase.getAvg(books[3].getTitle())));
        fourthBookName.setText(String.format(  "%1$S - %2$s", books[3].getTitle(), books[3].getAuthor()));

    }
}
