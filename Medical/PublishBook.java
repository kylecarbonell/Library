package Medical;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.*;

public class PublishBook extends GUI implements ActionListener{
    Integer[] quantities = new Integer[26];
    private JPanel publishBookPanel;

    private JLabel title;

    private JTextField bookName;
    private JTextField authorName;
    private JComboBox<Integer> quantity;

    private JLabel bookNameText;
    private JLabel authorNameText;
    private JLabel quantityText;

    private JButton publishBookButton;
    private JLabel errorText;

    PublishBook(){
        //Adds numbers to combobox
        for(int i = 0; i <= 25; i++){
            quantities[i] = i;
        }

        publishBookPanel = new JPanel();
        frame.add(publishBookPanel);
        publishBookPanel.setLayout(null);

        //Title text
        title = new JLabel();
        title.setText("Create Account");
        title.setBounds(width/2 - 200, height/2 -400, 600, 50);
        title.setFont(new Font("MonoSpaced", Font.PLAIN, 50));
        title.setForeground(Color.white);
        publishBookPanel.add(title);
        
        //bookName text label
        bookNameText = new JLabel();
        bookNameText.setText("Title : ");
        bookNameText.setFont(font);
        bookNameText.setForeground(Color.white);
        bookNameText.setBounds(width/2 - 300, height/2 - 300, 400, 50);
        publishBookPanel.add(bookNameText);
        
        //bookName input box
        bookName = new JTextField();
        bookName.setForeground(Color.black);
        bookName.setFont(font);
        bookName.setBounds(width/2 - 50, height/2 - 300, 400, 50);
        publishBookPanel.add(bookName);

        //authorName text label
        authorNameText = new JLabel();
        authorNameText.setText("Author : ");
        authorNameText.setFont(font);
        authorNameText.setForeground(Color.white);
        authorNameText.setBounds(width/2 - 300, height/2 - 225, 400, 50);
        publishBookPanel.add(authorNameText);

        //Author Name inoput box
        authorName = new JTextField();
        authorName.setForeground(Color.black);
        authorName.setFont(font);
        authorName.setBounds(width/2 - 50, height/2 - 225, 400, 50);
        publishBookPanel.add(authorName);

        //quantity text label
        quantityText = new JLabel();
        quantityText.setText("Quantity : ");
        quantityText.setFont(font);
        quantityText.setForeground(Color.white);
        quantityText.setBounds(width/2 - 300, height/2 - 150, 400, 50);
        publishBookPanel.add(quantityText);

        //quantity input box
        quantity = new JComboBox<Integer>(quantities);
        quantity.setForeground(Color.black);
        quantity.setFont(font);
        quantity.setBounds(width/2 - 50, height/2 - 150, 400, 50);
        publishBookPanel.add(quantity);

        //Create Account button
        publishBookButton = new JButton();
        publishBookButton.setText("Publish Book");
        publishBookButton.setBackground(Color.lightGray);
        publishBookButton.setForeground(Color.BLACK);
        publishBookButton.setFont(font);
        publishBookButton.setBounds(width/2 -175, height/2 , 350, 50);
        publishBookButton.addActionListener(this);    
        publishBookPanel.add(publishBookButton);

        //Error Text
        errorText = new JLabel();
        errorText.setFont(font);
        errorText.setForeground(Color.white);
        errorText.setBounds(width/2 - 400, height/2 - 75, 800, 50);
        errorText.setHorizontalAlignment(SwingConstants.CENTER);
        errorText.setVerticalAlignment(SwingConstants.CENTER);
        publishBookPanel.add(errorText);

        publishBookPanel.setBackground(Color.DARK_GRAY);
    }

    public void open(){
        publishBookPanel.setVisible(true);
    }

    public void close(){
        publishBookPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String name = bookName.getText();
        String author = authorName.getText();
        Integer amount = (Integer)quantity.getSelectedItem();

        String[] info = {name, author};

        mysql.publishBook(errorText, info, amount);
    }
    
}
