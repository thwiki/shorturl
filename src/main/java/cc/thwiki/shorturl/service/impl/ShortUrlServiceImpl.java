package cc.thwiki.shorturl.service.impl;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cc.thwiki.shorturl.dal.domain.ShortUrlDO;
import cc.thwiki.shorturl.dal.mapper.ShortUrlMapper;
import cc.thwiki.shorturl.service.ShortUrlService;

@Service("shortUrlService")
public class ShortUrlServiceImpl implements ShortUrlService {
    private static final char[] BASE62 =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Resource
    private ShortUrlMapper shortUrlMapper;

    @Override
    public String getUrl(String shortPath) {
        QueryWrapper<ShortUrlDO> query = new QueryWrapper<>();
        query.eq(ShortUrlDO.DB_FIELD_SHORT_PATH, shortPath);
        ShortUrlDO domain = shortUrlMapper.selectOne(query);
        return domain == null ? null : domain.getRealUrl();
    }

    @Override
    public String addShortUrl(String url) {
        ShortUrlDO domain = new ShortUrlDO();
        domain.setRealUrl(url);
        domain.setShortPath(randomShortPath());
        shortUrlMapper.insert(domain);
        return domain.getShortPath();
    }

    private static String randomShortPath() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int len = random.nextInt(3) + 5;
        StringBuilder sb = new StringBuilder();
        while (len > 0) {
            --len;
            sb.append(BASE62[random.nextInt(BASE62.length)]);
        }
        return sb.toString();
    }
}