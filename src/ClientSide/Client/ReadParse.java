package Client;

import ClientWindow.ClientFrameData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

class ReadParse {

    void answerParser (String answer, AnswerObject answerObject){
        answerObject.resultAnswer.clear();
        answerObject.resultAnswer.addAll(Arrays.asList(answer.split("_")));
    }

    void responseProcessing (AnswerObject answerObject, ClientFrameData clientFrameData, ArrayList<String> contacts, String resultAnswerObjectSystem, String resultObjectFromSystem){
        ArrayList<String> bufferAnswer = new ArrayList<>();

        bufferAnswer.clear();
        bufferAnswer.addAll(answerObject.resultAnswer);
        resultAnswerObjectSystem = null;
        resultObjectFromSystem = null;

        switch (bufferAnswer.get(0)){
            case "authentication":
                if(bufferAnswer.get(1).equalsIgnoreCase("done")) { //authorization success
                    new JOptionPane("Registration is successfully", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    resultAnswerObjectSystem = "auth_done";
                }
                if(bufferAnswer.get(1).equalsIgnoreCase("dateIsWrong")) { //authorization fail
                    new JOptionPane("AuthDate is wrong", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    resultAnswerObjectSystem = "auth_fail";
                }
                if(bufferAnswer.get(1).equalsIgnoreCase("serverIsDown")) { //server is down
                    new JOptionPane("Connect with Server is LOST", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    resultAnswerObjectSystem = "auth_fail";
                }
                break;

            case "search":
                if(bufferAnswer.get(1).equalsIgnoreCase("clientNotFind")){
                    resultAnswerObjectSystem = "client_not_find";
                }
                if(bufferAnswer.get(1).equalsIgnoreCase("done")){
                    contacts.add(bufferAnswer.get(2));
                    resultAnswerObjectSystem = "search_done";
                }
                break;

            case "getContactList":
                if(bufferAnswer.get(1).equalsIgnoreCase("empty")){
                    resultAnswerObjectSystem = "contacts_empty";
                } else {
                    contacts.addAll(bufferAnswer);
                    contacts.remove(0);
                    resultAnswerObjectSystem = "contacts_done";
                }
                break;

            case "message":
                String fromContact;
                fromContact = bufferAnswer.get(1);
                StringBuilder newMessage;

                if(!clientFrameData.changedContact.equals(fromContact)){
                    if(clientFrameData.waitingContacts.size() < 1 || !clientFrameData.waitingContacts.contains(fromContact)){
                        clientFrameData.waitingContacts.add(fromContact);
                    }
                    newMessage = clientFrameData.contactsMap.get(fromContact).append("\n").append(bufferAnswer.get(2));
                    clientFrameData.contactsMap.put(fromContact, newMessage);
                }
                else
                clientFrameData.chatField.append("                    " + bufferAnswer.get(2) + "\n");
                resultAnswerObjectSystem = "message_done";
                break;
        }
    }
}
















   /* void answerParser (String answer, AnswerObject answerObject){
        answerObject.resultAnswer.clear();
        answerObject.resultAnswer.addAll(Arrays.asList(answer.split("_")));
    }

    void responseProcessing (AnswerObject answerObject, ClientFrameData clientFrameData, ArrayList<String> contacts){
        String resultAnswerObject = null;
        ArrayList<String> bufferAnswer = new ArrayList<>();

        bufferAnswer.clear();
        bufferAnswer.addAll(answerObject.resultAnswer);

        switch (bufferAnswer.get(0)){
            case "authentication":
                if(bufferAnswer.get(1).equalsIgnoreCase("done")) { //authorization success
                    new JOptionPane("Registration is successfully", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    resultAnswerObject = "auth_done";
                }
                if(bufferAnswer.get(1).equalsIgnoreCase("dateIsWrong")) { //authorization fail
                    new JOptionPane("AuthDate is wrong", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    resultAnswerObject = "auth_fail";
                }
                if(bufferAnswer.get(1).equalsIgnoreCase("serverIsDown")) { //server is down
                    new JOptionPane("Connect with Server is LOST", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    resultAnswerObject = "auth_fail";
                }
                break;

            case "search":
                if(bufferAnswer.get(1).equalsIgnoreCase("clientNotFind")){
                    resultAnswerObject = "client_not_find";
                }
                if(bufferAnswer.get(1).equalsIgnoreCase("done")){
                    contacts.add(bufferAnswer.get(2));
                    resultAnswerObject = "search_done";
                }
                break;

            case "getContactList":
                if(bufferAnswer.get(1).equalsIgnoreCase("empty")){
                    resultAnswerObject = "contacts_empty";
                } else {
                    contacts.addAll(bufferAnswer);
                    contacts.remove(0);
                    resultAnswerObject = "contacts_done";
                }
                break;

            case "message":
                String fromContact;
                fromContact = bufferAnswer.get(1)
                clientFrameData.chatField.append("                    " + bufferAnswer.get(2) + "\n");
                resultAnswerObject = "message_done";
                break;
        }
        return resultAnswerObject;
    }
}*/