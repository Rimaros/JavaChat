package HelloWindow;

import Client.AuthenticationDate;
import Client.WorkingFrame;
import ClientWindow.ClientFrame;
import ClientWindow.ClientFrameData;
import ClientWindow.ClientFrameOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

public class HelloFrame extends JFrame {

    //private WorkingFrame workingFrame;

    public HelloFrame(AuthenticationDate authenticationDate, HelloData helloData,
                      HelloFrameOperations helloFrameOperations, ClientFrameOperations clientFrameOperations, ClientFrameData clientFrameData, WorkingFrame workingFrame) throws IOException {

        //workingFrame = new WorkingFrame(this, clientFrame);

        // Form создаем окно, фиксируем размер, добавляем заглавие и программную обработку закрытия окна
        setBounds(900, 200, 400, 170);
        setTitle("Hello, Friend! Welcome to WebMessanger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // все компоненты кроме лэйбла и кнопки добавляем в панель
        helloData.nameField.setVisible(false);
        helloData.backButton.setVisible(false);
        helloData.sendRegistryData.setVisible(false);

        // добавляем в окно панель с содержимым, кнопку и лэйбл
        add(helloData.inputWindowPanel, BorderLayout.CENTER);
        add(helloData.label, BorderLayout.NORTH);
        add(helloData.buttonPanel, BorderLayout.SOUTH);

        // Send AuthData - описание действия ================================================================
        helloData.sendAuthData.addActionListener(actionEvent -> {
            if(!helloData.loginField.getText().trim().isEmpty() && helloData.passwordField.getPassword().length > 0){
                try {
                    if(helloFrameOperations.goToClientFrame(helloData, authenticationDate, clientFrameOperations, clientFrameData)) workingFrame.changeFrame("clientFrame");//setVisible(false);
                            else new JOptionPane("Authentication is fail. Please repeat!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else new JOptionPane("Login and Password fields must have content!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
        });

        helloData.sendRegistryData.addActionListener(actionEvent -> {
            if(!helloData.loginField.getText().trim().isEmpty() && helloData.passwordField.getPassword().length > 0 && !helloData.nameField.getText().trim().isEmpty()){
                try {
                    if(helloFrameOperations.goToClientFrame(helloData, authenticationDate, clientFrameOperations, clientFrameData)) workingFrame.changeFrame("clientFrame");
                        else new JOptionPane("Registration is fail. Please repeat!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else  new JOptionPane("Login, Password and Name fields must have content!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
        });

        // Registry - описание действия =====================================================================
        helloData.registryButton.addActionListener(actionEvent -> {
            helloData.nameField.setVisible(true);
            helloData.backButton.setVisible(true);
            helloData.sendRegistryData.setVisible(true);
            helloData.sendAuthData.setVisible(false);
            helloData.registryButton.setVisible(false);
        });

        helloData.backButton.addActionListener(actionEvent -> {
            helloData.registryButton.setVisible(true);
            helloData.sendAuthData.setVisible(true);
            helloData.nameField.setVisible(false);
            helloData.backButton.setVisible(false);
            helloData.sendRegistryData.setVisible(false);
        });

        // clean login field =================================================================================
        helloData.loginField.addFocusListener( new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                helloData.loginField.setText("");
            }
        });

        // clean password field =================================================================================
        helloData.passwordField.addFocusListener( new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                helloData.passwordField.setText("");
            }
        });

        // clean name field =================================================================================
        helloData.nameField.addFocusListener( new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                helloData.nameField.setText("");
            }
        });

        setVisible(false);
    }
}


/*String typeOfSendMessage = helloFrameOperations.getAuthDate(helloDate, authenticationDate);
                helloFrameOperations.sendMessage(authenticationDate, typeOfSendMessage);
                Boolean authorizationResult = helloFrameOperations.antsworthWait(authenticationDate.in);
                if(authorizationResult){
                    setVisible(false);
                    new ClientFrame(authenticationDate, helloFrameOperations);
                }*/