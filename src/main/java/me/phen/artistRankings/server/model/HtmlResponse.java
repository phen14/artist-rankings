package me.phen.artistRankings.server.model;

/**
 * Wrapper for an HTML Response
 *
 * @author Patrick W. Henstebeck
 * @since 2019-07-05 (Fr)
 */
public class HtmlResponse {

    private String html;

    /**
     * Initialize the wrapper.
     *
     * @param html HTML string.
     */
    public HtmlResponse(String html) {
        this.html = html;
    }

    /**
     * @return Returns the html.
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html The html to set.
     */
    public void setHtml(String html) {
        this.html = html;
    }
}
