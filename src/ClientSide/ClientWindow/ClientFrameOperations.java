package ClientWindow;

import Client.AuthenticationDate;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SplittableRandom;

public class ClientFrameOperations {

    boolean searchClient(String operationID, AuthenticationDate authenticationDate) {

        String clientForSearch = JOptionPane.showInputDialog("Enter " + operationID.toUpperCase() + " of client for search: ");
        String clientSearchResult;

        operationID = "search" + operationID;
        clientForSearch = "_" + clientForSearch;

        sendMessage(authenticationDate, operationID, clientForSearch);
        clientSearchResult = "fff";//authenticationDate.getReadThread().getResultAnswerObject();
        if(clientSearchResult.equalsIgnoreCase("search_done")){
            return true;
        }
        else {
            new JOptionPane("This Client not find in the System!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
            return false;
        }
    }

    void sendMessage(AuthenticationDate authenticationDate, String operationID, String message) {
            authenticationDate.getOut().println(operationID + authenticationDate.getLogin() + message);
            authenticationDate.getOut().flush();
            if(!operationID.equalsIgnoreCase("message")){  // для всех типов данных кроме сообщения ждем ответ
                antsworthWait(authenticationDate); // ждем ответ
            }
    }

   /* void addToClientList (AuthenticationDate authenticationDate, String clientFind){
        //String operationID = "addClient";
        //clientFind = "_" + clientFind;
        authenticationDate.getContacts().add(clientFind); // + "       " + "\n");
        //sendMessage(authenticationDate, operationID, clientFind);
    }*/

    void getDataAboutContacts (AuthenticationDate authenticationDate) throws IOException {  // получить данные о контактах у пользователя
        String operationID = "getContactList";
        sendMessage(authenticationDate, operationID, authenticationDate.getLogin());
    }

    void antsworthWait(AuthenticationDate authenticationDate){
        authenticationDate.getReadThread().workWithObjectAnswer(); // запускаем последовательность проверяющую ответ-объект
    }
}






//============================================================================================================




//searchResultClient = antsworthWait(authenticationDate.in); searchClient



/* String antsworthWait(BufferedReader in) {
        while (true) {
            try {
                String answer = in.readLine();
                for(String answerParse: answer.split("_")){
                    switch (answerParse.)
                }

                if (answer.toLowerCase().contains("done")) {
                    return answer;
                } else if (answer.toLowerCase().contains("clientnotfind")) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/


 /*String responseProcessing (AuthenticationDate authenticationDate, ClientFrameData clientFrameData){
        String requestResult = null;
        ArrayList<String> bufferAnswer = new ArrayList<>();

        bufferAnswer.clear();
        bufferAnswer.addAll(authenticationDate.resultAnswerObject.resultAnswer);

        switch (bufferAnswer.get(0)){
            case "helloFrame":
                if(bufferAnswer.get(1).equalsIgnoreCase("authentication")) { //authorization

                }

            case "search":
                if(bufferAnswer.get(1).equalsIgnoreCase("clientnotfind")){
                    requestResult = null;
                } else requestResult = bufferAnswer.get(1);
                break;

            case "getContactList":
                if(bufferAnswer.get(1).equalsIgnoreCase("empty")){
                    requestResult = null;
                } else {
                    authenticationDate.getContacts().addAll(bufferAnswer);
                    authenticationDate.getContacts().remove(0);
                    requestResult = "done";
                }
                break;

            case "message":
                clientFrameData.chatField.append(bufferAnswer.get(1) + "\n");
                break;
        }
        return requestResult;
    }*/

    /*void getDataAboutContacts (AuthenticationDate authenticationDate) throws IOException {
        String operationID = "getContactList";

        sendMessage(authenticationDate, operationID, authenticationDate.getLogin());
        //String operationResult = authenticationDate.getReadThread().getResultAnswerObject();
        //authenticationDate.getReadThread().getResultAnswerObject();
        *//*String contactsArray[] = contactsToString.split("_");
        authenticationDate.getContacts().addAll(Arrays.asList(contactsArray));*//*
    }*/

 /*void sendMsg(JTextField textMessage, JTextArea textArea, PrintWriter out, String login) {
        String message = textMessage.getText();
        out.println(message + login);
        out.flush();
        textMessage.setText("");
        textArea.append("\nI :    " + message + "\n\n");
    }*/