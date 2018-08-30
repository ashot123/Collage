package am.ak.acoolage.collage.dao.exception;

import java.sql.SQLException;

public class DbSystemException extends Exception {
    public DbSystemException(String message) {
        super(message);
    }

    public DbSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
