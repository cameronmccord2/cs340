package server.commands;

import java.lang.reflect.InvocationTargetException;

public interface ICommand {

	public String execute() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
