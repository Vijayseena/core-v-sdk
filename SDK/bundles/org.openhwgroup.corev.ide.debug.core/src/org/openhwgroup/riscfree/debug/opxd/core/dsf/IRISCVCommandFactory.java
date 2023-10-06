package org.openhwgroup.riscfree.debug.opxd.core.dsf;

import org.eclipse.cdt.dsf.datamodel.IDMContext;
import org.openhwgroup.riscfree.debug.opxd.core.comrv.MIComRVEngineDebugging;


public interface IRISCVCommandFactory {
	/**
	 * Create Overlay command
	 * 
	 * @param ctx
	 * @return
	 */
	public MIComRVEngineDebugging createSkipComRVEngineDebugging(IDMContext ctx, boolean isOverlayEnabled);
}