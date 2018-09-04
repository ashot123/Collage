package am.ak.coolage.collage.dao;

import am.ak.coolage.collage.dao.connection.PooledConnection;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.Room;
import am.ak.coolage.collage.entities.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoomDaoImpl {

    public List<Room> getPageRooms(int offset, int noOfRecords) throws DaoSystemException {
        ArrayList<Room> rooms;

        try {
            rooms = new ArrayList<>();
            Connection connection = PooledConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id, room_name " +
                            "from collage.rooms " +
                            "order by id " +
                            "limit ?, ?");

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setRoomName(resultSet.getString("room_name"));
                rooms.add(room);
            }

            return rooms;
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
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM collage.rooms");
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




