package am.ak.acoolage.collage.dao;

import am.ak.acoolage.collage.dao.connection.PooledConnection;
import am.ak.acoolage.collage.dao.exception.DaoSystemException;
import am.ak.acoolage.collage.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl {

    public List<Student> getPageStudents(int offset, int noOfRecords) throws DaoSystemException {
        ArrayList<Student> students;

        try {
            students = new ArrayList<>();
            //Connection connection = DataSourceSelector.getDataSource().getConnection();
            Connection connection = PooledConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id, first_name, last_name " +
                            "from collage.students " +
                            "order by id " +
                            "limit ?, ?");

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);

            ResultSet resultSet = preparedStatement.executeQuery();

            //String query = "select SQL_CALC_FOUND_ROWS * from employee limit " + offset + ", " + noOfRecords;

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getNString("first_name"));
                student.setLastName(resultSet.getNString("last_name"));
                //student.setDepartment(resultSet.getNString("department_name"));

                students.add(student);
            }

            return students;
        } catch (SQLException e) {
            throw new DaoSystemException("Exception"); // TODO
        }

    }

    public int getNumberOfRecord() throws DaoSystemException {
        Connection connection;
        int rowCount;
        try {
            connection = PooledConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM collage.students");
            //Connection connection = DataSourceSelector.getDataSource().getConnection();
            // Get the number of rows from the result set
            resultSet.next();
            rowCount = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new DaoSystemException(e); // TODO
        }

        return rowCount;
    }
}




