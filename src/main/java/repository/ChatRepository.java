package repository;

import model.Chat;
import model.User;

public interface ChatRepository extends Repository<Chat> {
    public Chat FindByParticipants(User ... users);
}
