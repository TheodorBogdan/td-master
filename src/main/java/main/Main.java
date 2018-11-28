package main;

import request.requesthandlers.GetUsersRequestHandler;
import request.requesthandlers.LoginRequestHandler;
import server.Server;

public class Main {

    private static final int PORT = 8090;

    //TODO Criptat chestii RSA

    public static void main(String[] args){

        System.out.println("Starting server on port : " + PORT);
        Server server = new Server();
        setRequestHandlers(server);

        server.start(PORT);
//        var user = new User(0,"AnotherUser","123456");
//
//        Repository<User> userRepository = new RepositoryImpl<>(User.class);
//        userRepository.create(user);
    }

    private static void setRequestHandlers(Server server){
        //server.addRequestHandler(,new );
        server.addRequestHandler("login", LoginRequestHandler.class);
        server.addRequestHandler("getUsers", GetUsersRequestHandler.class);
    }

}
