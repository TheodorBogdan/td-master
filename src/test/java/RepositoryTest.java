import junit.framework.TestCase;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;
import repository.RepositoryImpl;

public class RepositoryTest extends TestCase {

    Repository<User> repository;

    @Override
    public void setUp(){
        repository = new RepositoryImpl<User>(User.class);
    }

    @Test
    public void createTest(){
        var toCreate = new User();
        toCreate.setName("__TEST__");

        repository.create(toCreate);

        Assert.assertNotSame(0 ,toCreate.getId());
        Assert.assertEquals("__TEST__" ,toCreate.getName());
    }

    @Test
    public void findAllTest(){
        var user1 = new User();
        user1.setName("__TEST__");
        var user2 = new User();
        user2.setName("__TEST_2__");


        repository.create(user1);
        repository.create(user2);

        var found = repository.list();

        Assert.assertEquals(2,found.size());
        Assert.assertNotSame(0 ,found.get(0).getId());
        Assert.assertEquals("__TEST__" ,found.get(0).getName());
    }

    @Test
    public void test(){
        Assert.assertEquals(0,0);
    }
}
