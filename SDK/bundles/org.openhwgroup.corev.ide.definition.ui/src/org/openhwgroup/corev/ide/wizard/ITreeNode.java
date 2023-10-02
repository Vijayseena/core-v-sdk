package org.openhwgroup.corev.ide.wizard;

import java.util.List;
/*
 *Contributors:Promodkumar (OpenHW Group) - initial implementation
 */
public interface ITreeNode
{
	public String getName();

	public List getChildren();

	public boolean hasChildren();

	public ITreeNode getParent();
}