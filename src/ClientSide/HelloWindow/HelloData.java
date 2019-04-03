package HelloWindow;

import javax.swing.*;
import java.awt.*;

public class HelloData {

    JTextField loginField;
    JTextField nameField;
    JPasswordField passwordField;
    JPanel inputWindowPanel;
    JPanel buttonPanel;
    JLabel label;

    JButton sendAuthData;
    JButton sendRegistryData;
    JButton registryButton;
    JButton backButton;

    public HelloData(){

        // Поля ввода
        this.loginField = new JTextField("Login");
        this.loginField.setFont(new Font("Arial", Font.BOLD, 12));
        this.loginField.setPreferredSize(new Dimension(400, 50));

        this.nameField = new JTextField("Name");
        this.nameField.setFont(new Font("Arial", Font.BOLD, 12));
        this.nameField.setPreferredSize(new Dimension(400, 50));

        this.passwordField = new JPasswordField("Password");
        this.passwordField.setFont(new Font("Arial", Font.BOLD, 12));
        this.passwordField.setPreferredSize(new Dimension(400, 50));

        // Создаем панель с содержанием полей ввода
        this.inputWindowPanel = new JPanel(new BorderLayout());
        this.inputWindowPanel.add(this.loginField, BorderLayout.NORTH);
        this.inputWindowPanel.add(this.passwordField, BorderLayout.CENTER);
        this.inputWindowPanel.add(this.nameField, BorderLayout.SOUTH);

        // Кнопки
        this.sendAuthData = new JButton("Done");
        this.sendRegistryData = new JButton("Done");
        this.registryButton = new JButton("Registry new person");
        this.backButton = new JButton("Back to login");

        // Панель кнопок
        this.buttonPanel = new JPanel(new BorderLayout());
        this.buttonPanel.add(this.sendAuthData, BorderLayout.EAST);
        this.buttonPanel.add(this.sendRegistryData, BorderLayout.EAST);
        this.buttonPanel.add(this.registryButton, BorderLayout.WEST);
        this.buttonPanel.add(this.backButton, BorderLayout.WEST);

        // Лэйбл
        this.label = new JLabel("Enter your Name and Pass: ");
    }
}
