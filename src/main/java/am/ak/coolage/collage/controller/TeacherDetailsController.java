package am.ak.coolage.collage.controller;

import am.ak.coolage.collage.dao.TeacherDetailsDaoImpl;
import am.ak.coolage.collage.dao.exception.DaoSystemException;
import am.ak.coolage.collage.entities.CommonCourseDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacherDetails")
public class TeacherDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String teacherId = req.getParameter("teacherId");
            if(teacherId == null) {
                teacherId = "1";
            }
            int teacherIdInt = Integer.parseInt(teacherId);
            TeacherDetailsDaoImpl teacherDetailsDao = new TeacherDetailsDaoImpl();
            int numberOfRecord = teacherDetailsDao.getNumberOfRecord(teacherIdInt);

            int rows = CommonUtil.getRows(req);
            int pageId = CommonUtil.getPageId(req, rows, numberOfRecord);
            //int selectedPageId = CommonUtil.getSelectedPageId(req, rows, numberOfRecord);
            int numberOfPages = CommonUtil.getNumberOfPages(rows, numberOfRecord);

            List<CommonCourseDetails> allCommonCourseDetails =
                    teacherDetailsDao.getPageCommonCourseDetails((pageId - 1) * rows, rows, teacherIdInt);
            req.setAttribute("page", pageId);
            req.setAttribute("numberOfPages", numberOfPages);
            req.setAttribute("teacherDetails", allCommonCourseDetails);
        } catch (DaoSystemException e) {
            e.printStackTrace(); // TODO
        }

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/teacherDetails.jsp");
        rd.forward(req, resp);
    }
}
