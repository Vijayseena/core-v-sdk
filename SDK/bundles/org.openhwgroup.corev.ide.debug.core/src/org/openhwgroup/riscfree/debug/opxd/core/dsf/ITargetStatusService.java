/**
 * 
 */
package org.openhwgroup.riscfree.debug.opxd.core.dsf;

/**
 * @author vinod.appu
 *
 */
public interface ITargetStatusService {

	void startStatusPolling();
	
	void stopStatusPolling();
}
