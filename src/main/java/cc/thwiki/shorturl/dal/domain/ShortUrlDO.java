package cc.thwiki.shorturl.dal.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.htdong.common.db.domain.SuperDbDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("short_url")
public class ShortUrlDO extends SuperDbDO {
    public static final String DB_FIELD_SHORT_PATH = "short_path";
    public static final String DB_FIELD_REAL_URL = "real_url";
    public static final String DB_FIELD_EXPIRE_TIME = "expire_time";

    private String shortPath;
    private String realUrl;
    private Long expireTime;
}