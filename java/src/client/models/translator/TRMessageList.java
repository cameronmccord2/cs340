package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRMessageList  implements IModelValid{
	protected TRMessageLine[] lines;

	public TRMessageLine[] getLines() {
		return lines;
	}

	public void setLines(TRMessageLine[] lines) {
		this.lines = lines;
	}

	@Override
	public void isValid() throws InvalidTranslatorModelException {
		if(lines == null)
			throw new InvalidTranslatorModelException(this.toString());
		for (TRMessageLine line : this.lines) {
			line.isValid();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRMessageList [lines=");
		builder.append(lines);
		builder.append("]");
		return builder.toString();
	}
}
