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
 *******************************************************************************/
package org.openhwgroup.riscfree.debug.opxd.core.probe.scan;

/**
 * CommunicationMedium defines the communication interfaces supported
 * by OpenHW Group debug probes.
 * 
 * @author vinod.appu
 * 
 */
public enum CommunicationMedium {
	/** Denotes the USB communication interface.*/
	USB, 
	/** Denotes the Ethernet communication interface.*/
	ETHERNET
}
