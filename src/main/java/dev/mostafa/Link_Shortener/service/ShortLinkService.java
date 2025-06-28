package dev.mostafa.Link_Shortener.service;

import dev.mostafa.Link_Shortener.model.ShortLink;
import dev.mostafa.Link_Shortener.Repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShortLinkService {

    @Value("${app.base-url}")
    private String BASE_URL;

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    public ShortLink createShortLink(String originalUrl) {
        ShortLink existing = shortLinkRepository.getByOriginalUrl(originalUrl);
        if (existing != null) return existing;

        String code = UUID.randomUUID().toString().substring(0, 6);
        String shortUrl = BASE_URL + code;

        ShortLink shortLink = new ShortLink();
        shortLink.setOriginalUrl(originalUrl);
        shortLink.setShortLinkCode(code);
        shortLink.setShortLinkUrl(shortUrl);
        shortLinkRepository.save(shortLink);
        return shortLink;
    }

    public List<ShortLink> getShortLinks() {
        return shortLinkRepository.getAll();
    }

    public ShortLink getByShortCode(String code) {
        return shortLinkRepository.getByShortLinkCode(code);
    }

    public ShortLink getById(int id) {
        return shortLinkRepository.getById(id);
    }

    public ShortLink getByOriginalUrl(String url) {
        return shortLinkRepository.getByOriginalUrl(url);
    }

    public boolean deleteShortLinkByCode(String code) {
        return shortLinkRepository.deleteLinkByCode(code);
    }

}
