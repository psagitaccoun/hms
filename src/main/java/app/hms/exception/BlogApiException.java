package app.hms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BlogApiException extends RuntimeException {


    private String n1;
    private String n2;
    private long v;

    public BlogApiException( String n1, String n2, long v) {
        super(String.format("%s is not belongs to %s having id:'%s'",n1,n2,v));
        this.n1 = n1;
        this.n2 = n2;
        this.v = v;
    }

    public String getN1() {
        return n1;
    }

    public String getN2() {
        return n2;
    }

    public long getV() {
        return v;
    }
}
