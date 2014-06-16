package client.models.translator;

import java.io.Serializable;

import client.models.exceptions.InvalidTranslatorModelException;

public interface IModelValid extends Serializable {
	public void validate() throws InvalidTranslatorModelException;
}
