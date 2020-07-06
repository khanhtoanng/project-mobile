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

import com.toannk.project.dataset.Equipment;

/**
 * @author WIN
 *
 */
@Mapper
public interface EquipmentMapper {
	
	@Insert("Insert into tbl_equipment "
			+ "values "
			+ "( "
			+ "#{name},"
			+ "#{description},"
			+ "#{quantity},"
			+ "#{image},"
			+ "GETDATE(),"
			+ "#{status}"
			+ " )")
	public int createEquipment(Equipment equipment);
	
	@Select("Select * from tbl_equipment")
	public List<Equipment> getAllEquipment();
	
	@Update("Update "
			+ "tbl_equipment "
			+ "set "
			+ "name = #{name},"
			+ "description = #{description}, "
			+ "quantity = #{quantity},"
			+ "image = #{image},"
			+ "status = #{status} "
			+ "where "
			+ "id = #{id}")
	public int updateEquipment(Equipment equipment);
	
	@Delete("Delete from tbl_equipment where id = #{id}")
	public int deleteEquipment(@Param("id") int id);
	
	@Select("Select * "
			+ "from "
			+ "tbl_equipment "
			+ "where "
			+ "status = #{status} "
			+ "and "
			+ "createTime "
			+ "between "
			+ "#{fromDate} "
			+ "and "
			+ "#{toDate}")
	public void getEquipmentByTime(@Param("fromDate")  String fromDate,@Param("toDate")String toDate,@Param("status") String status);
	

	
}
