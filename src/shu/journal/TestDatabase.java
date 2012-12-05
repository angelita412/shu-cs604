package shu.journal;

import android.database.Cursor;

public class TestDatabase{
	private String[][] userData = new String[0][10];
	Cursor cursor;
	private Long user_id;
	void fillStringArray(){
		userData[0][0]="tUser";
		userData[0][1]="tPassword";
		userData[0][2]="tlocation";
		userData[0][3]="first";
		userData[0][4]="last";
		userData[0][5]="q1";
		userData[0][6]="q2";
		userData[0][7]= "q3";
		userData[0][8]= "a1";
		userData[0][9]="a2";
		userData[0][10]="a3";
		userData[1][0]="Username";
		userData[1][1]="Password";
		userData[1][2]="location";
		userData[1][3]="F Name";
		userData[1][4]="L Name";
		userData[1][5]="Question 1";
		userData[1][6]="Question 2";
		userData[1][7]="Question3";
		userData[1][8]="Answer1";
		userData[1][9]="Answer2";
		userData[1][10]="Answer3";
	}
	void testAddUser(DBAdapter databaseTest) {
		System.out.println("Testing adding user to the database");
		databaseTest.insertUser("tUser", "tPassword", "tlocation", "first", "last", "q1", "q2", "q3", "a1", "a2", "a3");
		boolean user_exists = databaseTest.checkUserExists("tUser");
		System.out.println("====User tUser exists --> " + user_exists);		
	}
	void testPasswordCheck(DBAdapter databaseTest){
		System.out.println("Testing successful password check");
		Long passwordReturn;
		passwordReturn = databaseTest.checkPassword("tUser", "tPassword");
		if(passwordReturn == -1)
			System.out.println("====Password check failed");
		else
			System.out.println("====Password check successful");
		System.out.println("Testing un-successful password check");
		passwordReturn = databaseTest.checkPassword("tUser", "tPasswords");
		if(passwordReturn == -1)
			System.out.println("====Password check failed");
		else
			System.out.println("====Password check successful");
	}
	void testAccountLockCheck(DBAdapter databaseTest)
	{
		System.out.println("Testing account lock check");
		cursor = databaseTest.getFirstUser();
		boolean accountLocked;
		accountLocked=databaseTest.getLockStatus("tUser");
		if(accountLocked == true)
			System.out.println("====Lock check successful, account is locked");
		else if(accountLocked == false)
			System.out.println("====Lock check successful, account is unlocked");
	}
	void testAddPage(DBAdapter databaseTest){
		System.out.println("Testing adding a journal page");
		cursor = databaseTest.getUserByUsername("tUser");
		databaseTest.insertPage(cursor.getLong(0), "Journal Entry");
		System.out.println("====Page added successfully");
	}
	void testGetPage(DBAdapter databaseTest)
	{
		System.out.println("Testing retrieving a journal page");
		cursor = databaseTest.getUserByUsername("tUser");
		Cursor c = databaseTest.getPageById(cursor.getLong(0));
		if (c.getString(3)=="Journal Entry")
			System.out.println("Page retrieved successfuly -- "+c.getString(3));
		else
			System.out.println("Could not retrieve page -- "+c.getString(3));
	}
	void testModifyPage(DBAdapter databaseTest){
		System.out.println("Testing modifying");
		cursor = databaseTest.getUserByUsername("tUser");
		databaseTest.updatePage(cursor.getLong(1), "Modify Entry");
		System.out.println("Page successfully modified");
	}
	void testDeletePage(DBAdapter databaseTest){
		System.out.println("Testing deleting a journal page");
		cursor = databaseTest.getUserByUsername("tUser");
		databaseTest.deletePage(cursor.getPosition());
		System.out.println("Page successfully deleted");
	}
	void testDeleteUser(DBAdapter databaseTest){
		System.out.println("Testing deleting a user");
		cursor = databaseTest.getUserByUsername("tUser");
		databaseTest.deleteUser("tUser");
		System.out.println("====User successfully deleted");
	}
}
