package com.zuehlke.movieticketservice.domain;

public class MovieSummary {

    private long id;
    private String title;
    private String poster;

    public MovieSummary(long id, String title, String poster) {
        this.id = id;
        this.title = title;
        this.poster = poster;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieSummary that = (MovieSummary) o;

        if (id != that.id) return false;
        if (!title.equals(that.title)) return false;
        return poster.equals(that.poster);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + poster.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MovieSummary{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }

}
