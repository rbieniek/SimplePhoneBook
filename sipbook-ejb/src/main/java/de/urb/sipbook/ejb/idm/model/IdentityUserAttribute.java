/**
 * 
 */
package de.urb.sipbook.ejb.idm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rainer
 *
 */
@Entity
@Table(name="IDENTITY_USER_ATTRIBUTE")
public class IdentityUserAttribute implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7008761677676090283L;
	
	private @Id @GeneratedValue @Column(name="ID", nullable=false) Long id;
	private @Column(name="NAME", nullable=false, unique=true, length=64) String name;
	private @Column(name="VALUE", nullable=false, unique=true, length=256) String value;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
