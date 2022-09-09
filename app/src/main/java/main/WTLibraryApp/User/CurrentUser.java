package main.WTLibraryApp.User;

//defines the user of the current page visiting, necessary for 
//transferring reservation data to the user interface
public class CurrentUser {
	
	private static long currentUserId;

	public static long getCurrentUserId() { 
		return currentUserId;
	}

	public static void setCurrentUserId(long currentUserId) {
		CurrentUser.currentUserId = currentUserId;
	}
	
	

}
