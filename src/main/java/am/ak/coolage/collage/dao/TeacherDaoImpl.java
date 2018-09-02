package am.ak.coolage.collage.dao;

import am.ak.coolage.collage.dao.connection.PooledConnection;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TeacherDaoImpl {

    public List<Teacher> getPageTeachers(int offset, int noOfRecords) throws DaoSystemException {
        ArrayList<Teacher> teachers;

        try {
            teachers = new ArrayList<>();
            //Connection connection = DataSourceSelector.getDataSource().getConnection();
            Connection connection = PooledConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id, teacher_name " +
                            "from collage.teachers " +
                            "order by id " +
                            "limit ?, ?");

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);

            ResultSet resultSet = preparedStatement.executeQuery();

            //String query = "select SQL_CALC_FOUND_ROWS * from employee limit " + offset + ", " + noOfRecords;

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getNString("teacher_name"));
                //student.setDepartment(resultSet.getNString("department_name"));

                teachers.add(teacher);
            }

            return teachers;
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
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM collage.teachers");
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




