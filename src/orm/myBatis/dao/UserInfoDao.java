package orm.myBatis.dao;

import org.apache.ibatis.annotations.Param;

import orm.myBatis.domain.UserInfo;

public interface UserInfoDao {
	
	public UserInfo getUserByUserCode(@Param("userCode") String userCode);
	
	public UserInfo getUserByUserId(@Param("id") int id);
	
	public void insertUser(UserInfo userInfo);
	
}
