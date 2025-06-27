package dev.mostafa.Link_Shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("links")
public class ShortLinkController {

    private static final Logger logger = LoggerFactory.getLogger(ShortLinkController.class);

    @Autowired
    ShortLinkService shortLinkService;

    @GetMapping({"", "/"})
    public List<ShortLink> index() {
        logger.info("Fetching all short links");
        return shortLinkService.getShortLinks();
    }

    @PostMapping("/")
    public ShortLink createShortLink(@RequestBody String originalUrl) {
        String cleanUrl = originalUrl.substring(1, originalUrl.length() - 1);
        logger.info("Creating short link for: {}", cleanUrl);
        return shortLinkService.createShortLink(cleanUrl);
    }

    @GetMapping("/{id}")
    public ShortLink getById(@PathVariable int id) {
        logger.info("Fetching short link by ID: {}", id);
        return shortLinkService.getById(id);
    }

    @GetMapping("/resolve/{code}")
    public RedirectView resolveLinkByCode(@PathVariable String code) {
        logger.info("Resolving short link with code: {}", code);
        String originalUrl = shortLinkService.getByShortCode(code).getOriginalUrl();
        logger.info("Redirecting to: {}", originalUrl);
        return new RedirectView(originalUrl);
    }

    @DeleteMapping("/{code}")
    public boolean deleteShortLink(@PathVariable String code) {
        logger.info("Deleting short link with code: {}", code);
        return shortLinkService.deleteShortLinkByCode(code);
    }
}
