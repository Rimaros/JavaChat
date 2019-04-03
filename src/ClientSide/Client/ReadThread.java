package Client;

import ClientWindow.ClientFrameData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReadThread implements Runnable {

    private BufferedReader in;

    private ReadParse readParse;
    private AnswerObject answerObject;
    private ArrayList<String> contacts;
    private ClientFrameData clientFrameData;
    private String resultAnswerObjectSystem;
    private String resultObjectFromSystem;
    private boolean getFlag = false;  // флаг считывания объекта resultAnswerObject
    private String answer;

    ReadThread(BufferedReader in, ArrayList<String> contacts, ClientFrameData clientFrameData){ //JTextArea textArea
        this.in = in;
        this.readParse = new ReadParse();
        this.answerObject = null;
        this.contacts = contacts;
        this.clientFrameData = clientFrameData;
        this.resultAnswerObjectSystem = null;
        this.resultObjectFromSystem = null;
        this.answer = null;
    }

    @Override
    public void run() {


        try {
            while (true) {
                answer = in.readLine();
                    readParse.answerParser(answer, answerObject);
                    readParse.responseProcessing(answerObject, clientFrameData, contacts, resultAnswerObjectSystem, resultObjectFromSystem); // для аутентификации done или fail
            }
        } catch (IOException e){
            System.out.println("Connect is closed! Sorry.....");
        }
    }

    public void workWithObjectAnswer(){

        while (true) { // вечный цикл пока в ответ не капнет объект
            if(resultAnswerObjectSystem!=null) {
                getFlag = true;
                break;
            }
        }
    }
}



    /*private JTextArea textArea;
    this.textArea = textArea;
    textArea.append(fserver + "\n");*/

    /*
    try {
            while (true){
            answer = in.readLine();
            getFlag = false;
            while (!getFlag){
            try {
            wait(10);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            }
            }
            } catch (IOException e){
            System.out.println("Connect is closed! Sorry.....");
            }
            }

public void workWithObjectAnswer(){

        while (true) { // вечный цикл пока в ответ не капнет объект
        if(answer!=null) {
        readParse.answerParser(answer, answerObject);
        resultAnswerObject = readParse.responseProcessing(answerObject, clientFrameData, contacts); // для аутентификации done или fail
        answer = null;
        getFlag = true;
        break;
        }
        }
        }*/



   /*  try {
             while (true) {
             answer = in.readLine();
             getFlag = false;
             while (!getFlag) {
             readParse.answerParser(answer, answerObject);
             resultAnswerObject = readParse.responseProcessing(answerObject, clientFrameData, contacts); // для аутентификации done или fail

             if(resultAnswerObject.equalsIgnoreCase("message_done")){
             getFlag = true;
             }
             }
             }
             } catch (IOException e){
             System.out.println("Connect is closed! Sorry.....");
             }
             }*/