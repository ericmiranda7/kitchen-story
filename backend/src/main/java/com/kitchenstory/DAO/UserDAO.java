package com.kitchenstory.DAO;

import com.kitchenstory.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends AbstractDAO<User> {
    protected UserDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getOneById(int id) {
        return null;
    }

    @Override
    public User add(User userDetails) {
        return null;
    }

    public User getUserByUsername(String username) throws Exception {
        User res = this.jdbcTemplate.queryForObject("SELECT * FROM user WHERE username = ?", (rs, rowNum) -> {
            User user = new User();
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setAdmin(rs.getInt(4) == 1);
            return user;
        }, username);
        return res;
    }

    public void update(String username, String pass) {
        this.jdbcTemplate.update("UPDATE user SET password = ? WHERE username = ?", pass, username);
    }


}
