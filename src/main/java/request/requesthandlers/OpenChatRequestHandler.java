package request.requesthandlers;

import com.google.gson.GsonBuilder;
import model.Chat;
import model.User;
import repository.Repository;
import repository.RepositoryImpl;
import request.Request;
import request.Response;

import java.util.ArrayList;

public class OpenChatRequestHandler extends RequestHandler{

    @Override
    public Response handle(Request request) {
        Repository<Chat> charRepository = new RepositoryImpl<>(Chat.class);


        String response =  new GsonBuilder().create().toJson(new ArrayList<User>(users));

        return new Response(response,true);
    }
}
