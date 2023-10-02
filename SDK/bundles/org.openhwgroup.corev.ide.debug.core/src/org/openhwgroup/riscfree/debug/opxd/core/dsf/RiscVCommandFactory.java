/**
 * 
 */
package org.openhwgroup.riscfree.debug.opxd.core.dsf;

import org.eclipse.cdt.dsf.datamodel.IDMContext;
import org.openhwgroup.riscfree.debug.opxd.core.comrv.MIComRVEngineDebugging;

/**
 * @author vinod.appu
 *
 */
public class RiscVCommandFactory extends AshCommandFactory implements IRISCVCommandFactory{

	@Override
	public MIComRVEngineDebugging createSkipComRVEngineDebugging(IDMContext ctx, boolean isOverlayEnabled) {

		return new MIComRVEngineDebugging(ctx, isOverlayEnabled);

	}
}
