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

import com.toannk.project.dataset.ActorDestiny;
import com.toannk.project.dataset.ActorRoleInDestiny;
import com.toannk.project.dataset.Destiny;
import com.toannk.project.dataset.Equipment;
import com.toannk.project.dataset.EquipmentInDestiny;

/**
 * @author WIN
 *
 */
@Mapper
public interface DestinyMapper {
	
	@Insert("Insert into "
			+ "tbl_destiny ("
			+ "name,"
			+ "location,"
			+ "description,"
			+ "createTime,"
			+ "endTime"
			+ " )"
			+ "values "
			+ "( "
			+ "#{name},"
			+ "#{location},"
			+ "#{description},"
			+ "#{createTime},"
			+ "#{endTime}"
			+ " )")
	public int createDestiny(Destiny destiny);
	
	@Select("Select * from tbl_destiny")
	public List<Destiny> getAllDestiny();
	
	@Update("Update "
			+ "tbl_destiny "
			+ "set "
			+ "name = #{name},"
			+ "location = #{location},"
			+ "description = #{description}, "
			+ "createTime = #{createTime}, "
			+ "endTime = #{endTime} "
			+ "where "
			+ "id = #{id}")
	public int updateDestiny(Destiny destiny);
	
	@Delete("Delete from tbl_destiny where id = #{id}")
	public int deleteDestiny(@Param("id") int id);
	
	
	@Insert("<script>"
			+ "Insert into "
			+ "tbl_actor_detail "
			+ "( "
			+ "username,"
			+ "idDestiny,"
			+ "roleInDestiny,"
			+ "createTime"
			+ " ) "
			+ "values "
			+ "<foreach collection='list' item='element' index='index' open='(' separator='),(' close=')'>"
			+ "#{element.username},"
			+ "#{element.idDestiny},"
			+ "#{element.roleInDestiny},"
			+ "GETDATE()"
			+ "</foreach>"
			+ "</script>")
	public int insertActorToDestiny(List<ActorRoleInDestiny> actorDestiny);
	
	
	@Insert("<script>"
			+ "Insert into "
			+ "tbl_destiny_detail "
			+ "( "
			+ "idEquipment,"
			+ "idDestiny,"
			+ "equipmentQuantity,"
			+ "createTime"
			+ " ) "
			+ "values "
			+ "<foreach collection='list' item='element' index='index' open='(' separator='),(' close=')'>"
			+ "#{element.idEquipment},"
			+ "#{element.idDestiny},"
			+ "#{element.equipmentQuantity},"
			+ "GETDATE() "
			+ "</foreach>"
			+ "</script>")
	public int insertQuantityEquipmentInDestiny(List<EquipmentInDestiny> listEquipmentInDestiny);
	
	@Select("Select "
			+ "count(*) "
			+ "from "
			+ "tbl_destiny_detail "
			+ "where "
			+ "idEquipment = #{idEquipment},"
			+ "idDestiny = #{idDestiny}")
	public int isQuantityEquipmentInDestinyExisted(EquipmentInDestiny equipmentInDestiny);
	
	@Update("Update "
			+ "tbl_equipment "
			+ "set "
			+ "quantity = #{quantity},"
			+ "status = #{status},"
			+ "updateTime = GETDATE()"
			+ "where "
			+ "id = #{id}")
	public int updateQuantityEquipment(Equipment equipment);
	
	@Select("Select "
			+ "quantity "
			+ "from "
			+ "tbl_equipment "
			+ "where "
			+ "id = #{id}")
	public int getQuantityEquipmentById(int id);
	
	
	
}
