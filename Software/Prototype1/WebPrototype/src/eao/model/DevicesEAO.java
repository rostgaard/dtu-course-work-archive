package eao.model;

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


	public DeviceEntity addDeviceName(String mac, String name) {
		DeviceEntity deviceEntity = getDeviceEntity(mac, name);
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

	public DeviceEntity getDevice(String mac){
		DeviceEntity device = em.find(DeviceEntity.class, mac);
		return device;

	}

	public DeviceEntity getDeviceEntity(String mac, String name) {
		TypedQuery<DeviceEntity> query = em.createQuery(Query.queryGetDevice, DeviceEntity.class);
		query.setParameter("mac", mac);
		List<DeviceEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	public DeviceEntity update(String mac, String name) {
		TypedQuery<DeviceEntity> query = em.createQuery(Query.queryGetDevice, DeviceEntity.class);
		query.setParameter("mac", mac);
		DeviceEntity result = query.getSingleResult();
		result.setName(name);
	    return em.merge(result);
	}

}
