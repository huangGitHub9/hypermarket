package com.mumu.dao.impl;

import com.mumu.dao.IMemberDao;
import com.mumu.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 19:22
 */
public class IMemberDaoImpl implements IMemberDao {
    public Connection conn;
    public PreparedStatement pstmt;

    public IMemberDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Member vo) throws SQLException {
        String sql = "INSERT INTO member(phone,password)VALUES(?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, vo.getPhone());
        this.pstmt.setString(2, vo.getPassword());
        return this.pstmt.executeUpdate() > 0;
    }
}
