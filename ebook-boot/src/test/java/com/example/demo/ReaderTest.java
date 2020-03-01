package com.example.demo;

import com.example.demo.epub.Reader;
import com.example.demo.util.StringUtil;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.PackageDocumentReader;
import nl.siegmann.epublib.util.ResourceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author imagic
 * @disc ${}
 */
public class ReaderTest {
    @Test
    public void initTest() {
        //file=3.epub&href=OEBPS/Text/Section0001_split_003.xhtml
        Reader reader = new Reader("D:/imagic/lang/idea/dad/src/main/resources/static/book/1.epub", "OEBPS/Text/Section0001_split_003.xhtml");
         System.out.println(reader.getResource().getInputEncoding());
        System.out.println(reader.getBook().getTitle());
        try {
            System.out.println(new String(reader.getBook().getNcxResource().getData()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getResourceDataTest() {
        Reader reader = new Reader("D:/imagic/lang/idea/dad/src/main/resources/static/book/1.epub", "OEBPS/Text/Section0001_split_000.xhtml#epubw_com_id_2");
        //String string = new String(reader.getResourceData("epubw_com_id_2"));
        //string = StringUtil.replaceHtmlTag(string, "img", "src", "src=\"http://junlenet.com/", "\"");
        try {
            Document document = ResourceUtil.getAsDocument(reader.getResource());
            NodeList nodeList = document.getElementsByTagName("body");
            System.out.println(nodeList.item(0).getChildNodes().item(0).getChildNodes().getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
