package service.exception;

import dao.exception.dao.DAOException;

public class LoginExistException extends DAOException {
    public LoginExistException() {
    }

    public LoginExistException(String message) {
        super(message);
    }

    public LoginExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginExistException(Throwable cause) {
        super(cause);
    }
}
