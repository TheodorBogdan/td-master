package request.requesthandlers;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class RequestHandlerCreator {

    private Map<String,  Class> reqeustHandlers;

    public RequestHandlerCreator(Map<String,  Class> reqeustHandlers){
        this.reqeustHandlers = reqeustHandlers;
    }

    public RequestHandler create(String requestName) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        var constructors = reqeustHandlers.get(requestName).getConstructors();

        return (RequestHandler) constructors[0].newInstance();
    }
}
