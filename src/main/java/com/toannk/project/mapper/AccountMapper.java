/**
 * 
 */
package com.toannk.project.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.toannk.project.dataset.Account;
import com.toannk.project.dataset.ActorDestiny;

/**
 * @author WIN
 *
 */
@Mapper
public interface AccountMapper {
	
	
	@Insert("Insert into "
			+ "tbl_account"
			+ "("
			+ "username,"
			+ "password,"
			+ "phone,"
			+ "email,"
			+ "fullname,"
			+ "role,"
			+ "isActive,"
			+ "descriptionAccount,"
			+ "image,"
			+ "createTime"
			+ ") "
			+ "values "
			+ "( "
			+ "#{username},"
			+ "#{password},"
			+ "#{phone},"
			+ "#{email},"
			+ "#{fullname},"
			+ "'actor',"
			+ "0,"
			+ "#{descriptionAccount},"
			+ "#{image},"
			+ "GETDATE()"
			+ " )")
	public int createActor(Account account);
	
	@Select("Select * from tbl_account where role = 'actor'")
	public List<Account> getAllActor();
	
	@Update("Update "
			+ "tbl_account "
			+ "set "
			+ "password = #{password},"
			+ "phone = #{phone},"
			+ "email = #{email}, "
			+ "fullname = #{fullname}, "
			+ "isActive = #{isActive}, "
			+ "descriptionAccount = #{descriptionAccount},"
			+ "updateTime = GETDATE(),"
			+ "image = #{image},"
			+ "updateAccount = #{updateAccount} "
			+ "where "
			+ "username = #{username}")
	public int updateActor(Account account);
	
	@Delete("Delete from tbl_account where username = #{username} and role = 'actor'")
	public int deleteActor(@Param("username") String username);
	
	
	@Select("Select "
			+ "count(*) "
			+ "from "
			+ "tbl_account "
			+ "where "
			+ "username = #{username}")
	public int isExistAccount(@Param("username") String username);
	
	@Select("Select "
			+ "role "
			+ "from "
			+ "tbl_account "
			+ "where "
			+ "username = #{username} "
			+ "and "
			+ "password = #{password}")
	public String isLogin(@Param("username") String username, @Param("password") String password);
	
	
	@Select("Select " + 
			"DES.name," + 
			"DES.location," + 
			"DES.numberOfScreen," + 
			"DES.description," + 
			"DES.detail," + 
			"DES.endTime," + 
			"DES.createTime," + 
			"ACT.roleInDestiny," + 
			"ACT.contentRole " + 
			"from " + 
			"tbl_destiny as DES " + 
			"INNER JOIN " + 
			"tbl_actor_detail as ACT " + 
			"ON " + 
			"DES.id = ACT.idDestiny " + 
			"Where " + 
			"DES.endTime < GETDATE() "
			+ "AND "
			+ "ACT.username = #{username}")
	public List<ActorDestiny> getHistoryDestiny(@Param("username") String username);
	
	@Select("Select " + 
			"DES.name," + 
			"DES.location," + 
			"DES.numberOfScreen," + 
			"DES.description," + 
			"DES.detail," + 
			"DES.endTime," + 
			"DES.createTime," + 
			"ACT.roleInDestinty," + 
			"ACT.contentRole " + 
			"from " + 
			"tbl_destiny as DES " + 
			"INNER JOIN " + 
			"tbl_actor_detail as ACT " + 
			"ON " + 
			"DES.id = ACT.idDestiny " + 
			"Where " + 
			"DES.createTime > GETDATE() "
			+ "AND "
			+ "ACT.username = #{username}")
	public List<ActorDestiny> getIncomingDestiny(@Param("username") String username);
	
	
}
