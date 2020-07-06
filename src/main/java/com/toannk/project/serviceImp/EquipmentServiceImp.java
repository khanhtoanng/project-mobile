/**
 * 
 */
package com.toannk.project.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toannk.project.dataset.Equipment;
import com.toannk.project.mapper.EquipmentMapper;
import com.toannk.project.service.EquipmentService;
import com.toannk.project.utils.ConstranstUtils;

/**
 * @author WIN
 *
 */
@Service
public class EquipmentServiceImp implements EquipmentService {
	private final int ACTION_ON_EQUIPMENT_ERROR = -3;

	@Autowired
	private EquipmentMapper mapper;

	public EquipmentServiceImp(EquipmentMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Equipment> getAllEquipment() {
		return mapper.getAllEquipment();
	}

	@Override
	public int insertEquipment(Equipment equipment) {
		int result = ACTION_ON_EQUIPMENT_ERROR;
		try {
			result = mapper.createEquipment(equipment);
		} catch (Exception e) {
			if (e.getMessage().contains("PRIMARY")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_PRIMARY_KEY;
			}
			System.out.print("Exception in insert: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int updateEquipment(Equipment equipment, int id) {
		equipment.setId(id);
		return mapper.updateEquipment(equipment);
	}

	@Override
	public int deleteEquipment(int id) {
		int result = ACTION_ON_EQUIPMENT_ERROR;
		try {
			result = mapper.deleteEquipment(id);
		} catch (Exception e) {
			if (e.getMessage().contains("REFERENCE")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_REFERENCE;
			}
			System.out.print("Exception in delete: " + e.getMessage());
		}
		return result;
	}

}
