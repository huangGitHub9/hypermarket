package cn.xu.dygl.history.dao;

import cn.xu.dygl.history.entity.History;

import java.util.List;
import java.util.Map;

public interface HistoryDao {

    //添加历史记录
    public void addHistroryRecord(Map<String, Object> params);

    //查找该历史记录
    public int findHByUidAndMId(Map<String, Object> params);

    //删除该用户的历史记录
    public void delHByUidAndMid(Map<String, Object> params);


    public List<History> findAllHByUid(String userId);

    public void clearHByUid(String userId);







}
