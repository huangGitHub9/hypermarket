package com.ahrtolia.service;

import com.ahrtolia.entity.*;

import java.util.List;

public interface UserService {

    User login(User user);

    void register(User user);

    List<Order> getMyHasPayedOrder(User user, int page, int limit);

    int getMyHasPayedOrderCount(User user, int limit);

    Product getProductDetaile(int productId);

    void addToCar(int userId, int productId, int count, int state);

    List<Order> getMyProductCar(int userId);

    void deleteCarProduct(int orderId);

    void updateOrderCount(int orderId, int count);

    UserAddress getUserAddress(int userId);

    void updateOrder(int addressId, int userId);

    String getSerialNumber(int orderId);

    String getOrderAddress(int userId);

    List<Favorite> getCollection(int userId);

    void remove(int favoriteId);

    int doCollect(int userId, int productId);

    User findPhone(String mobile);

    User findUserName(String userName);

    Favorite findFavorite(int userId, int productId);

    Order getOrderCount(int userId, int productId);

    int addCarCount(int userId, int productId, int count0);

    void findProductIds(int userId, String productIds);

    List<Order> getOrder(int userId);

    List<Order> getMyOrderCar(int userId);

    void updateOrderPayState(String orderNo);

    void addNewAddress(int userId, String address);

    int modifyUserPhoto(String fileName, int id);

    List<UserAddress> getAllAddress(int userId);

    void deleteAddress(int id);

    void addDeleteAddress(int id);

    void AddActiveCode(int id,String email);

    void updateActiveCode(int id);

    User getUserById(int id);

    void weChatAddUser(WechatUserUnionID userUnionID);

    User getEmail(int id);

    String addEmail(int id, String email);

    void doAddEmail(int id, String email);
}
