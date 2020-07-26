package com.lxh.seckill.other.lock;

import com.lxh.seckill.common.LockTypeEnum;
import com.lxh.seckill.dao.MyLockMapper;
import com.lxh.seckill.entity.MyLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * created by lanxinghua@2dfire.com on 2020/7/25
 * 基于唯一索引实现分布式锁
 */
@Component
public class Lock1 {
    @Autowired
    private MyLockMapper myLockMapper;

    public Boolean getLock(LockTypeEnum typeEnum){
        try {
            MyLock lock = new MyLock();
            lock.setGmtModify(new Date());
            lock.setLockType(typeEnum.getCode());
            lock.setLockDesc(typeEnum.getDesc());
            myLockMapper.insert(lock);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public Boolean unLock(LockTypeEnum typeEnum){
        try {
            myLockMapper.deleteByLockType(typeEnum.getCode());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
