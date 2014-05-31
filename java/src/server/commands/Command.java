package server.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

/**
 * Command object for the Command Design pattern. Takes in the method name to be executed and the appropriate params
 * @author scottdaly
 *
 */
public abstract class Command implements ICommand, ITestCommand {
	
	protected String methodName;
	protected ICommandParams commandParams;
	protected UserAttributes userAttributes;
	protected IServerModelFacade facade;

	public Command(String methodName, ICommandParams commandParams, UserAttributes userAttributes, IServerModelFacade facade){
		this.methodName = methodName;
		this.commandParams = commandParams;
		this.userAttributes = userAttributes;
		this.facade = facade;
	}
	
	public String execute() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = this.facade.getClass().getMethod(this.methodName, new Class[] {ICommandParams.class, UserAttributes.class});
		String response = (String)method.invoke(this.commandParams, this.userAttributes);
		
		if(response.equals("Success"))
			return this.facade.getJsonGameModelString(this.userAttributes);
		else
			return response;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public UserAttributes getUserAttributes() {
		return userAttributes;
	}

	public void setUserAttributes(UserAttributes userAttributes) {
		this.userAttributes = userAttributes;
	}

	public ICommandParams getCommandParams() {
		return commandParams;
	}

	public void setCommandParams(ICommandParams commandParams) {
		this.commandParams = commandParams;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Command [methodName=");
		builder.append(methodName);
		builder.append(", commandParams=");
		builder.append(commandParams);
		builder.append(", userAttributes=");
		builder.append(userAttributes);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commandParams == null) ? 0 : commandParams.hashCode());
		result = prime * result
				+ ((methodName == null) ? 0 : methodName.hashCode());
		result = prime * result
				+ ((userAttributes == null) ? 0 : userAttributes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (commandParams == null) {
			if (other.commandParams != null)
				return false;
		} else if (!commandParams.equals(other.commandParams))
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (userAttributes == null) {
			if (other.userAttributes != null)
				return false;
		} else if (!userAttributes.equals(other.userAttributes))
			return false;
		return true;
	}

}
