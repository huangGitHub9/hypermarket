package com.ahrtolia.dao;

import com.ahrtolia.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    User login(User user);

    List<Order> getMyHasPayedOrder(@Param("userId") int userId);

    Product getProductDetaile(@Param("productId") int productId);

    void addToCar(@Param("userId") int userId, @Param("productId") int productId, @Param("count") int count, @Param("state")int state);

    List<Order> getMyProductCar(@Param("userId") int userId);

    void deleteCarProduct(@Param("orderId") int orderId);

    void updateOrderCount(@Param("orderId") int orderId, @Param("count") int count);

    UserAddress getUserAddress(@Param("userId") int userId);

    void updateOrder(@Param("createTime")String createTime, @Param("serialNumber")String serialNumber, @Param("addressId")int addressId, @Param("userId")int userId);

    String getSerialNumber(@Param("orderId") int orderId);

    String getOrderAddress(@Param("userId") int userId);

    List<Favorite> getCollection(@Param("userId") int userId);

    void remove(@Param("favoriteId") int favoriteId);

    int doCollect(@Param("userId") int userId, @Param("productId") int productId);

    User findPhone(@Param("mobile") String mobile);

    User findUserName(@Param("userName") String userName);

    void register(@Param("name") String name, @Param("userName") String userName, @Param("password") String password, @Param("sex") String sex, @Param("identityCode") String identityCode, @Param("email") String email, @Param("mobile") String mobile, @Param("fileName") String fileName);

    Favorite findFavorite(@Param("userId")int userId, @Param("productId")int productId);

    Order getOrderCount(@Param("userId")int userId, @Param("productId")int productId);

    int addCarCount(@Param("userId")int userId, @Param("productId")int productId, @Param("count") int count);

    void findProductIds(@Param("userId") int userId, @Param("pids")String pids);

    List<Order> getOrder(@Param("userId") int userId);

    List<Order> getMyOrderCar(@Param("userId") int userId);

    void updateOrderPayState(@Param("serialNumber") String orderNo);

    void addNewAddress(@Param("userId") int userId,@Param("address") String address);

    int modifyUserPhoto(@Param("fileName") String fileName, @Param("id") int id);

    List<UserAddress> getAllAddress(@Param("userId") int userId);

    void deleteAddress(@Param("id") int id);

    void addDeleteAddress(@Param("id") int id);

    void saveActiveCode(@Param("id") int id, @Param("activeCode") String activeCode);

    void updateActiveCode(@Param("id") int id);

    User getUserById(@Param("id") int id);

    void weChatAddUser(@Param("userName") String userName, @Param("name") String name, @Param("sex") String sex, @Param("password") String password, @Param("fileName") String fileName);

    User getEmail(@Param("id") int id);

    void doAddEmail(@Param("id") int id, @Param("email") String email);
}
