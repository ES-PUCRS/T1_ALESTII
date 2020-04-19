package src.main.resources;

public enum LogMessages {

	PACKING_LEVEL("Packing on level: "),
	PACKED_ON("\nThey have being packed into: "),

	CLOSE_LOG(" - Runned on "),
	REFERENCE_LIST_GROWING("The reference list got new linkedlist on index: "),

	INIT_RUN("Running on file "),
	GAPS_REMOVAL(" remotions has beeing occured ");

	private String message;

	LogMessages(String message){
		this.message = message;
	}

	@Override
	public String toString(){
		return message;
	}
}