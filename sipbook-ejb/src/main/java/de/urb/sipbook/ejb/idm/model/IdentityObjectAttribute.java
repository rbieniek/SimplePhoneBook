/**
 * 
 */
package de.urb.sipbook.ejb.idm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jboss.seam.security.annotations.management.EntityType;
import org.jboss.seam.security.annotations.management.IdentityEntity;
import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

/**
 * @author rainer
 *
 */
@Entity
@Table(name="IDENTITY_OBJECT_ATTRIBUTE")
@IdentityEntity(EntityType.IDENTITY_ATTRIBUTE)
public class IdentityObjectAttribute implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3082425853427811797L;
	
	@Id @GeneratedValue @Column(name="ID") private Long id;
	@ManyToOne @JoinColumn(name="IDENTITY_OBJECT_ID") private IdentityObject identityObject;
	@IdentityProperty(PropertyType.NAME) @Column(name="NAME") private String name;
	@IdentityProperty(PropertyType.VALUE) @Column(name="VALUE") private String value;
	
	public IdentityObject getIdentityObject() {
		return identityObject;
	}
	
	public void setIdentityObject(IdentityObject identityObject) {
		this.identityObject = identityObject;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}
}
