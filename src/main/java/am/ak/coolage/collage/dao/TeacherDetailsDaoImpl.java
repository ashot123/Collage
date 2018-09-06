package am.ak.coolage.collage.dao;

import am.ak.coolage.collage.dao.connection.PooledConnection;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.CommonCourseDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDetailsDaoImpl {
    public List<CommonCourseDetails> getPageCommonCourseDetails(int offset, int noOfRecords, int teacherIdInt)
            throws DaoSystemException {

        ArrayList<CommonCourseDetails> commonCourseDetails;
        try {
            commonCourseDetails = new ArrayList<>();
            //Connection connection = DataSourceSelector.getDataSource().getConnection();
            Connection connection = PooledConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT teacher_name, course_name, room_name " +
                            "FROM courses_details cd " +
                            "INNER JOIN teachers t ON cd.teachers_id=t.id " +
                            "INNER JOIN courses c ON cd.courses_id=c.id " +
                            "INNER JOIN rooms r ON cd.rooms_id=r.id " +
                            "WHERE t.id=? " +
                            "LIMIT ?, ?");

            preparedStatement.setInt(1, teacherIdInt);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, noOfRecords);

            ResultSet resultSet = preparedStatement.executeQuery();
            //
            //String query = "select SQL_CALC_FOUND_ROWS * from employee limit " + offset + ", " + noOfRecords;

            while (resultSet.next()) {
                CommonCourseDetails ccDetails = new CommonCourseDetails();
                ccDetails.setTeacherName(resultSet.getString("teacher_name"));
                ccDetails.setCourseName(resultSet.getString("course_name"));
                ccDetails.setRoomName(resultSet.getString("room_name"));

                commonCourseDetails.add(ccDetails);
            }

            return commonCourseDetails;
        } catch (SQLException e) {
            throw new DaoSystemException("Exception"); // TODO
        }
    }

    public int getNumberOfRecord(int teacherIdInt) throws DaoSystemException {
        Connection connection;
        int rowCount;
        try {
            connection = PooledConnection.getConnection();

            //Statement statement = connection.createStatement();
            // ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM collage.courses_details");
            PreparedStatement preparedStatement = connection.prepareStatement(("SELECT COUNT(*) " +
                    "FROM (SELECT teacher_name, course_name, room_name " +
                    "FROM courses_details cd " +
                    "INNER JOIN teachers t ON cd.teachers_id=t.id " +
                    "INNER JOIN courses c ON cd.courses_id=c.id " +
                    "INNER JOIN rooms r ON cd.rooms_id=r.id " +
                    "WHERE t.id=?) as T;"));

            preparedStatement.setInt(1, teacherIdInt);
            ResultSet resultSet = preparedStatement.executeQuery();

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
