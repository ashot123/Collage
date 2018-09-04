package am.ak.coolage.collage.dao;

import am.ak.coolage.collage.dao.connection.PooledConnection;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.Course;
import am.ak.coolage.collage.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl {

    public List<Course> getPageCourses(int offset, int noOfRecords) throws DaoSystemException {
        ArrayList<Course> courses;

        try {
            courses = new ArrayList<>();
            Connection connection = PooledConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id, course_name " +
                            "from collage.courses " +
                            "order by id " +
                            "limit ?, ?");

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);

            ResultSet resultSet = preparedStatement.executeQuery();

            //String query = "select SQL_CALC_FOUND_ROWS * from employee limit " + offset + ", " + noOfRecords;

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setCourseName(resultSet.getString("course_name"));

                courses.add(course);
            }

            return courses;
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
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM collage.courses");
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
