package server;

import request.Request;
import request.requesthandlers.RequestHandler;
import request.requesthandlers.RequestHandlerCreator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientSession implements Runnable{

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

                    objectOutputStream.writeObject(requestHandlerCreator.create(request.getName()).handle(request));
                }
            }
        }catch (Exception ex){

        }
    }
}


