package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.Request;
import request.requesthandlers.RequestHandler;
import request.requesthandlers.RequestHandlerCreator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Random;

public class ClientSession implements Runnable{

    private static Logger logger = LogManager.getLogger(ClientSession.class);

    private Socket socket;
    private RequestHandlerCreator requestHandlerCreator;

    public ClientSession(Socket socket, RequestHandlerCreator requestHandlerCreator){
        this.socket = socket;
        this.requestHandlerCreator = requestHandlerCreator;
    }

    @Override
    public void run() {
        try(var objectInputStream = new ObjectInputStream(socket.getInputStream());
            var objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){
            while (socket.isConnected()){
                var rawRequest = objectInputStream.readObject();
                if(rawRequest instanceof Request){
                    var request = (Request) rawRequest;

                    Random random = new Random();
                    var requestId = random.nextInt(1000000000);

                    logger.info(" Received request " + request.getName() + " with content :"+request.getContent() + " with id: " + requestId);

                    var response = requestHandlerCreator.create(request.getName()).handle(request);

                    logger.info(" Sent response " + response.getContent() + " for the request with id: " + requestId);

                    objectOutputStream.writeObject(response);
                }
            }
        }catch (Exception ex){
            logger.error(ex);
        }
    }
}


