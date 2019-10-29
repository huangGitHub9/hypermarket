package cn.xu.dygl.memPackage.exception;

public class MemPackageException extends Exception {

    public MemPackageException() {
        super();
    }

    public MemPackageException(String message) {
        super(message);
    }

    public MemPackageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemPackageException(Throwable cause) {
        super(cause);
    }
}
