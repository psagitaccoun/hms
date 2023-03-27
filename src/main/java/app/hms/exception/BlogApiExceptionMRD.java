package app.hms.exception;

public class BlogApiExceptionMRD extends RuntimeException{

    private String mr;
    private long mr1;
    private String p;
    private long p1;



    public BlogApiExceptionMRD( String mr, long mr1, String p, long p1) {
        super(String.format("%s having id:'%s',not belongs to %s with id:'%s'",mr,mr1,p,p1));
        this.mr = mr;
        this.mr1 = mr1;
        this.p = p;
        this.p1 = p1;
    }

    public String getMr() {
        return mr;
    }

    public long getMr1() {
        return mr1;
    }

    public String getP() {
        return p;
    }

    public long getP1() {
        return p1;
    }
}
