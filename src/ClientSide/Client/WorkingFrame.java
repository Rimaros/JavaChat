package Client;

import ClientWindow.ClientFrame;
import ClientWindow.ClientFrameData;
import ClientWindow.ClientFrameOperations;
import HelloWindow.HelloData;
import HelloWindow.HelloFrame;
import HelloWindow.HelloFrameOperations;

import java.io.IOException;

public class WorkingFrame {

    private HelloFrame helloFrame;
    private ClientFrame clientFrame;

    public WorkingFrame(AuthenticationDate authenticationDate, HelloData helloDate,
                        HelloFrameOperations helloFrameOperations, ClientFrameOperations clientFrameOperations, ClientFrameData clientFrameData) throws IOException {

        this.clientFrame = new ClientFrame(authenticationDate, clientFrameOperations, clientFrameData, this);
        clientFrame.setVisible(false);

        this.helloFrame = new HelloFrame(authenticationDate, helloDate, helloFrameOperations, clientFrameOperations, clientFrameData, this);
        helloFrame.setVisible(true);
    }

    public void changeFrame(String index){
        if(index.equalsIgnoreCase("clientFrame")){
            clientFrame.setVisible(true);
            helloFrame.setVisible(false);
        }

        if(index.equalsIgnoreCase("helloFrame")){
            clientFrame.setVisible(false);
            helloFrame.setVisible(true);
        }
    }
}
