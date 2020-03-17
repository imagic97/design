package com.imagic97.ebook;

import com.imagic97.ebook.epub.Reader;
import nl.siegmann.epublib.domain.SpineReference;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        Reader reader =new Reader("7.epub");
        List<SpineReference> resourceList = reader.getBook().getSpine().getSpineReferences();
        resourceList.size();
    }

}
