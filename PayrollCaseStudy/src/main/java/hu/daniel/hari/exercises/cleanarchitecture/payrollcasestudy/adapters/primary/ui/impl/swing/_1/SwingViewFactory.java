package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1;

import javax.swing.JFrame;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.UncaugthExceptionDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.mainpanel.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.mainpanel.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.mainpanel.EmployeesTablePanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.mainpanel.PayCheckListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.mainpanel.PayDayPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.uncaugthexception.UncaugthExceptionController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.PayDayController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.messageformatter.StatusBarMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;

public class SwingViewFactory {

	private UseCaseFactories useCaseFactories;
	private EventBus eventBus;
	private ViewLoader viewLoader;
	private JFrame mainFrame = null; //Nullable

	public SwingViewFactory(UseCaseFactories useCaseFactories, EventBus eventBus, ViewLoader viewLoader) {
		this.useCaseFactories = useCaseFactories;
		this.eventBus = eventBus;
		this.viewLoader = viewLoader;
	}

	public MainFrameWindow mainFrameWindow() {
		MainFrameWindow mainFrameWindow = new MainFrameWindow(this);
		MainFrameController controller = new MainFrameController();
		controller.setView(mainFrameWindow);
		setMainFrame(mainFrameWindow);
		return mainFrameWindow;
	}

	public MainPanel mainPanel() {
		MainPanel mainPanel = new MainPanel(this);
		MainPanelController controller = new MainPanelController(mainPanel, viewLoader);
		mainPanel.setListener(controller);
		return mainPanel;
	}
	
	private void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public StatusBarPanel statusBarPanel() {
		StatusBarPanel statusBarPanel = new StatusBarPanel();
		StatusBarController controller = new StatusBarController(eventBus);
		controller.setView(statusBarPanel);
		return statusBarPanel;
	}

	public EmployeeManagerPanel employeeManagerPanel() {
		EmployeeManagerPanel view = new EmployeeManagerPanel(this);
		EmployeeManagerController controller = new EmployeeManagerController(useCaseFactories, useCaseFactories, eventBus);
		controller.setView(view);
		view.setListener(controller);
		return view;
	}
	
	public PayDayPanel payDayPanel() {
		PayCheckListPanel payCheckListPanel = new PayCheckListPanel();
		PayDayPanel payDayPanel = new PayDayPanel(payCheckListPanel);
		PayDayController controller = new PayDayController(useCaseFactories, useCaseFactories);
		controller.setView(payDayPanel);
		payDayPanel.setListener(controller);
		return payDayPanel;
	}

	public EmployeesTablePanel employeesTablePanel() {
		EmployeesTablePanel view = new EmployeesTablePanel();
		EmployeeListController controller = new EmployeeListController(useCaseFactories, eventBus);
		controller.setView(view);
		view.setListener(controller);
		return view;
	}
	
	public AddEmployeeDialog addEmployeeDialog() {
		AddEmployeeDialog dialog = new AddEmployeeDialog(mainFrame);
		AddEmployeeController controller = new AddEmployeeController(dialog, useCaseFactories, eventBus);
		dialog.setListener(controller);
		return dialog;
	}

	public UncaugthExceptionDialog uncaugthExceptionDialog(Throwable e) {
		UncaugthExceptionDialog dialog = new UncaugthExceptionDialog(mainFrame);
		UncaugthExceptionController controller = new UncaugthExceptionController(dialog, e);
		dialog.setListener(controller);
		return dialog;
	}
	
	
}