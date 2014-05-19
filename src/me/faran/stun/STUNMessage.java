package me.faran.stun;

import java.util.ArrayList;
import java.util.List;

import me.faran.stun.attr.STUNAttribute;

public abstract class STUNMessage implements STUNElement {
	private STUNHeader requestHeader = new STUNHeader();
	private List<STUNAttribute> attributes = new ArrayList<STUNAttribute>();
	
	public STUNHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(STUNHeader requestHeader) {
		this.requestHeader = requestHeader;
	}
	
	public void addAttribute(STUNAttribute attribute) {
		this.attributes.add(attribute);
	}

	public List<STUNAttribute> getAttributes() {
		return attributes;
	}
}
