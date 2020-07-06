/**
 * 
 */
package com.toannk.project.service;

import java.util.List;

import com.toannk.project.dataset.Equipment;

/**
 * @author WIN
 *
 */
public interface EquipmentService {
	public List<Equipment> getAllEquipment();
	public int insertEquipment(Equipment equipment);
	public int updateEquipment(Equipment equipment,int id);
	public int deleteEquipment(int id);
}
