package am.ak.acoolage.collage.controller;

import javax.servlet.http.HttpServletRequest;

class CommonUtil {

    static int getRows(HttpServletRequest request) {
        String rowsInPageStr = request.getSession().getServletContext().getInitParameter("rowsInPage");
        return Integer.parseInt(rowsInPageStr);
    }

    // get Param variable and move to attribute. Also return that value as integer
    static int getPageIdAndParamToAttribute(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        if (pageStr == null) {
            pageStr = "1";
        }
        request.setAttribute("page", pageStr);
        return Integer.valueOf(pageStr);
    }
}
