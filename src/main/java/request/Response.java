package request;

import java.io.Serializable;

public class Response implements Serializable {

    public String getContent() {
        return content;
    }

    private String content;

    public boolean isSuccess() {
        return success;
    }

    private boolean success;

    public Response(String content, boolean success){
        this.content = content;
        this.success = success;
    }


}
