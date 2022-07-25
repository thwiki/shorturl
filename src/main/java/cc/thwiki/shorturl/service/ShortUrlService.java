package cc.thwiki.shorturl.service;

public interface ShortUrlService {
    String getUrl(String shortPath);

    String addShortUrl(String url, Long expireTime);
}