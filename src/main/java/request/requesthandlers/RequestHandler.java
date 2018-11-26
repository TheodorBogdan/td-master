package request.requesthandlers;

import request.Request;
import request.Response;

public abstract class RequestHandler {
    public abstract Response handle(Request request);
}
