package dev.mostafa.Link_Shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
        return shortLinkService.createShortLink(originalUrl.substring(1, originalUrl.length() - 1));
    }

    @GetMapping("/{id}")
    public ShortLink getById(@PathVariable int id) {
        return shortLinkService.getById(id);
    }

    @GetMapping("/resolve/{code}")
    public RedirectView resolveLinkByCode(@PathVariable String code) {
        String OriginalUrl = shortLinkService.getByShortCode(code).getOriginalUrl();
        System.out.println(OriginalUrl);
        return new RedirectView(OriginalUrl);
    }

    @DeleteMapping("/{code}")
    public boolean deleteShortLink(@PathVariable String code) {
        return shortLinkService.deleteShortLinkByCode(code);
    }
}
