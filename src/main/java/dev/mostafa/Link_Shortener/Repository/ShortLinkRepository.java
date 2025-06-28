package dev.mostafa.Link_Shortener.Repository;

import dev.mostafa.Link_Shortener.model.ShortLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ShortLinkRepository {

    private static final Logger logger = LoggerFactory.getLogger(ShortLinkRepository.class);

    private final DataSource dataSource;

    @Autowired
    ShortLinkRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        runSchemaSql();
    }

    private void runSchemaSql() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = new BufferedReader(new InputStreamReader(
                    new ClassPathResource("database-schema.sql").getInputStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));

            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    try (Statement stmt = connection.createStatement()) {
                        stmt.execute(statement.trim());
                    }
                }
            }
            logger.info("data base schema created successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }


    private ShortLink map(ResultSet rs) throws SQLException {
        return new ShortLink(
                rs.getInt("id"),
                rs.getString("original_url"),
                rs.getString("short_link_code"),
                rs.getString("short_link_url")
        );
    }

    public void save(ShortLink link) {
        String sql = "INSERT INTO short_links (original_url, short_link_code, short_link_url) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, link.getOriginalUrl());
            stmt.setString(2, link.getShortLinkCode());
            stmt.setString(3, link.getShortLinkUrl());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ShortLink> getAll() {
        List<ShortLink> result = new ArrayList<>();
        String sql = "SELECT * FROM short_links";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ShortLink getById(int id) {
        String sql = "SELECT * FROM short_links WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ShortLink getByShortLinkCode(String code) {
        String sql = "SELECT * FROM short_links WHERE short_link_code = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ShortLink getByOriginalUrl(String url) {
        String sql = "SELECT * FROM short_links WHERE original_url = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, url);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteLinkByCode(String code) {
        String sql = "DELETE FROM short_links WHERE short_link_code = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
}
