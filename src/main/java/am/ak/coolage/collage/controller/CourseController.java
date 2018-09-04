package am.ak.coolage.collage.controller;


import am.ak.coolage.collage.dao.CourseDaoImpl;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.Course;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            CourseDaoImpl courseDao = new CourseDaoImpl();
            int numberOfRecord = courseDao.getNumberOfRecord();

            int rows = CommonUtil.getRows(request);
            int pageId = CommonUtil.getPageId(request, rows, numberOfRecord);
            int numberOfPages = CommonUtil.getNumberOfPages(rows, numberOfRecord);

            List<Course> allCourses = courseDao.getPageCourses((pageId - 1) * rows, rows);

            request.setAttribute("page", pageId);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("courses", allCourses);

        } catch (DaoSystemException e) {
            e.printStackTrace(); // TODO
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/courses.jsp");
        rd.forward(request, response);
    }
}