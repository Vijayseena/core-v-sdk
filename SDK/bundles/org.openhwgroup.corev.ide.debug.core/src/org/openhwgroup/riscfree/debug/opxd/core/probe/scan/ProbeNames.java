/*******************************************************************************
 * Copyright (c) 2012 OpenHW Group.
 * All rights reserved. 
 * 
 * This software is the confidential and proprietary information of 
 * OpenHW Group. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with OpenHW Group.
 * 
 * Contributors: 
 * Vinod appu P.G. - 18-09-2012 - Initial version
 * Vinod appu P.G. - 27-09-2012 - Override toString.
 *******************************************************************************/
package org.openhwgroup.riscfree.debug.opxd.core.probe.scan;

/**
 * ProbeNames defines the names of OpenHW Group debug probes.
 * 
 * @author vinod.appu
 * 
 */
public enum ProbeNames {
	OPELLA_XD;
	
	@Override
	public String toString() {
		String probeName;
		switch (this) {
		case OPELLA_XD:
			probeName = "Opella-XD";
			break;
		default:
			probeName = "Invalid Debug probe";
			break;
		}
		return probeName;
	};
}
