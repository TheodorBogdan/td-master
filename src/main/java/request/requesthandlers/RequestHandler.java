package request.requesthandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.Request;
import request.Response;
import server.Server;

public abstract class RequestHandler {

    private static Logger logger = LogManager.getLogger(RequestHandler.class);

    public abstract Response handle(Request request);
}
