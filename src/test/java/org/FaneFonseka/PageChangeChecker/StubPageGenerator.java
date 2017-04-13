package org.FaneFonseka.PageChangeChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by Fane on 10/04/2017.
 */
public class StubPageGenerator implements ContentFetcher{

    private Stack<String> stubPageRefreshes;
    private final String fileAsString;

    StubPageGenerator() throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("testHTMLPage.html");
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String line = in.readLine();
        StringBuilder sb = new StringBuilder();
        while(line != null){
            sb.append(line).append("\n");
            line = in.readLine();
        }

        fileAsString = sb.toString();
        String changedWebPage = "webpage has changed";

        stubPageRefreshes = new Stack<String>();

        stubPageRefreshes.add(changedWebPage);
        stubPageRefreshes.add(fileAsString);
        stubPageRefreshes.add(fileAsString);

    }


    public String getHTMLBody() throws IOException {
        String refreshedContent = stubPageRefreshes.pop();

        refreshedContent = refreshedContent.replaceAll("\\s","");
        return refreshedContent;
    }
}
