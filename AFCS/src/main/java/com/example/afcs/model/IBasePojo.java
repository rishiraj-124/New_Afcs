/**
 * 
 */
package com.example.afcs.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rishiraj
 *
 */
public interface IBasePojo extends Serializable {

	public Object getPrimaryKey();

	public String getValue();

	public void setCreatedOn(final Date createdDate);

	public void setLastUpdatedOn(final Date updatedOn);

	public void setCreatedBy(final String createdBy);

	public void setLastUpdatedBy(final String lastUpdatedBy);
}
