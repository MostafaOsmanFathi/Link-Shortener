package dev.mostafa.Link_Shortener.model;

public class ShortLink {
    int id;
    String originalUrl;

    String shortLinkCode;
    String shortLinkUrl;

    public ShortLink(int id, String originalUrl, String shortLink, String shortLinkUrl) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortLinkCode = shortLink;
        this.shortLinkUrl = shortLinkUrl;
    }

    public ShortLink() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLinkCode() {
        return shortLinkCode;
    }

    public void setShortLinkCode(String shortLinkCode) {
        this.shortLinkCode = shortLinkCode;
    }

    public String getShortLinkUrl() {
        return shortLinkUrl;
    }

    public void setShortLinkUrl(String shortLinkUrl) {
        this.shortLinkUrl = shortLinkUrl;
    }
}
