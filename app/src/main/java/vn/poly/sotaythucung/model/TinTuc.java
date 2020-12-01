package vn.poly.sotaythucung.model;

public class TinTuc {
    private String titleNews,imgHeaderNews, idNews, urlNews;

    public TinTuc() {
    }



    public TinTuc(String titleNews, String idNews, String imgHeaderNews, String urlNews) {
        this.titleNews = titleNews;
        this.idNews = idNews;
        this.urlNews = urlNews;
        this.imgHeaderNews = imgHeaderNews;
    }


    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getIdNews() {
        return idNews;
    }

    public void setIdNews(String idNews) {
        this.idNews = idNews;
    }

    public String getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(String urlNews) {
        this.urlNews = urlNews;
    }

    public String getImgHeaderNews() {
        return imgHeaderNews;
    }

    public void setImgHeaderNews(String imgHeaderNews) {
        this.imgHeaderNews = imgHeaderNews;
    }
}
