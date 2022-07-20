package cc.thwiki.shorturl.config.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    @Resource
    private DataSource druidDataSource;

    @PostConstruct
    public void init() throws SQLException, IOException {
        try (Connection connection = druidDataSource.getConnection()) {
            try (Statement stat = connection.createStatement()) {
                executeSql(stat, new ClassPathResource("db/init_table.sql"));
            }
        }
    }

    private void executeSql(Statement stat, ClassPathResource sqlFile) throws IOException, SQLException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(sqlFile.getInputStream()))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        String[] s = sb.toString().split(";\n");
        for (String i : s) {
            if (StringUtils.isNotBlank(i)) {
                stat.addBatch(i);
            }
        }
        stat.executeBatch();
        stat.clearBatch();
    }
}