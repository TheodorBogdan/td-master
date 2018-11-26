package request.requesthandlers;

import com.google.gson.GsonBuilder;
import model.User;
import repository.Repository;
import repository.RepositoryImpl;
import request.Request;
import request.Response;

import java.util.ArrayList;

public class GetUsersRequestHandler extends RequestHandler {

    @Override
    public Response handle(Request request) {

        Repository<User> repository = new RepositoryImpl<>(User.class);

        var users = repository.list();
        String response =  new GsonBuilder().create().toJson(new ArrayList<User>(users));

        return new Response(response,true);
    }
}
