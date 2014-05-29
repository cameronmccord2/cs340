package server.commands;

import java.lang.reflect.InvocationTargetException;

public interface ICommand
{
	/**
	 * 
	 * @return
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
