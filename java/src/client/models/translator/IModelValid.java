package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public interface IModelValid {
	public boolean isValid() throws InvalidTranslatorModelException;
}
