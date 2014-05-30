package server.commands;

import java.lang.reflect.InvocationTargetException;

public interface ICommand
{
	/**
	 * Executes from the facade and manipulates the appropriate model classes.
	 * @return string to indicate the change in the model was successful
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public String execute() throws NoSuchMethodException,
								   SecurityException,
								   IllegalAccessException,
								   IllegalArgumentException,
								   InvocationTargetException;
}
