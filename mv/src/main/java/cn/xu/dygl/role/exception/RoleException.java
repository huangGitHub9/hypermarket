package cn.xu.dygl.role.exception;

public class RoleException extends Exception {

    public RoleException() {
        super();
    }

    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleException(Throwable cause) {
        super(cause);
    }
}
