package com.ahrtolia.dao;

import com.ahrtolia.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User login(User user);

    List<Order> getMyHasPayedOrder(int userId, int begin, int limit);

    int getMyHasPayedOrderCount(int userId);

    Product getProductDetaile(int productId);

    void addToCar(int userId, int productId, int count, int state);

    List<Order> getMyProductCar(int userId);

    void deleteCarProduct(int orderId);

    void updateOrderCount(int orderId, int count);

    UserAddress getUserAddress(int userId);

    void updateOrder(String createTime, String serialNumber, int addressId, int userId);

    String getSerialNumber(int orderId);

    String getOrderAddress(int userId);

    List<Favorite> getCollection(int userId);

    void remove(int favoriteId);

    int doCollect(int userId, int productId);

    User findPhone(String mobile);

    User findUserName(String userName);

    void register(String name, String userName, String password, String sex, String identityCode, String email, String mobile, String fileName);

    Favorite findFavorite(int userId, int productId);

    Order getOrderCount(int userId, int productId);

    int addCarCount(int userId, int productId, int count);

    void findProductIds(int userId, String pids);

    List<Order> getOrder(int userId);

    List<Order> getMyOrderCar(int userId);

    void updateOrderPayState(String orderNo);

    void addNewAddress(int userId, String address);

    int modifyUserPhoto(@Param("fileName") String fileName, @Param("id") int id);

    List<UserAddress> getAllAddress(@Param("userId") int userId);

    void deleteAddress(@Param("id") int id);

    void addDeleteAddress(int id);

    void saveActiveCode(@Param("id") int id,@Param("activeCode") String activeCode);

    void updateActiveCode(@Param("id") int id);

    User getUserById(@Param("id") int id);

    void weChatAddUser(@Param("userName") String userName, @Param("name") String name, @Param("sex") String sex, @Param("password")String password, @Param("fileName")String fileName);

    User getEmail(@Param("id") int id);

    void doAddEmail(@Param("id")int id, @Param("email")String email);
}
