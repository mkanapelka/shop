package com.max.shop.util;

import lombok.experimental.UtilityClass;
import javax.servlet.http.HttpServletResponse;
import java.net.URLConnection;

@UtilityClass
public class ContentTypeUtil {

    public void setContentType(HttpServletResponse response, String fileName) {
        response.addHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
        response.setContentType(URLConnection.guessContentTypeFromName(fileName));
    }
}
