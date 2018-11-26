package request.requesthandlers;

import com.google.gson.GsonBuilder;
import model.User;
import org.hibernate.criterion.Restrictions;
import repository.Repository;
import repository.RepositoryImpl;
import request.Request;
import request.Response;

import javax.persistence.criteria.Predicate;
import java.util.Base64;

public class LoginRequestHandler extends RequestHandler {
    @Override
    public Response handle(Request request) {

        Repository<User> repository = new RepositoryImpl<User>(User.class);

        var user = new GsonBuilder().create().fromJson(request.getContent(), User.class);

        var actualUser = repository.findBy("name", user.getName());

        if(actualUser == null){
            return new Response("0",false);
        }

        if(actualUser.getPassword().equals( user.getPassword())){
            return new Response("0",false);
        }

        var userToken = Base64.getEncoder().encode(actualUser.getName().getBytes()).toString();

        return new Response(userToken,true);
    }
}
