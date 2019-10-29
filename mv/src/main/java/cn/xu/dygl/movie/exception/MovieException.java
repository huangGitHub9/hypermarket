package cn.xu.dygl.movie.exception;

public class MovieException extends  Exception {

    public MovieException() {
        super();
    }

    public MovieException(String message) {
        super(message);
    }

    public MovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieException(Throwable cause) {
        super(cause);
    }
}
