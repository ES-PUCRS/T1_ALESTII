package src.main.resources;

public enum Messages {
	

	LINK_BEFORE_NULL("Can not link before on a null reference"),
	LINK_AFTER_NULL("Can not link after on a null reference"),
	CREATE_FIRST("Kandle has being created as the first node");



	private String message;

	Messages(String message){
		this.message = message;
	}

	@Override
	public String toString(){
		return message;
	}
}