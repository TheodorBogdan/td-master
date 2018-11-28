package repository;

import model.Chat;
import model.User;

public class ChatRepositoryImpl extends RepositoryImpl<Chat> implements ChatRepository {

    public ChatRepositoryImpl(){
        super(Chat.class);
    }

    @Override
    public Chat FindByParticipants(User... users) {
        entityManager.getTransaction().begin();

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(entityClass);
        var root = query.from(entityClass);

        //query.where(criteriaBuilder.in(""));

        var result = entityManager.createQuery(query).getSingleResult();

        entityManager.getTransaction().commit();
        return result;
    }
}
