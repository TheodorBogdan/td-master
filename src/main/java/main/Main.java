package main;

import model.User;
import repository.RepositoryImpl;
import request.requesthandlers.GetUsersRequestHandler;
import request.requesthandlers.LoginRequestHandler;
import server.Server;

public class Main {

    private static final int PORT = 8090;

    public static void main(String[] args){

        System.out.println("Starting server on port : " + PORT);
        Server server = new Server();
        setRequestHandlers(server);

        server.start(PORT);
    }

    private static void setRequestHandlers(Server server){
        //server.addRequestHandler(,new );
        server.addRequestHandler("login", LoginRequestHandler.class);
        server.addRequestHandler("getUsers", GetUsersRequestHandler.class);
    }

}
