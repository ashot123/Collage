package am.ak.acoolage.collage.dao.connection;

import am.ak.acoolage.collage.dao.exception.DbSystemException;
import com.mchange.v2.c3p0.DataSources;
//import com.mchange.v2.c3p0.DataSources;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Ashot Karakhanyan on 16-12-2013
 */
public class DataSourceSelector {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/university?useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "r00t";

    private DataSourceSelector() {
    }

    public static DataSource getDataSource() throws DbSystemException {
        try {
            return getUnPoolDataSource();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DbSystemException("Can't create data source", e);
        }
    }

    private static DataSource getUnPoolDataSource() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DataSources.unpooledDataSource(JDBC_URL, LOGIN, PASSWORD);
    }

}