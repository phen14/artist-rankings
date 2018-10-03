// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * last.fm response metadata.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-30 Sa
 */
public class LastFmResponseAttributes {

    private int page;
    
    private int perPage;

    private int totalPages;

    @JsonProperty("total")
    private int totalRecords;

    private String user;

    
    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    // =================================================================================================================
    // -----| Object |-----
    // ====================
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LastFmResponseAttributes that = (LastFmResponseAttributes) o;

        if (page != that.page) {
            return false;
        }
        if (perPage != that.perPage) {
            return false;
        }
        if (totalPages != that.totalPages) {
            return false;
        }
        if (totalRecords != that.totalRecords) {
            return false;
        }
        return user != null ? user.equals(that.user) : that.user == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + perPage;
        result = 31 * result + totalPages;
        result = 31 * result + totalRecords;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmResponseAttributes{");
        sb.append("page=").append(page);
        sb.append(", perPage=").append(perPage);
        sb.append(", totalPages=").append(totalPages);
        sb.append(", totalRecords=").append(totalRecords);
        sb.append(", user='").append(user).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
