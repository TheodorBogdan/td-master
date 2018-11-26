package request;

import java.io.*;

public class Request implements Serializable {

    private String content;
    private String name;

    public String getUserToken() {
        return userToken;
    }

    private String userToken;

    public Request(String content, String name, String userToken){
        this.content = content;
        this.name = name;
        this.userToken = userToken;
    }

    public String getContent(){
        return content;
    }

    public String getName(){
        return this.name;
    }
}
