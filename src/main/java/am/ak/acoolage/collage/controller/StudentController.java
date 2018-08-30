package am.ak.acoolage.collage.controller;

import am.ak.acoolage.collage.controller.CommonUtil;
import am.ak.acoolage.collage.dao.StudentDaoImpl;
import am.ak.acoolage.collage.dao.exception.DaoSystemException;
import am.ak.acoolage.collage.dao.exception.DbSystemException;
import am.ak.acoolage.collage.entities.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;


@WebServlet("/students")
public class StudentController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageId = CommonUtil.getPageIdAndParamToAttribute(request);

        // Size of rows now in web.xml
        int rows = CommonUtil.getRows(request);


        StudentDaoImpl studentDao = new StudentDaoImpl();
        try {
            List<Student> allStudents = studentDao.getPageStudents((pageId - 1) * rows, rows);
            request.setAttribute("students", allStudents);

            int numberOfRecord = studentDao.getNumberOfRecord();

            int numberOfPages;
            if (numberOfRecord == 0) {
                numberOfPages = 0;
            } else {
                numberOfPages = (numberOfRecord - 1) / rows + 1;
            }
            request.setAttribute("numberOfPages", numberOfPages);

        } catch (DaoSystemException e) {
            e.printStackTrace(); // TODO
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/students.jsp");
        rd.forward(request, response);
    }
}
