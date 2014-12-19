package eao.model;

import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.model.DeviceEntity;


/**
 *
 * @author s124255
 *
 */
@LocalBean
@Stateless
public class DevicesEAO {

	@PersistenceContext(unitName = "Prototype2")
	EntityManager em;


	/**
	 * @param mac The mac address of the device to be added
	 * @param name The name of the device to be added
	 * @return A DeviceEntity object of the added entity
	 */
	public DeviceEntity addDeviceName(String mac, String name) {
		DeviceEntity deviceEntity = getDevice(mac);
		if (deviceEntity != null) {
			return null;
		}

		deviceEntity = new DeviceEntity();
		deviceEntity.setMac(mac);
		deviceEntity.setName(name);
		em.persist(deviceEntity);
		em.flush();

		return deviceEntity;
	}

	/**
	 * @param mac The mac address of the device to be found in the database by the EntityManager
	 * @return A DeviceEntity object of the found entity
	 */
	public DeviceEntity getDevice(String mac){
		DeviceEntity device = em.find(DeviceEntity.class, mac);
		if(device == null){
			return null;
		}
		return device;

	}

	/**
	 * @deprecated
	 * Not in use anymore
	 * @param mac The mac address of the device to be found in the database by Query request
	 * @return A DeviceEntity object of the found entity
	 */
	public DeviceEntity getDeviceEntity(String mac) {
		TypedQuery<DeviceEntity> query = em.createQuery(Query.queryGetDevice, DeviceEntity.class);
		query.setParameter("mac", mac);
		List<DeviceEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	
	/**
	 * @param mac The mac address of the device which name will be updated
	 * @param name The new name for the device
	 * @return An DeviceEntity object of the entity merged
	 */
	public DeviceEntity updateName(String mac, String name) {
		TypedQuery<DeviceEntity> query = em.createQuery(Query.queryGetDevice, DeviceEntity.class);
		query.setParameter("mac", mac);
		DeviceEntity result = query.getSingleResult();
		result.setName(name);
	    return em.merge(result);
	}

	/**
	 * @return A list of all DeviceEntity in the database
	 */
	public List<DeviceEntity> getAllDeviceEntitylist() {
			TypedQuery<DeviceEntity> query = em.createQuery(Query.querytextGetAllDeviceNames, DeviceEntity.class);	
			List<DeviceEntity> result = query.getResultList();

			if (result.size() > 0) {
				return result;
			} else {
				return Collections.emptyList();
			}
		}
	

}
