package cn.xu.dygl.user.service;

import cn.xu.dygl.user.entity.User;
import cn.xu.dygl.user.exception.UserException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface UserService {

    public void add(User user) throws UserException;
    public void addUserAndRU(User user, String[] roleIds) throws UserException;

    public void delete(String id);

    public void updateUDelState(String userId);

    public List<User> findAll();

    public User findObjectById(String id);

    public void update(User user)throws UserException;

    public void updateUserAndRU(User user, String[] roleIds)throws UserException;

    //用户登录
    public User login(User user)throws UserException;

    //用户分页查询
    public List<User> findAllByPaging(Integer pCur, User form);

    //得到该分页查询的总记录数
    public Integer getTotalCounts(User form);

    public void collectMovie(Map<String, Object> params) throws UserException;

    public void delCollectMByUIdAndMId(Map<String, Object> params);

    public int findCollectMByUIdAndMId(Map<String, Object> params);

    //添加用户套餐之间的关系
    public void addUMP(Map<String, Object> params);

    //删除用户套餐之间的关系
    public void delUMPByUserId(String userId);

    public List<Map<String,Object>> findUserConsume(String userId);




}
