package client.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageList  implements Serializable {
	protected List<MessageLine> lines;
	
	public MessageList(){
		this.lines = new ArrayList<MessageLine>();
	}

	public List<MessageLine> getLines() {
		return lines;
	}

	public void setLines(List<MessageLine> lines) {
		this.lines = lines;
	}

	public void addLine(MessageLine line) {
		this.lines.add(line);
	}

	
}
