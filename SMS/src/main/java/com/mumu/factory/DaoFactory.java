package com.mumu.factory;

import com.mumu.dao.IMemberDao;
import com.mumu.dao.impl.IMemberDaoImpl;

import java.sql.Connection;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 19:46
 */
public class DaoFactory {
    public static IMemberDao getIMemberDaoInstance(Connection conn) {
        return new IMemberDaoImpl(conn);
    }
}
