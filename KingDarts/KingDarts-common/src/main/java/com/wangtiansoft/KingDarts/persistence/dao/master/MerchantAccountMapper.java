package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MerchantAccountMapper extends BaseMapper<MerchantAccount> {

    List<Map> queryMerchantAccountList(Map paramMap);

    //根据用户名查找用户
    @Select("select * from darts_merchant_account where (merno = #{loginName} or mobile = #{loginName}) and isdelete=0")
    MerchantAccount getUserByLoginName(String loginName);
    
    //根据手机号查找用户
    @Select("select * from darts_merchant_account where mobile=#{mobile} and isdelete=0")
    MerchantAccount getUserByMobile(String mobile);
    
    //根据Id修改密码
    @Update("update darts_merchant_account set password=#{password},mobile=#{mobile} where id=#{id}")
    Integer updatePasswordById(@Param("password")String password,@Param("mobile")String mobile,@Param("id")Integer id);
    
    //根据商户编号修改密码
    @Update("update darts_merchant_account set password=#{password},mobile=#{mobile},accountnames=#{accountnames},mertype=#{mertype} where merno=#{merno}")
    Integer updatePasswordByMerno(@Param("password")String password,@Param("mobile")String mobile,
    		@Param("accountnames")String accountnames,@Param("mertype")Integer mertype,@Param("merno")String merno);
    
    //根据商户编号修改用户名
    @Update("update darts_merchant_account set mobile=#{mobile},accountnames=#{accountnames},mertype=#{mertype} where merno=#{merno}")
    Integer updateAccountNameByMerno(@Param("mobile")String mobile,@Param("accountnames")String accountnames,
    		@Param("mertype")Integer mertype,@Param("merno")String merno);
    
    //根据商户编号修改微信openid
    @Update("update darts_merchant_account set openid=#{openid} where merno=#{merno}")
    Integer updateOpenidByMerno(@Param("openid")String openid,@Param("merno")String merno);
    
    @Select("select password from sys_user where login_name = #{merno}")
    String getOldSysUserPwd(String merno);
}