package request.validation;

import model.User;
import repository.Repository;
import repository.RepositoryImpl;

public class UserTokenValidator {

    public boolean validate(String userToken){
        Repository<User> repo = new RepositoryImpl<>(User.class);
        var user = repo.findBy("name", userToken);

        return user == null;
    }

}
