package am.ak.acoolage.collage.dao.exception;

import java.sql.SQLException;

public class DaoSystemException extends Exception {
    public DaoSystemException(String message, SQLException cause) {
        super(message, cause);
    }

    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(Exception e) {
        super(e);
    }
}
