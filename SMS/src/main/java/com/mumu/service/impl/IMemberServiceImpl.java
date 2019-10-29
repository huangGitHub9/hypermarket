package com.mumu.service.impl;

import com.mumu.dbc.DatabaseConnection;
import com.mumu.factory.DaoFactory;
import com.mumu.service.IMemberService;
import com.mumu.vo.Member;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 19:41
 */
public class IMemberServiceImpl implements IMemberService {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(Member vo) throws Exception {
        try {
            return DaoFactory.getIMemberDaoInstance(this.dbc.getConn()).doCreate(vo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
