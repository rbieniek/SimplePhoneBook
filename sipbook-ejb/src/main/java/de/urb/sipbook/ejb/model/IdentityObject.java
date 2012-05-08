/**
 * 
 */
package de.urb.sipbook.ejb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

/**
 * @author rainer
 *
 */
@Entity
@Table(name="IDENTITY_OBJECT")
public class IdentityObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8149761429242660793L;

	@Id @GeneratedValue @Column(name="ID") private Long id;
	@IdentityProperty(PropertyType.NAME) @Column(name="NAME") private String name;
	@ManyToOne @JoinColumn(name="IDENTITY_OBJECT_TYPE_ID") @IdentityProperty(PropertyType.TYPE) private IdentityObjectType  type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IdentityObjectType getType() {
		return type;
	}

	public void setType(IdentityObjectType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}
	
}
