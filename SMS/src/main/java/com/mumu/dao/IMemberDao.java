package com.mumu.dao;

import com.mumu.vo.Member;

import java.sql.SQLException;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 19:19
 */
public interface IMemberDao {
    /*
    增加用户
     */
    public boolean doCreate(Member vo) throws SQLException;
}
