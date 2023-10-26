package com.scu275.invoicemanagement.entity;

// 在一个独立的包下创建仓库接口

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 自定义查询方法（如果需要）
}
