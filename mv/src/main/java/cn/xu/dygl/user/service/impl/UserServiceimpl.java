package cn.xu.dygl.user.service.impl;

import cn.xu.core.utils.PageResult;
import cn.xu.dygl.user.dao.UserDao;
import cn.xu.dygl.user.entity.User;
import cn.xu.dygl.user.exception.UserException;
import cn.xu.dygl.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceimpl implements UserService {

    @Resource
    private UserDao userDao;

    @Transactional
    @Override
    public void addUserAndRU(User user, String[] roleIds) throws UserException {
        //用户名和密码不能重复
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        params.put("userName",user.getUserName());
        params.put("userPassword",user.getUserPassword());
        User _user = userDao.findUserByNameAndPassword(params);
        if(_user !=null){
            throw new UserException("对不起该用户已注册!");
        }
        //套餐的添加

        userDao.add(user);
        //添加用户和角色之间的关系
        addRoleIdAndUserId(user.getUserId(),roleIds);
    }

    private void addUserMemPackage(){

    }

    private void addRoleIdAndUserId(String userId,String[] roleIds){
        Map<String,Object> params = new HashMap<String, Object>();
        for(String roleId : roleIds){
            params.put("userId",userId);
            params.put("roleId",roleId);
            userDao.addRoleU(params);
        }
    }


    @Transactional
    public void add(User user)throws UserException {
        //用户名和密码不能重复
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        params.put("userName",user.getUserName());
        params.put("userPassword",user.getUserPassword());
        User _user = userDao.findUserByNameAndPassword(params);
        if(_user !=null){
            throw new UserException("对不起该用户已注册!");
        }
        userDao.add(user);
    }


    @Transactional
    public void delete(String id) {
        userDao.delete(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findObjectById(String id){
        /*
        判断该用户是Vip还是一般用户
         */
        User user = userDao.findObjectVipById(id);
        if(user !=null){
            return user;
        }

        return userDao.findObjectById(id);
    }

    //后台的更新
    @Override
    public void updateUserAndRU(User user, String[] roleIds) throws UserException {
        //用户名和密码不能重复
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        params.put("userName",user.getUserName());
        params.put("userPassword",user.getUserPassword());
        params.put("userId",user.getUserId());
        int counts = userDao.findUserExcludeSelf(params);
        if(counts >0){
            throw new UserException("对不起该用户名已经存在!");
        }
        userDao.update(user);
        //删除该用户和角色之间的关系
        userDao.delRoleUByUId(user.getUserId());
        //然后在添加用户和角色之前的关系
        addRoleIdAndUserId(user.getUserId(),roleIds);
    }

    //前台的更新
    @Transactional
    public void update(User user)throws UserException {
        //用户名和密码不能重复
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        params.put("userName",user.getUserName());
        params.put("userPassword",user.getUserPassword());
        params.put("userId",user.getUserId());
        int counts = userDao.findUserExcludeSelf(params);
        if(counts >0){
            throw new UserException("对不起该用户名已经存在!");
        }
        userDao.update(user);
    }


    public User login(User user)throws UserException{
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        params.put("userName",user.getUserName());
        params.put("userPassword",user.getUserPassword());
        User _user = userDao.findUserByNameAndPassword(params);
        if(_user ==null){
            throw new UserException("用户名和密码错误，请重新输入！");
        }
        return _user;
    }

    @Override
    public List<User> findAllByPaging(Integer pCur,User form) {
        Map<String,Object> params = new HashMap<String,Object>();
        PageResult pr = new PageResult();
        pr.setpCur(pCur);

        params.put("pCur",pr.getpCur());
        params.put("pSize",PageResult.PAGERESULT_PSIZE);
        setUserMapParams(params,form);
        return userDao.findAllByPaging(params);
    }

    public Integer getTotalCounts(User form){
        Map<String,Object> params = new HashMap<String,Object>();
        setUserMapParams(params,form);
        return userDao.getTotalCounts(params);
    }

    @Transactional
    @Override
    public void updateUDelState(String userId) {
        userDao.updateUDelState(userId);
    }

    @Transactional
    @Override
    public void collectMovie(Map<String, Object> params)throws UserException {
        /*
        在收藏之前要根据用户id和视频的id来判断 该视频是否已经收藏
        若已经收藏 则删除收藏记录
        若没有收藏： 则收藏该视频
         */
        int counts = userDao.findCollectMByUIdAndMId(params);
        if(counts>0){
            //说明该视频已经收藏过了需要删除
            throw new UserException();
        }
        userDao.collectMovie(params);
    }

    @Override
    public List<Map<String,Object>> findUserConsume(String userId) {
        return userDao.findUserConsume(userId);
    }
    //页面的用户消费详情页的添加和数据再次封装

    @Override
    public int findCollectMByUIdAndMId(Map<String, Object> params) {

        return userDao.findCollectMByUIdAndMId(params);
    }

    @Override
    public void delCollectMByUIdAndMId(Map<String, Object> params) {
        userDao.delCollectMByUIdAndMId(params);
    }

    @Transactional
    @Override
    public void addUMP(Map<String, Object> params) {
        userDao.addUMP(params);
    }

    @Override
    public void delUMPByUserId(String userId) {
        userDao.delUMPByUserId(userId);
    }

    private void setUserMapParams(Map<String,Object> params, User form){
        params.put("userName", (form.getUserName() != null)? "%"+form.getUserName()+"%": form.getUserName());
        params.put("userGender",form.getUserGender());
        params.put("roleId",form.getRoleId());
    }

}
