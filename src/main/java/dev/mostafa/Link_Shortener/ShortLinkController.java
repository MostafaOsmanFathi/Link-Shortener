package dev.mostafa.Link_Shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("links")
public class ShortLinkController {
    @Autowired
    ShortLinkService shortLinkService;

    @GetMapping({"", "/"})
    public List<ShortLink> index() {
        return shortLinkService.getShortLinks();
    }

    @PostMapping("/")
    public ShortLink createShortLink(@RequestBody String originalUrl) {
        return shortLinkService.createShortLink(originalUrl);
    }

    @GetMapping("/{id}")
    public ShortLink getById(@PathVariable int id) {
        return shortLinkService.getById(id);
    }

    @GetMapping("/resolve/{code}")
    public String resolveLinkByCode(@PathVariable String code) {
        return shortLinkService.getByShortCode(code).getOriginalUrl();
    }

    @DeleteMapping("/{code}")
    public boolean deleteShortLink(@PathVariable String code) {
        return shortLinkService.deleteShortLinkByCode(code);
    }
}
