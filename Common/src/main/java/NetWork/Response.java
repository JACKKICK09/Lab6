package NetWork;

import java.io.Serializable;

public class Response implements Serializable {
    private String messege;
    private String getCollection;

    public Response(String messege, String getCollection) {
        this.messege = messege;
        this.getCollection = getCollection;
    }

    public String getMessege() {
        return messege;
    }

    public String getGetCollection() {
        return getCollection;
    }

}
