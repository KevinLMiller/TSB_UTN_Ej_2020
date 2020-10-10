package suport;

public class TSBArrayListIOException extends Exception {
    private String message = "Problema al serializar la lista";

    public TSBArrayListIOException(){}

    public TSBArrayListIOException(String msg){
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
