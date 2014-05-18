package client.models;

import java.util.HashMap;
import java.util.Map;

import client.models.translator.TRResourceList;
import shared.definitions.ResourceType;

public class ResourceCollection {
	

	private Integer wood;
	private Integer ore;
	private Integer wheat;
	private Integer brick;
	private Integer sheep;
	
	private IProxy proxy;
	
	public ResourceCollection(IProxy proxy){
		this.proxy = proxy;
		this.wood = 0;
		this.sheep = 0;
		this.ore = 0;
		this.brick = 0;
		this.wheat = 0;
	}
	
	
	public void increaseResourceAmount(ResourceType resource) {
		switch(resource){
		case BRICK:
			if(this.proxy.getFacade().getPlayerResourceCount(resource) > this.brick)
				this.brick++;
			break;
		case ORE:
			if(this.proxy.getFacade().getPlayerResourceCount(resource) > this.ore)
				this.ore++;
			break;
		case SHEEP:
			if(this.proxy.getFacade().getPlayerResourceCount(resource) > this.sheep)
				this.sheep++;
			break;
		case WHEAT:
			if(this.proxy.getFacade().getPlayerResourceCount(resource) > this.wheat)
				this.wheat++;
			break;
		case WOOD:
			if(this.proxy.getFacade().getPlayerResourceCount(resource) > this.wood)
				this.wood++;
			break;
		}
	}
	
	public void decreaseResourceAmount(ResourceType resource) {
		switch(resource){
		case BRICK:
			if(this.brick > 0)
				this.brick--;
			break;
		case ORE:
			if(this.ore > 0)
				this.ore--;
			break;
		case SHEEP:
			if(this.sheep > 0)
				this.sheep--;
			break;
		case WHEAT:
			if(this.wheat > 0)
				this.wheat--;
			break;
		case WOOD:
			if(this.wood > 0)
				this.wood--;
			break;
		}
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResourceCollection [wood=");
		builder.append(wood);
		builder.append(", ore=");
		builder.append(ore);
		builder.append(", wheat=");
		builder.append(wheat);
		builder.append(", brick=");
		builder.append(brick);
		builder.append(", sheep=");
		builder.append(sheep);
		builder.append(", proxy=");
		builder.append(proxy);
		builder.append("]");
		return builder.toString();
	}


	public void setResourceToZero(ResourceType resource) {
		switch(resource){
		case BRICK:
			this.brick = 0;
			break;
		case ORE:
			this.ore = 0;
			break;
		case SHEEP:
			this.sheep = 0;
			break;
		case WHEAT:
			this.wheat = 0;
			break;
		case WOOD:
			this.wood = 0;
			break;
		}
	}


	public Map<IResourceCard, Integer> getResourceList() {
		Map<IResourceCard, Integer> r = new HashMap<IResourceCard, Integer>();
		r.put(ResourceCard.BRICK, this.brick);
		r.put(ResourceCard.ORE, this.ore);
		r.put(ResourceCard.SHEEP, this.sheep);
		r.put(ResourceCard.WHEAT, this.wheat);
		r.put(ResourceCard.WOOD, this.wood);
		return r;
	}


	public static TRResourceList getOffer(ResourceCollection sendCounts, ResourceCollection recieveCounts) {
		TRResourceList l = new TRResourceList();
		l.setBrick(0);
		if(sendCounts.getBrick() > 0)
			l.setBrick(sendCounts.getBrick() * -1);
		else
			l.setBrick(recieveCounts.getBrick());
		
		l.setOre(0);
		if(sendCounts.getOre() > 0)
			l.setOre(sendCounts.getOre() * -1);
		else
			l.setOre(recieveCounts.getOre());
		
		l.setWheat(0);
		if(sendCounts.getWheat() > 0)
			l.setWheat(sendCounts.getWheat() * -1);
		else
			l.setWheat(recieveCounts.getWheat());
		
		l.setSheep(0);
		if(sendCounts.getSheep() > 0)
			l.setSheep(sendCounts.getSheep() * -1);
		else
			l.setSheep(recieveCounts.getSheep());
		
		l.setWood(0);
		if(sendCounts.getWood() > 0)
			l.setWood(sendCounts.getWood() * -1);
		else
			l.setWood(recieveCounts.getWood());
		return l;
	}


	public Integer getWood() {
		return wood;
	}


	public void setWood(Integer wood) {
		this.wood = wood;
	}


	public Integer getOre() {
		return ore;
	}


	public void setOre(Integer ore) {
		this.ore = ore;
	}


	public Integer getWheat() {
		return wheat;
	}


	public void setWheat(Integer wheat) {
		this.wheat = wheat;
	}


	public Integer getBrick() {
		return brick;
	}


	public void setBrick(Integer brick) {
		this.brick = brick;
	}


	public Integer getSheep() {
		return sheep;
	}


	public void setSheep(Integer sheep) {
		this.sheep = sheep;
	}
}
























