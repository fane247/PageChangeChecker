package org.FaneFonseka.PageChangeChecker;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by Fane on 10/04/2017.
 */
public class PageChangeChecker {


    private final String originalHTMLBodyTrimmed;
    private final ContentFetcher contentFetcher;

    public PageChangeChecker(ContentFetcher contentFetcher) throws IOException {

        this.contentFetcher = contentFetcher;
        String originalWebPageBody = contentFetcher.getHTMLBody();
        originalHTMLBodyTrimmed = removeWhiteSpace(originalWebPageBody);
    }

    private String removeWhiteSpace(String webPageBody) {
        return webPageBody.replaceAll("\\s", "");
    }

    public String getRefreshedContent() throws IOException {

        String refreshedHTMLBody = contentFetcher.getHTMLBody();
        refreshedHTMLBody = removeWhiteSpace(refreshedHTMLBody);

        return refreshedHTMLBody;
    }

    public boolean webPageHasChanged() throws IOException {

        String refreshedHTMLBody = getRefreshedContent();

        return !originalHTMLBodyTrimmed.equals(refreshedHTMLBody);
    }



}
