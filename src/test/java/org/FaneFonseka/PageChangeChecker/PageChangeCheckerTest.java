package org.FaneFonseka.PageChangeChecker;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Created by Fane on 10/04/2017.
 */
public class PageChangeCheckerTest {


    private String webPageBodyAsString;

    @Before
    public void setup() throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("testHTMLPage.html");
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String line = in.readLine();
        StringBuilder sb = new StringBuilder();
        while(line != null){
            sb.append(line).append("\n");
            line = in.readLine();
        }

        webPageBodyAsString = sb.toString();

        webPageBodyAsString = webPageBodyAsString.replaceAll("\\s","");

    }



    @Test
    public void whenGivenAURLPageContentIsReturnedWithoutSpaces() throws IOException {

//        String url = "https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html";
        ContentFetcher stubPageGenerator = new StubPageGenerator();

        PageChangeChecker pageChangeChecker = new PageChangeChecker(stubPageGenerator);

        String content = pageChangeChecker.getRefreshedContent();

        assertEquals(webPageBodyAsString, content);

    }

    @Test
    public void whenComparisonOfRefreshedContentIsDifferentToOriginalURLContentReturnsTrue() throws IOException {

        ContentFetcher stubPageGenerator = new StubPageGenerator();

        PageChangeChecker pageChangeChecker = new PageChangeChecker(stubPageGenerator);


        boolean firstRefresh = pageChangeChecker.webPageHasChanged();
        boolean secondRefresh = pageChangeChecker.webPageHasChanged();

        assertEquals(false,firstRefresh);
        assertEquals(true,secondRefresh);
    }

}