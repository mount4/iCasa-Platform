/**
 *
 *   Copyright 2011-2012 Universite Joseph Fourier, LIG, ADELE team
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package fr.liglab.adele.m2mappbuilder.application;

import java.util.List;

import org.osgi.framework.Bundle;

/**
 * Represents root access to applications and unique way to install and uninstall applications.
 * 
 * @author Thomas Leveque
 *
 */
public interface ApplicationManager {
	
	/**
	 * Returns ordered list of application categories.
	 * If there is no category, returns an empty list.
	 * 
	 * @return ordered list of application categories.
	 */
	public List<ApplicationCategory> getCategories();
	
	/**
	 * Returns all installed applications.
	 * If there is no installed application, returns an empty list.
	 * 
	 * @return all installed applications.
	 */
	public List<Application> getApplications();

	/**
	 * Returns the specified application.
	 * 
	 * @param applicationId id of requested application
	 * @return the specified application.
	 */
	public Application getApplication(String applicationId);

	/**
	 * Adds an application listener.
	 * 
	 * @param listener an application listener
	 */
	public void addApplicationListener(ApplicationTracker listener);
	
	/**
	 * Removes an application listener.
	 * 
	 * @param listener an application listener
	 */
	public void removeApplicationListener(ApplicationTracker listener);

	/**
	 * Returns application which corresponds to specified bundle.
	 * Returns null if no such application exists.
	 * 
	 * @param bundleSymbolicName bundle symbolic name
	 * @return application which corresponds to specified bundle.
	 */
	public Application getApplicationOfBundle(String bundleSymbolicName);

	/**
	 * Returns true if specified bundle is part of an application.
	 * 
	 * @param bundle a Bundle
	 * @return true if specified bundle is part of an application.
	 */
	public boolean isApplicationBundle(Bundle bundle);
	
	//TODO add advanced request support and installation features
}
