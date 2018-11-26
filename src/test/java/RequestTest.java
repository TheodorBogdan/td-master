import model.User;
import org.junit.Assert;
import org.junit.Test;
import request.Request;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RequestTest {
    @Test
    public void Serialize(){

        var user = new User();
        user.setName("username");

        Request request = new Request(user,"name","token");

        var serializedObject = new byte[0];

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObject);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);){
            objectOutputStream.writeObject(request);

            serializedObject = byteArrayInputStream.readAllBytes();

            var deserializedObject = (Request) objectInputStream.readObject();

            Assert.assertEquals("name", request.getName());
            Assert.assertEquals("token", request.getUserToken());
            Assert.assertEquals("username", ((User)request.getContent()).getName());
        }catch (Exception ex){

        }


    }
}
