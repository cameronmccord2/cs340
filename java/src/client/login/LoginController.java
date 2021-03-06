package client.login;

import client.base.Controller;
import client.base.IAction;
import client.misc.IMessageView;
import client.models.interfaces.IProxy;
import client.server.UserCommandParams;


/**
 * Implementation for the login controller
 */
//@SuppressWarnings({"unused"})
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	private IProxy proxy;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView, IProxy proxy) {
		super(view);
		this.proxy = proxy;
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		UserCommandParams user = new UserCommandParams(((ILoginView) super.getView()).getLoginUsername(), ((ILoginView) super.getView()).getLoginPassword());
		if(proxy.postUserLogin(user).getJson().equals("Success")){
			// If log in succeeded
			if(getLoginView().isModalShowing())
				getLoginView().closeModal();
			loginAction.execute();
		}
		else{
			messageView.setTitle("Login Error");
			messageView.setMessage("Login failed. Bad username or password.");
			messageView.showModal();
		}
			
	}

	@Override
	public void register() {
		UserCommandParams user = new UserCommandParams(((ILoginView) super.getView()).getRegisterUsername(), ((ILoginView) super.getView()).getRegisterPassword());
		if(proxy.postUserRegister(user).getJson().equals("Success")){
			// If register succeeded, log them in
			proxy.postUserLogin(user);
			getLoginView().closeModal();
			loginAction.execute();
		}
	}
}

