package am.ak.coolage.collage.controller;

import am.ak.coolage.collage.dao.StudentDaoImpl;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/students")
public class StudentController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            StudentDaoImpl studentDao = new StudentDaoImpl();
            int numberOfRecord = studentDao.getNumberOfRecord();

            int rows = CommonUtil.getRows(request);
            int pageId = CommonUtil.getPageId(request, rows, numberOfRecord);
            int numberOfPages = CommonUtil.getNumberOfPages(rows, numberOfRecord);

            List<Student> allStudents = studentDao.getPageStudents((pageId - 1) * rows, rows);

            request.setAttribute("page", pageId);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("students", allStudents);

        } catch (DaoSystemException e) {
            e.printStackTrace(); // TODO
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/students.jsp");
        rd.forward(request, response);
    }
}
