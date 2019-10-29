package cn.xu.dygl.history.service;

import cn.xu.dygl.history.entity.History;

import java.util.List;
import java.util.Map;

public interface HistoryService {

    //添加历史记录
    public void addHistroryRecord(History form);

    public List<History> findAllHByUid(String userId);
    //删除该用户的历史记录
    public void delHByUidAndMid(Map<String, Object> params);

    public void clearHByUid(String userId);



}
