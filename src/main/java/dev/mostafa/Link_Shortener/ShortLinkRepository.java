package dev.mostafa.Link_Shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShortLinkRepository {

    @Autowired
    private DataSource dataSource;

    ShortLinkRepository() {
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
}
