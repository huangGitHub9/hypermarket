package cn.xu.dygl.user.dao;

import cn.xu.dygl.user.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface UserDao {
    public void add(User user);

    public void delete(String id);

    public void update(User user);

    public List<User> findAll();

    public void updateUDelState(String userId);

    //一般用户的查询
    public User findObjectById(String id);

    //vip查询
    public User findObjectVipById(String id);

    //分页查询
    public List<User> findAllByPaging(Map<String, Object> params);

    //查找用户通过用户名密码
    public User findUserByNameAndPassword(Map<String, Object> params);

    //查找分页的总记录数
    public Integer getTotalCounts(Map<String, Object> params);

    //查找用户除了该自己
    public int findUserExcludeSelf(Map<String, Object> params);


    //用户收藏电影
    public void collectMovie(Map<String, Object> params);
    public int findCollectMByUIdAndMId(Map<String, Object> params);
    public void delCollectMByUIdAndMId(Map<String, Object> params);

    //添加角色和用户之间的关系
    public void addRoleU(Map<String, Object> params);

    //删除用户和角色之间的关系
    public void delRoleUByUId(String userId);

    //删除该用户的会员的角色 通过 用户id和角色id
    public void delUVIPByURId(Map<String, Object> params);

    //添加用户套餐之间的关系
    public void addUMP(Map<String, Object> params);

    //删除用户套餐之间的关系
    public void delUMPByUserId(String userId);

    //查询用户的消费详情
    public List<Map<String,Object>> findUserConsume(String userId);
}
