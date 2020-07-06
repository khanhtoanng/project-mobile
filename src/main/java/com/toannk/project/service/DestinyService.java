/**
 * 
 */
package com.toannk.project.service;

import java.util.List;

import com.toannk.project.dataset.ActorDestiny;
import com.toannk.project.dataset.ActorRoleInDestiny;
import com.toannk.project.dataset.Destiny;
import com.toannk.project.dataset.EquipmentInDestiny;

/**
 * @author WIN
 *
 */
public interface DestinyService {
	public List<Destiny> getAllDestiny();
	public int insertDestiny(Destiny destiny);
	public int updateDestiny(Destiny destiny,int id);
	public int deleteDestiny(int id);
	public int addListActorToDestiny(List<ActorRoleInDestiny> listActorDestiny);
	public int addListEquipmentToDestiny(List<EquipmentInDestiny> listEquipmentInDestiny);
}
