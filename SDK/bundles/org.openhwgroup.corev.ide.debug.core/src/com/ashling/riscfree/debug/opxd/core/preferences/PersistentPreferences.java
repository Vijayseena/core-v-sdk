package com.ashling.riscfree.debug.opxd.core.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class PersistentPreferences {
	// ------------------------------------------------------------------------

	protected static IScopeContext fgContexts[] = new IScopeContext[] { InstanceScope.INSTANCE,
			ConfigurationScope.INSTANCE, DefaultScope.INSTANCE };

	// ------------------------------------------------------------------------

	/**
	 * Search the given scopes and return the non empty trimmed string or the
	 * default.
	 * 
	 * @param pluginId
	 *            a string with the plugin id.
	 * @param key
	 *            a string with the key to search.
	 * @param defaultValue
	 *            a string with the default, possibly null.
	 * @param contexts
	 *            an array of IScopeContext.
	 * @return a trimmed string or the given default, possibly null.
	 */
	public static String getPreferenceValueForId(String pluginId, String key, String defaultValue,
			IScopeContext[] contexts) {

		String value = null;
		for (int i = 0; i < contexts.length; ++i) {
			value = contexts[i].getNode(pluginId).get(key, null);

			if (value != null) {
				value = value.trim();

				if (!value.isEmpty()) {
					break;
				}
			}
		}

		if (value != null) {			
			return value;
		}
		return defaultValue;
	}

	/**
	 * Compute a maximum array of scopes where to search for.
	 * 
	 * @param project
	 *            the IProject reference to the project, possibly null.
	 * @return an array of IScopeContext.
	 */
	public static IScopeContext[] getPreferenceScopeContexts(IProject project) {

		// If the project is known, the contexts searched will include the
		// specific ProjectScope.
		IScopeContext[] contexts;
		if (project != null) {
			contexts = new IScopeContext[] { new ProjectScope(project), InstanceScope.INSTANCE,
					ConfigurationScope.INSTANCE, DefaultScope.INSTANCE };
		} else {
			contexts = fgContexts;
		}
		return contexts;
	}

	/**
	 * Search all scopes and return the non empty trimmed string or the default.
	 * 
	 * @param pluginId
	 *            a string with the plugin id.
	 * @param key
	 *            a string with the key to search.
	 * @param defaultValue
	 *            a string with the default, possibly null.
	 * @param project
	 *            the IProject reference to the project, possibly null.
	 * @return a trimmed string or the given default, possibly null.
	 */
	public static String getPreferenceValueForId(String pluginId, String key, String defaultValue, IProject project) {

		IScopeContext[] contexts = getPreferenceScopeContexts(project);
		return getPreferenceValueForId(pluginId, key, defaultValue, contexts);
	}

	// ------------------------------------------------------------------------

	protected String fPluginId;

	// ------------------------------------------------------------------------

	public PersistentPreferences(String pluginId) {
		fPluginId = pluginId;
	}

	// ----- Getters ----------------------------------------------------------

	/**
	 * Search string in persistent store. Explicitly define the list of scopes,
	 * starting with project scope.
	 * 
	 * @param key
	 * @param defaultValue
	 * @param project
	 * @return
	 */
	protected String getString(String key, String defaultValue, IProject project) {

		assert (project != null);
		IScopeContext[] contexts = new IScopeContext[] { new ProjectScope(project), InstanceScope.INSTANCE,
				ConfigurationScope.INSTANCE, DefaultScope.INSTANCE };

		String value = getPreferenceValueForId(fPluginId, key, defaultValue, contexts);
		if (value != null && !value.isEmpty()) {
			return value;
		}

		return defaultValue;
	}

	/**
	 * Search string in persistent stores. Explicitly define the list of scopes,
	 * excluding the project scope.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected String getString(String key, String defaultValue) {

		String value = getPreferenceValueForId(fPluginId, key, defaultValue, fgContexts);
		if (value != null && !value.isEmpty()) {
			return value;
		}

		return defaultValue;
	}

	protected String getEclipseString(String key, String defaultValue) {

		// Access the Eclipse scope.
		Preferences preferences = ConfigurationScope.INSTANCE.getNode(fPluginId);

		String value = preferences.get(key, defaultValue);
		
		return value;
	}

	private String getWorkspaceString(String key, String defaultValue) {

		// Access the Workspace scope.
		Preferences preferences = InstanceScope.INSTANCE.getNode(fPluginId);

		String value = preferences.get(key, defaultValue);

		return value;
	}

	// ------------------------------------------------------------------------

	public boolean getBoolean(String key, boolean defaultValue) {

		boolean value;

		value = Platform.getPreferencesService().getBoolean(fPluginId, key, defaultValue, fgContexts);

		return value;
	}

	// ----- Setters ----------------------------------------------------------

	/**
	 * Store a string in the persistent store. If the key was already present in
	 * the workspace, store it there, otherwise use the glonbal store.
	 * 
	 * @param key
	 * @param value
	 */
	protected void putString(String key, String value) {

		String oldValue = getWorkspaceString(key, null);
		if (oldValue != null) {
			putWorkspaceString(key, value);
		} else {
			putEclipseString(key, value);
		}
	}

	protected void putEclipseString(String key, String value) {

		value = value.trim();

		// Access the Eclipse scope.
		Preferences preferences = ConfigurationScope.INSTANCE.getNode(fPluginId);
		preferences.put(key, value);
	}

	protected void putWorkspaceString(String key, String value) {

		value = value.trim();

		// Access the Workspace scope.
		Preferences preferences = InstanceScope.INSTANCE.getNode(fPluginId);
		preferences.put(key, value);
	}

	public void flush() {

		try {
			ConfigurationScope.INSTANCE.getNode(fPluginId).flush();
			InstanceScope.INSTANCE.getNode(fPluginId).flush();
		} catch (BackingStoreException e) {
			;
		}
	}

	protected void putProjectString(String key, String value, IProject project) {

		value = value.trim();

		// Access the project scope.
		Preferences preferences = new ProjectScope(project).getNode(fPluginId);
		preferences.put(key, value);
	}

	public void flush(IProject project) {

		try {
			new ProjectScope(project).getNode(fPluginId).flush();
		} catch (BackingStoreException e) {
			;
		}
	}

	// ------------------------------------------------------------------------
}
