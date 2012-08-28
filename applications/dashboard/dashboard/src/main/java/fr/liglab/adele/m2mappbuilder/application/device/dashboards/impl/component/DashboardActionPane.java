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
package fr.liglab.adele.m2mappbuilder.application.device.dashboards.impl.component;

import nextapp.echo.app.Extent;
import nextapp.echo.app.ResourceImageReference;
import nextapp.echo.extras.app.layout.AccordionPaneLayoutData;

import fr.liglab.adele.m2mappbuilder.application.Application;
import fr.liglab.adele.icasa.application.device.web.common.impl.BaseHouseApplication;
import fr.liglab.adele.icasa.application.device.web.common.impl.component.ActionPane;
import fr.liglab.adele.icasa.application.device.web.common.util.BundleResourceImageReference;
import org.osgi.framework.Bundle;

import fr.liglab.adele.m2mappbuilder.application.device.dashboards.impl.DashboardApplicationImpl;

/**
 * This Pane contains actions that can be realized by user
 * 
 * @author bourretp
 */
public class DashboardActionPane extends ActionPane implements SelectedApplicationTracker {

	/**
    * 
    */
	private static final long serialVersionUID = 4290465922034559972L;

	public static Extent ICON_SIZE = new Extent(25);

	private DashboardApplicationImpl appInstance;

	//private DashboardDevicePane m_devicePane;

	private ApplicationManagerPane m_appPane;



	public DashboardActionPane(DashboardApplicationImpl appInstance) {
		super(appInstance);
		this.appInstance = appInstance;
		initContent();
	}

	private void updateDeviceTabTitle() {
		/*
		AccordionPaneLayoutData devicePaneLayout = (AccordionPaneLayoutData) m_devicePane.getLayoutData();
		final Application selectedService = getApplicationInstance().getSelectedApplication();
		String titlePostfix;
		if (selectedService == null) {
			titlePostfix = SelectAppPane.UNDEFINED_SERV_NAME;
		} else {
			titlePostfix = selectedService.getName();
		}
		devicePaneLayout.setTitle(getDeviceTabName(titlePostfix));
		m_devicePane.setLayoutData(devicePaneLayout);
		*/
	}


	public void addApplication(Application service) {
		m_appPane.addApplication(service);
	}

	public void removeApplication(Application service) {
		m_appPane.removeApplication(service);
	}


	public void notifySelectedAppChanged(Application oldSelectServ, Application newSelectedServ) {
		updateDeviceTabTitle();
		((DashboardDevicePane)m_devicePane).notifySelectedAppChanged(oldSelectServ, newSelectedServ);
	}

	public void refreshDeviceWidgets() {
		((DashboardDevicePane)m_devicePane).refreshDeviceWidgets();
	}

	
	@Override
   protected void initContent() {
		Bundle bundle = BaseHouseApplication.getBundle();
		m_devicePane = new DashboardDevicePane(this);
		final AccordionPaneLayoutData devicePaneLayout = new AccordionPaneLayoutData();
		devicePaneLayout.setIcon(new BundleResourceImageReference(DashboardDevicePane.DEVICE_IMAGE.getResource(), ICON_SIZE,
		      ICON_SIZE, bundle));
		devicePaneLayout.setTitle(getDeviceTabName(SelectAppPane.UNDEFINED_SERV_NAME));
		m_devicePane.setLayoutData(devicePaneLayout);
		add(m_devicePane);

		// Create the devices status controller pane
		if (appInstance.isAndroid()) {
			final AccordionPaneLayoutData statusPaneLayout = new AccordionPaneLayoutData();
			statusPaneLayout.setIcon(new BundleResourceImageReference(DashboardDevicePane.DEVICE_IMAGE.getResource(), ICON_SIZE,
			      ICON_SIZE, bundle));
			statusPaneLayout.setTitle("Device Details");
			appInstance.getStatusPane().setLayoutData(statusPaneLayout);
			add(appInstance.getStatusPane());
		}

		// Create the application manager pane.
		m_appPane = new ApplicationManagerPane(this);
		final AccordionPaneLayoutData appPaneLayout = new AccordionPaneLayoutData();
		appPaneLayout.setIcon(new BundleResourceImageReference(ApplicationManagerPane.APPLICATION_ICON.getResource(),
		      ICON_SIZE, ICON_SIZE, bundle));
		appPaneLayout.setTitle("Applications");
		m_appPane.setLayoutData(appPaneLayout);
		add(m_appPane);

		setActiveTabIndex(0);

	   
   }
	


}