package client.models.translator;

public class TRMessageList  implements IModelValid{
	protected TRMessageLine[] lines;

	public TRMessageLine[] getLines() {
		return lines;
	}

	public void setLines(TRMessageLine[] lines) {
		this.lines = lines;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
