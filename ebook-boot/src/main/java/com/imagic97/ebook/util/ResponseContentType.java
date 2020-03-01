package com.imagic97.ebook.util;


import java.util.HashMap;
import java.util.Map;


public class  ResponseContentType {

    private static ResponseContentType responseContentType = new ResponseContentType();
    private static Map<String,String> typeMap = new HashMap<>();
    static {
        typeMap.put(".xhtml","application/xhtml+xml");
        typeMap.put(".png","image/png");
        typeMap.put(".epub","application/epub+zip");
        typeMap.put(".ncx","application/x-dtbncx+xml");
        typeMap.put(".js","text/javascript");
        typeMap.put(".css","text/css");
        typeMap.put(".jpg","image/jpeg");
        typeMap.put(".gif","image/gif");
        typeMap.put(".svg","image/svg+xml");
        typeMap.put(".ttf","application/x-truetype-font");
        typeMap.put(".otf","application/vnd.ms-opentype");
        typeMap.put(".woff","application/font-woff");
        typeMap.put(".mp3","audio/mpeg");
        typeMap.put(".ogg","audio/ogg");
        typeMap.put(".mp4","video/mp4");
        typeMap.put(".smil","application/smil+xml");
        typeMap.put(".xpgt","application/adobe-page-template+xml");
        typeMap.put(".pls","application/pls+xml");
    }

    public String matchType(String string,String rules){
        return typeMap.get(string.substring(string.lastIndexOf(rules)));
    }

    public static ResponseContentType getInstance(){
        return responseContentType;
    }

}
