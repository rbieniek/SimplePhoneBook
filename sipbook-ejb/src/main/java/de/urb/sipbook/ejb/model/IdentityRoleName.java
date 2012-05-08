/**
 * 
 */
package de.urb.sipbook.ejb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

/**
 * @author rainer
 *
 */
@Entity
@Table(name="IDENTITY_ROLE_NAME")
public class IdentityRoleName implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1457966551920196976L;

	@Id @GeneratedValue @Column(name="ID", nullable=false) private Long id;
	
	@IdentityProperty(PropertyType.NAME) @Column(name="NAME", nullable=false) private String name;
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}