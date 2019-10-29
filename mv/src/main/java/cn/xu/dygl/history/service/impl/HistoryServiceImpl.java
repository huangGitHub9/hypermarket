package cn.xu.dygl.history.service.impl;

import cn.xu.dygl.history.dao.HistoryDao;
import cn.xu.dygl.history.entity.History;
import cn.xu.dygl.history.service.HistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "historyService")
public class HistoryServiceImpl implements HistoryService {

    @Resource
    private HistoryDao historyDao;

    @Override
    public void addHistroryRecord(History form){
            /*
        1.判断session中的用户是否存在
            存在：则添加
                如果浏览两次的话删除上次浏览记录 重新插入新的浏览记录
            否则：不添加
         */
        Map<String,Object> params = getHistoryMapParams(form);

         int counts = historyDao.findHByUidAndMId(params);
         if(counts>0){
            //则删除重新添加
             historyDao.delHByUidAndMid(params);
         }
        historyDao.addHistroryRecord(params);
    }

    //删除该用户的历史记录
    public void delHByUidAndMid(Map<String,Object> params){
        historyDao.delHByUidAndMid(params);
    }


    private Map<String,Object> getHistoryMapParams(History form){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("historyId",form.getHistoryId());
        params.put("historyDate",form.getHistoryDate());
        params.put("userId",form.getUser().getUserId());
        params.put("movieId",form.getMovie().getMovieId());
        return params;

    }

    @Transactional
    @Override
    public void clearHByUid(String userId) {
        historyDao.clearHByUid(userId);
    }

    @Override
    public List<History> findAllHByUid(String userId) {
        return historyDao.findAllHByUid(userId);
    }
}
