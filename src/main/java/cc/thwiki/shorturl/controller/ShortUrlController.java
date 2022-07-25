package cc.thwiki.shorturl.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.thwiki.shorturl.service.ShortUrlService;

@RestController
public class ShortUrlController {

    @Resource
    private ShortUrlService shortUrlService;

    @GetMapping("/{path}")
    public ResponseEntity<Void> shortUrl(@PathVariable("path") String path, HttpServletResponse response)
        throws IOException {
        String realUrl = shortUrlService.getUrl(path);
        if (realUrl != null) {
            response.sendRedirect(realUrl);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(@RequestParam("url") String url,
        @RequestParam(name = "expireTime", required = false) Long expireTime) {
        return ResponseEntity.ok().body(shortUrlService.addShortUrl(url, expireTime));
    }
}
