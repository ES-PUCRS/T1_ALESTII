package src.main.resources;

public enum LogMessages {

	

	PACKING_LEVEL("Packing on level: "),
	PACKED_ON("\nThey have being packed into: "),

	CLOSE_LOG("Program runned on "),
	REFERENCE_LIST_GROWING("The reference list got new linkedlist on index: ");



	private String message;

	LogMessages(String message){
		this.message = message;
	}

	@Override
	public String toString(){
		return message;
	}
}