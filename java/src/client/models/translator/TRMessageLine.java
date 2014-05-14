package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRMessageLine  implements IModelValid{
	protected String message;
	protected String source;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public void isValid() throws InvalidTranslatorModelException {
		if(this.message == null || this.message.length() == 0 || this.source == null || this.source.length() == 0)
			throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRMessageLine [message=");
		builder.append(message);
		builder.append(", source=");
		builder.append(source);
		builder.append("]");
		return builder.toString();
	}
}
