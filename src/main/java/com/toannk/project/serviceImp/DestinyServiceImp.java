/**
 * 
 */
package com.toannk.project.serviceImp;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.toannk.project.dataset.ActorDestiny;
import com.toannk.project.dataset.ActorRoleInDestiny;
import com.toannk.project.dataset.Destiny;
import com.toannk.project.dataset.Equipment;
import com.toannk.project.dataset.EquipmentInDestiny;
import com.toannk.project.mapper.DestinyMapper;
import com.toannk.project.service.DestinyService;
import com.toannk.project.utils.ConstranstUtils;

/**
 * @author WIN
 *
 */
@Service
public class DestinyServiceImp implements DestinyService {

	private final int ACTION_ON_DESTINY_ERROR = -3;

	private final int QUANTITY_IN_DESTINY_IS_EXISTED = 0;

	private final int QUANTITY_INPUT_ERROR = -1;

	private final String AVAILABLE_EQUIPMENT = "available";

	private final String NON_AVAILABLE_EQUIPMENT = "non-available";
	@Autowired
	private DestinyMapper mapper;

	public DestinyServiceImp(DestinyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Destiny> getAllDestiny() {
		return mapper.getAllDestiny();
	}

	@Override
	public int insertDestiny(Destiny destiny) {
		int result = ACTION_ON_DESTINY_ERROR;
		try {
			result = mapper.createDestiny(destiny);
		} catch (Exception e) {
			if (e.getMessage().contains("PRIMARY")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_PRIMARY_KEY;
			}
			System.out.print("Exception in insert: " + e.getMessage());
			throw new PersistenceException("Exception in InsertDestiny");
		}
		return result;
	}

	@Override
	public int updateDestiny(Destiny destiny, int id) {
		destiny.setId(id);
		return mapper.updateDestiny(destiny);
	}

	@Override
	public int deleteDestiny(int id) {
		int result = ACTION_ON_DESTINY_ERROR;
		try {
			result = mapper.deleteDestiny(id);
		} catch (Exception e) {
			if (e.getMessage().contains("REFERENCE")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_REFERENCE;
			}
			System.out.print("Exception in delete: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int addListActorToDestiny(List<ActorRoleInDestiny> listActorDestiny) {
		int result = -1;
		try {
			result = mapper.insertActorToDestiny(listActorDestiny);
		} catch (Exception e) {
			if (e.getMessage().contains("PRIMARY")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_PRIMARY_KEY;
			}
			System.out.print("Exception in addActorToDestiny: " + e.getMessage());
			throw new PersistenceException("Exception Primarykey");
		}
		return result;
	}

	@Override
	public int addListEquipmentToDestiny(List<EquipmentInDestiny> listEquipmentInDestiny) {
		int result = QUANTITY_INPUT_ERROR;
		try {
			// check if quantity input is greatter than quantity in store
			boolean isInValidInput = listEquipmentInDestiny
					.stream()
					.anyMatch(e -> e.getEquipmentQuantity() > mapper.getQuantityEquipmentById(e.getIdEquipment()));
			
			if (isInValidInput) {
				return result;
			} 

			// insert list to DB
			result = mapper.insertQuantityEquipmentInDestiny(listEquipmentInDestiny);
			
			// update quantity in store
			listEquipmentInDestiny.stream().forEach(e -> {
				int quantityAfter = mapper.getQuantityEquipmentById(e.getIdEquipment()) - e.getEquipmentQuantity();
				Equipment equipment = new Equipment();
				equipment.setId(e.getIdEquipment());
				equipment.setQuantity(quantityAfter);
				if (quantityAfter == 0)
					equipment.setStatus(NON_AVAILABLE_EQUIPMENT);
				else
					equipment.setStatus(AVAILABLE_EQUIPMENT);

				mapper.updateQuantityEquipment(equipment);
			});

		} catch (Exception e) {
			if (e.getMessage().contains("PRIMARY")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_PRIMARY_KEY;
			} else if (e.getMessage().contains("CHECK")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_QUANTITY_GREATTER_THAN_ZERO;
			}
			System.out.print("Exception in addEquipmentToDestiny: " + e.getMessage());
			throw new PersistenceException("Exception");
		}
		return result;

	}

}
