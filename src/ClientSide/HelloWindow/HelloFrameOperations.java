package HelloWindow;

import Client.AuthenticationDate;
import ClientWindow.ClientFrame;
import ClientWindow.ClientFrameData;
import ClientWindow.ClientFrameOperations;

import java.io.IOException;

public class HelloFrameOperations {

    void sendMessage (AuthenticationDate authenticationDate, String operationID){

        switch (operationID){
            case "authentication":
                authenticationDate.getOut().println(operationID + "_" + authenticationDate.getLogin() + authenticationDate.getPassword());
                break;
            case "authorization":
                authenticationDate.getOut().println(operationID + "_" + authenticationDate.getLogin() + authenticationDate.getPassword() + authenticationDate.getName());
                break;
        }
        authenticationDate.getOut().flush();
        antsworthWait(authenticationDate); // ждем ответ
    }

    String getAuthDate (HelloData helloData, AuthenticationDate authenticationDate){
        String operationID = "authorization";
        char[] passwordCharMassiv;
        StringBuilder password = new StringBuilder();

        passwordCharMassiv = helloData.passwordField.getPassword();
        for(char passwordChar : passwordCharMassiv){
            password.append(String.valueOf(passwordChar));
        }

        authenticationDate.setLogin("_" + helloData.loginField.getText());
        authenticationDate.setPassword("_" + password.toString());

        if(!helloData.nameField.getText().trim().isEmpty()){
            authenticationDate.setName("_" + helloData.nameField.getText());
            operationID = "authentication";
        }
        return operationID;
    }

   /* boolean antsworthWait(BufferedReader in) { // проверить участок кода

        while (true) {
            try {
                String answer = in.readLine();

                switch (answer.toLowerCase()){
                    case ("authentication_done"):
                        new JOptionPane("Operation is successfully", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        return true;
                    case ("authentication_authdate_is_wrong"):
                        new JOptionPane("AuthDate is wrong", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        break;
                    case ("authentication_server_is_down"):
                        new JOptionPane("Connect with Server is LOST", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        break;
                    default:
                        new JOptionPane("Unknown ERROR", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        break;
                }
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    boolean goToClientFrame(HelloData helloData, AuthenticationDate authenticationDate, ClientFrameOperations clientFrameOperations, ClientFrameData clientFrameData) throws IOException {
        String typeOfSendMessage = getAuthDate(helloData, authenticationDate);
        sendMessage(authenticationDate, typeOfSendMessage);
        String authenticationResult = "ff";//authenticationDate.getReadThread().getResultAnswerObject();

        if(authenticationResult.equalsIgnoreCase("auth_done")){
            //new ClientFrame(authenticationDate, clientFrameOperations, clientFrameData);
            return true;
        } else {
            helloData.loginField.grabFocus();  // focus to loginField
            return false;
        }
    }

    void antsworthWait(AuthenticationDate authenticationDate){
        authenticationDate.getReadThread().workWithObjectAnswer(); // запускаем последовательность наполняющую ответ-объект
    }
}


    /*boolean antsworthWait(BufferedReader in) { // проверить участок кода

        while (true) {
            try {
                String answer = in.readLine();

                switch (answer.toLowerCase()){
                    case ("authentication_done"):
                        new JOptionPane("Operation is successfully", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        return true;
                    case ("authentication_authdate_is_wrong"):
                        new JOptionPane("AuthDate is wrong", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        break;
                    case ("authentication_server_is_down"):
                        new JOptionPane("Connect with Server is LOST", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        break;
                    default:
                        new JOptionPane("Unknown ERROR", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        break;
                }
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/