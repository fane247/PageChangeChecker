package org.FaneFonseka.PageChangeChecker;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by Fane on 12/04/2017.
 */
public class ContentFetcherImp implements ContentFetcher {


    private final String url;

    ContentFetcherImp(String url){

        this.url = url;
    }

    public String getHTMLBody() throws IOException {

        return Jsoup.connect(url).get().html();

    }

}
