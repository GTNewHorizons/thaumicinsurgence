package thaumicinsurgence.item.types;

import thaumicinsurgence.main.utils.LocalizationManager;

public enum ResourceType
{
	TC_DUST_AIR("TCairDust", true),
	TC_DUST_WATER("TCwaterDust", true),
	TC_DUST_FIRE("TCfireDust", true),
	TC_DUST_EARTH("TCearthDust", true),
	TC_DUST_ORDER("TCorderDust", true),
	TC_DUST_CHAOS("TCchaosDust", true),
	
	
	// Dummy items for Thaumanomicon research icons.
	//   These won't ever actually exist in-game, and so they can be moved around.
	RESEARCH_STARTNODE("startNode", false),
	RESEARCH_BEEINFUSION("beeInfusion", false),
	RESEARCH_INSURGENCE("insurgence", false),
	;
	
	private ResourceType(String n, boolean show)
	{
		this.name = n;
		this.showInList = show;
	}
	private String name;
	public boolean showInList;
	
	public void setHidden()
	{
		this.showInList = false;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getLocalizedName()
	{
		return LocalizationManager.getLocalizedString("resource." + this.name);
	}
}
