package ru.study.simpleshop.dao;

public interface UserProductDAO {
    void save(Long user_id, Long product_id);

    void remove(Long user_id, Long product_id);

    void removeAllByUserId(Long user_id);
}
