package orm.myBatis.domain;

import java.util.Date;

public class UserInfo {
	
	public Integer id;
	
	public String  name;
	
	public String  userCode;
	
	public Integer age;
	
	public Date    creationTime;
	
	public Date    updateTime;
	
	public Integer  yn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}
	public UserInfo(){
		
	}
	
	public UserInfo(String name,String userCode,int age,Date creationTime,Date updateTime){
		this.name = name;
		this.userCode = userCode;
		this.age = age;
		this.creationTime = creationTime;
		this.updateTime = updateTime;
	}
	
}
