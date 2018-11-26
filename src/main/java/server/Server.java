package server;

import request.Request;
import request.requesthandlers.RequestHandler;
import request.requesthandlers.RequestHandlerCreator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;

    public Server(){
        reqeustHandlers = new HashMap<String, Class>();
    }

    private Map<String,  Class> reqeustHandlers;

    public void addRequestHandler(String requestName, Class requestHandler){
        this.reqeustHandlers.put(requestName, requestHandler);
    }

    public void start(int port){

        ExecutorService executorService= Executors.newCachedThreadPool();
        try {
            System.out.println("Runs");
            serverSocket= new ServerSocket(port);

            while(true){
                try {

                    Socket socket = serverSocket.accept();

                    executorService.execute(
                            new ClientSession(
                                    socket,
                                    new RequestHandlerCreator(this.reqeustHandlers))
                    );
                } catch (Exception e) {
                    //logger.warn("error in serverComponent "+e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //logger.error("Error in run catch1 :"+ e.getMessage());
        }
        finally {
            try {
                System.out.println("server close");
                serverSocket.close();
            } catch (IOException e) {
                //logger.error("Error closing the server socket"+ e.getMessage());
            }
        }

    }



    private Request deserializeRequest(byte [] rawRequest){
        try (var byteArrayInputStream = new ByteArrayInputStream(rawRequest);
             var objectInputStream = new ObjectInputStream(byteArrayInputStream);){
            Request request = (Request) objectInputStream.readObject();
            return request;
        }catch(Exception ex){
            return (Request) null;
        }
    }
}
