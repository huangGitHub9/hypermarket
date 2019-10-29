package cn.xu.dygl.movieType.exception;

public class MovieTypeException extends  Exception {
    public MovieTypeException() {
        super();
    }

    public MovieTypeException(String message) {
        super(message);
    }

    public MovieTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieTypeException(Throwable cause) {
        super(cause);
    }
}
