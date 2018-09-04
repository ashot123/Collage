package am.ak.coolage.collage.controller;

import am.ak.coolage.collage.dao.RoomDaoImpl;
import am.ak.coolage.collage.dao.TeacherDaoImpl;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.Room;
import am.ak.coolage.collage.entities.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/rooms")
public class RoomController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            RoomDaoImpl roomDao = new RoomDaoImpl();

            int numberOfRecord = roomDao.getNumberOfRecord();

            int rows = CommonUtil.getRows(request);
            int pageId = CommonUtil.getPageId(request, rows, numberOfRecord);
            int numberOfPages = CommonUtil.getNumberOfPages(rows, numberOfRecord);

            List<Room> allRooms = roomDao.getPageRooms((pageId - 1) * rows, rows);

            request.setAttribute("page", pageId);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("rooms", allRooms);

        } catch (DaoSystemException e) {
            e.printStackTrace(); // TODO
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/rooms.jsp");
        rd.forward(request, response);
    }
}
