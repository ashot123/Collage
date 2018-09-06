package am.ak.coolage.collage.controller;

import javax.servlet.http.HttpServletRequest;

class CommonUtil {

    static int getRows(HttpServletRequest request) {
        String rowsInPageStr = request.getSession().getServletContext().getInitParameter("rowsInPage");
        return Integer.parseInt(rowsInPageStr);
    }

    static int getNumberOfPages(int rows, int numberOfRecord) {
        int numberOfPages;
        if (numberOfRecord == 0) {
            numberOfPages = 0;
        } else {
            numberOfPages = (numberOfRecord - 1) / rows + 1;
        }
        return numberOfPages;
    }

    static int getPageId(HttpServletRequest request, int rows, int numberOfRecord) {
        String pageStr = request.getParameter("page");

        if (pageStr == null) {
            pageStr = "1";
        }

        int pageId = Integer.valueOf(pageStr);

        // real page number again request pageId
        double realPageNumber = Math.ceil(numberOfRecord / (rows * 1.0));
        if (pageId > realPageNumber || pageId < 1) {
            pageId = 1;
        }
        return pageId;
    }

    static int getSelectedPageId(HttpServletRequest request, int rows, int numberOfRecord) {
        String pageStr = request.getParameter("teacherId");

        if (pageStr == null) {
            pageStr = "1";
        }

        int pageId = Integer.valueOf(pageStr);

        // real page number again request pageId
        double realPageNumber = Math.ceil(numberOfRecord / (rows * 1.0));
        if (pageId > realPageNumber || pageId < 1) {
            pageId = 1;
        }
        return pageId;
    }

}
