package org.FaneFonseka.PageChangeChecker;

import java.io.IOException;

/**
 * Created by Fane on 10/04/2017.
 */
public interface ContentFetcher {

    String getHTMLBody() throws IOException;

}
