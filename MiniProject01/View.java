package org.comstudy.homework;

import java.util.InputMismatchException;

class View implements Resources{
	int space = 50;
	public void display() {
		
	}
	public void display(Account a) {
		
	}
	public boolean checkUserType(Account type) {
		if (type instanceof User) return false;
		else return true;
	}
	
	public void printDash() {
		for (int i=0; i<space; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	public void printOneLine(String printString) {
		int size = getStringSize(printString);
		int startIndex = space/2 - size/2 -1;
		int endIndex = startIndex + size;
		boolean isContinue = false;
		for(int i=0; i<space;i++) {
			if (i<startIndex) System.out.print("-");
			else if( i>=endIndex) System.out.print("-");
			else {
				if (!isContinue) {
					System.out.print(printString);
					isContinue=true;
				}
			}
		}
		System.out.println();
	}
	public void printUserInfo(String printString) {
		System.out.print("-----");
		int size = getStringSize(printString);
		System.out.print(printString);
		if (size<space-5) {
			for(int i=0; i<space - size - 5;i++) {
				System.out.print("-");
			}
		}
		System.out.println();
	}
	
	private int getStringSize(String printString) {
		String pattern ="[°¡-ÆR¤¡-¤¾¤¿-¤Ó]";
		//String oneSizepattern = "[A-z1-9 :,.'!\\-]";
		double count = 0;
		for (int i=0; i<printString.length(); i++) {
			if ((Character.toString(printString.charAt(i))).matches(pattern))
				count += 4.0/3.0;
			else
				count += 1;
		}
		return (int)count;
	}
	
	public int validateInputInteger(String printString,int minIntegerSize, int maxIntegerSize) {
		int input = 0;
		while (true) {
			try{
				printOneLine(printString);
				System.out.print("----->>");
				input = scan.nextInt();
				if (!(input <= maxIntegerSize && input >=minIntegerSize)) {
					printOneLine("ERROR :: ¿Ã¹Ù¸¥ ¹üÀ§ ³»ÀÇ Á¤¼ö¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä!!");
					continue;
				}
				break;
			} catch(InputMismatchException e) {
				printOneLine("ERROR :: Á¤¼ö¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä!!");
				scan.next();
			}
		}
		return input;
	}
	
	public String validateInputIDPW(String printString) {
		String idpwPattern = "[a-zA-Z0-9]*";
		return validatePattern(idpwPattern, printString);
	}
	
	public String validateInputPhoneNumber(String printString) {
		String phoneNumberPattern = "[0-9]{3}-[0-9]{3,4}-[0-9]{3,4}";
		return validatePattern(phoneNumberPattern, printString);
	}
	public String validateInputYN(String printString) {
		String ynPattern = "[yn]";
		return validatePattern(ynPattern, printString);
	}
	
	private String validatePattern(String pattern, String printString) {
		String input = "";
		while (true) {
			printOneLine(printString);
			System.out.print("----->>");
			input = scan.next();
			if (input.matches(pattern)) break;
			else {
				printOneLine("ERROR :: ¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä!");
			}
		}
		return input;
	}
	
	public void validateInputMessage(String printString) {
		printOneLine(printString);
		printDash();
		String input = "";
		String message = "";
		while(true) {
			input = scan.nextLine();
			if (input.equals("³¡"))
				break;
			message += printMessage(input);
		}
		printOneLine("::: ¹®ÀÚ ³»¿ëÀÔ´Ï´Ù :::");
		System.out.print(message);
		printDash();
	}
	
	private String printMessage(String message) {
		int size = getStringSize(message);
		int startIndex = space/2 - size/2 -1;
		int endIndex = startIndex + size;
		boolean isContinue = false;
		String output = "|";
		for(int i=1; i<space-5;i++) {
			if (i<startIndex) output += " ";
			else if( i>=endIndex) output += " ";
			else {
				if (!isContinue) {
					output += message;
					isContinue=true;
				}
			}
		}
		output += "\t|\n";
		return output;
	}
}

class SelectMenu extends View{
	// manager, userÀÎÁö¿¡ µû¶ó ´Ù¸£°Ô Ãâ·ÂÇØ¾ßÇÔ.
	// manager : Á¶È¸, ½ÂÀÎ, ¹ß¼Û, ·Î±×¾Æ¿ô
	// user : Á¶È¸, Á¤º¸ º¯°æ, ·Î±×¾Æ¿ô
	public static int loginMenuNo = 0;
	public static int managerMenuNo = 0;
	public static int userMenuNo=0;
	private String input = "";
	
	public void display(Account a) {
		if (checkUserType(a)) {
			// managerÀÌ¸é.
			input = showMenu(managerViewItems);
			managerMenuNo = validateInputInteger(input, 1, managerViewItems.length);
		} else { // user ÀÌ¸é.
			input = showMenu(userViewItems);
			userMenuNo = validateInputInteger(input, 1, userViewItems.length);
		}
	}
	
	public void display() {
		if(Login.loginUser != null) {
			display(Login.loginUser);
		}
		// ¾Æ¹«°Íµµ ¾øÀ¸¸é ·Î±×ÀÎ È­¸é.
		else {
			printOneLine(":::  ¾È³çÇÏ¼¼¿ä  :::");
			input = showMenu(loginViewItems);
			loginMenuNo = validateInputInteger(input, 1, loginViewItems.length);			
		}
	}
	
	public String showMenu(String[] viewItems) {
		String printString = "  ";
		for (int i=0; i<viewItems.length; i++) {
			printString += i+1 +"::"+ viewItems[i]; 
			printString += (i==viewItems.length-1)? "  ":", ";
		}
		return printString;
	}
}
class Register extends View{
	// manager, userÀÎÁö¿¡ µû¶ó ´Ù¸£°Ô Ãâ·ÂÇØ¾ßÇÔ.
	int type = 0;
	String id = "";
	String password = "";
	String key = "";
	String phoneNumber = "";
	
	public void display() {
		printOneLine(" È¸¿ø°¡ÀÔ È­¸é ÀÔ´Ï´Ù. ");
		type = validateInputInteger("  1::¸Å´ÏÀú, 2::À¯Àú  ",1,2);
		if (type==1) {
			printOneLine("  ¸Å´ÏÀú È¸¿ø°¡ÀÔ  ");
			id = validateInputIDPW("  id¸¦ ÀÔ·ÂÇÏ¼¼¿ä(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
			while(isExistId(id)) {
				id = validateInputIDPW("  °¡ÀÔµÈ id°¡ ÀÖ½À´Ï´Ù.´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
			}
			password = validateInputIDPW("  ÆÐ½º¿öµå¸¦ ÀÔ·ÂÇÏ¼¼¿ä(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
			key = validateInputIDPW("  ÀÎÁõÅ°¸¦ ÀÔ·ÂÇÏ¼¼¿ä  ");
			Manager newUser = new Manager(id,password);
			if (newUser.validateKey(key)){
				managers.add(newUser);
				printOneLine("  È¸¿ø°¡ÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.  ");
			}
			else {
				printOneLine("  È¸¿ø°¡ÀÔ¿¡ ½ÇÆÐÇÏ¿´½À´Ï´Ù. ÀÎÁõÅ°¸¦ È®ÀÎÇÏ¼¼¿ä.");
			}
			printDash();
		}
		else {
			if(managers.getSize()==0) {
				printOneLine("  ¸Å´ÏÀú°¡ ÇÑ¸íµµ ¾ø½À´Ï´Ù. ¸Å´ÏÀú·Î ¸ÕÀú °¡ÀÔÇØÁÖ¼¼¿ä.  ");
				return;
			}
			printOneLine("  À¯Àú È¸¿ø°¡ÀÔ  ");
			id = validateInputIDPW("  id¸¦ ÀÔ·ÂÇÏ¼¼¿ä(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
			while(isExistId(id)) {
				id = validateInputIDPW("  °¡ÀÔµÈ id°¡ ÀÖ½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
			}
			password = validateInputIDPW("  ÆÐ½º¿öµå¸¦ ÀÔ·ÂÇÏ¼¼¿ä(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
			phoneNumber = validateInputPhoneNumber("  ÇÚµåÆù ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä  ");
			User newUser = new User(id,password);
			newUser.setPhoneNumber(phoneNumber);
			int managerIndex = rand.nextInt(managers.getSize());
			newUser.setManager(managers.arrAccountPointer[managerIndex].getId());
			users.add(newUser);
			printOneLine("  È¸¿ø°¡ÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.  ");
			printDash();
		}
	}
	
	private boolean isExistId(String id) {
		for(int i=0; i<managers.getSize(); i++) {
			if (managers.arrAccountPointer[i].getId().equals(id)) {
				return true;
			}
		}
		for(int i=0; i<users.getSize(); i++) {
			if (users.arrAccountPointer[i].getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
}

class Login extends View{
	// manager, userÀÎÁö¿¡ µû¶ó ´Ù¸£°Ô Ãâ·ÂÇØ¾ßÇÔ.
	public static boolean isLogin = false;
	int type = 0;
	String id = "";
	String password = "";
	private String key = "";
	public static Account loginUser;
	public void display() {
		if (users.getSize()==0 && managers.getSize()==0) {
			printOneLine("  È¸¿øÀÌ ÇÑ¸íµµ ¾ø½À´Ï´Ù. È¸¿ø°¡ÀÔÀ» ÇØÁÖ¼¼¿ä.  ");
			return;
		}
		printOneLine("  ·Î±×ÀÎ È­¸éÀÔ´Ï´Ù.  ");
		type = validateInputInteger("  1::¸Å´ÏÀú, 2::À¯Àú  ",1,2);
		if (type==1) {
			if(managers.getSize()==0) {
				printOneLine("  ¸Å´ÏÀú°¡ ¾ø½À´Ï´Ù. È¸¿ø°¡ÀÔÀ» ÇØÁÖ¼¼¿ä.  ");
				return;
			}
			printOneLine("  :: MANAGER ::  ");
			userLogin(managers, type);
		} else {
			if(users.getSize()==0) {
				printOneLine("  À¯Àú°¡ ¾ø½À´Ï´Ù. È¸¿ø°¡ÀÔÀ» ÇØÁÖ¼¼¿ä.  ");
				return;
			}
			printOneLine("  :: USER ::  ");
			userLogin(users, type);
		}
		
	}
	
	private void userLogin(DynamicAccountArr accounts, int type) {
		id = validateInputIDPW("  id¸¦ ÀÔ·ÂÇÏ¼¼¿ä(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
		for (int i=0; i<accounts.getSize(); i++) {
			if(accounts.arrAccountPointer[i].getId().equals(id)) {
				key = accounts.arrAccountPointer[i].getPassword();
				loginUser = accounts.arrAccountPointer[i];
			}
		}
		if (key.equals("")) {
			printOneLine("  ÀÔ·ÂÇÏ½Å ¾ÆÀÌµð°¡ ¾ø½À´Ï´Ù.  ");
			isLogin = false;
			return;
		}
		password = validateInputIDPW("  ÆÐ½º¿öµå¸¦ ÀÔ·ÂÇÏ¼¼¿ä(¿µ¾î ´ë¼Ò¹®ÀÚ, ¼ýÀÚ)  ");
		if(key.equals(password)) {
			if (type==1)
				printOneLine(String.format("  ¾È³çÇÏ¼¼¿ä,  %s ¸Å´ÏÀú´Ô  ", id));
			else {
				if (((User) loginUser).getIsApproved())
					printOneLine(String.format("  ¾È³çÇÏ¼¼¿ä,  %s´Ô  ", id));
				else {
					printOneLine("  ´ã´ç ¸Å´ÏÀú°¡ ¾ÆÁ÷ ½ÂÀÎÇÏÁö ¾Ê¾Ò½À´Ï´Ù.  ");
					loginUser = null;
					isLogin = false;
					return;
				}
			}
			isLogin = true;
		}
		else {
			printOneLine("  ºñ¹Ð¹øÈ£°¡ ¸ÂÁö ¾Ê½À´Ï´Ù. ´Ù½Ã ·Î±×ÀÎÇØÁÖ¼¼¿ä.");
			loginUser = null;
			isLogin = false;
			return;
		}
	}
}

class Print extends View {
	// manager, userÀÎÁö¿¡ µû¶ó ´Ù¸£°Ô Ãâ·ÂÇØ¾ßÇÔ.
	String[] showMenus = {"ÀüÃ¼Á¶È¸","³» ´ã´ç¸¸ Á¶È¸", "°Ë»ö"};
	public void display(Account a) {
		if(checkUserType(a)) {
			printOneLine("::: ¸Å´ÏÀú Á¶È¸ È­¸éÀÔ´Ï´Ù :::");
			printOneLine(menu.showMenu(showMenus));
			int allShow = validateInputInteger(" ¾î¶² À¯Àú¸¦ Á¶È¸ÇÏ½Ã°Ú½À´Ï±î?? ", 1, showMenus.length);
			
			switch(allShow) {
			case 1:
				for(int i=0; i<users.getSize(); i++) {
					User pointer = (User) users.arrAccountPointer[i];
					printUserInfo(String.format("[ À¯Àú¹øÈ£::%d, id::%s, ÇÚµåÆù::%s, ¹®ÀÚ¼ö½Å¿©ºÎ::%s, ´ã´ç¸Å´ÏÀú::%s, °¡ÀÔ½ÂÀÎ¿©ºÎ::%s ]",i, pointer.getId(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager(), pointer.getIsApproved()?"y":"n"));
				}
				break;
			case 2:
				for(int i=0; i<users.getSize(); i++) {
					User pointer = (User) users.arrAccountPointer[i];
					if (pointer.getManager().equals(Login.loginUser.getId()))
						printUserInfo(String.format("[ À¯Àú¹øÈ£::%d, id::%s, ÇÚµåÆù::%s, ¹®ÀÚ¼ö½Å¿©ºÎ::%s, ´ã´ç¸Å´ÏÀú::%s, °¡ÀÔ½ÂÀÎ¿©ºÎ::%s ]",i, pointer.getId(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager(), pointer.getIsApproved()?"y":"n"));
				}
				break;
			case 3:
				String searchUserName = validateInputIDPW("  °Ë»öÇÒ ¾ÆÀÌµð¸¦ ÀÔ·ÂÇÏ¼¼¿ä  ");
				for(int i=0; i<users.getSize(); i++) {
					User pointer = (User) users.arrAccountPointer[i];
					if (pointer.getId().equals(searchUserName)) {
						printUserInfo(String.format("[ À¯Àú¹øÈ£::%d, id::%s, ÇÚµåÆù::%s, ¹®ÀÚ¼ö½Å¿©ºÎ::%s, ´ã´ç¸Å´ÏÀú::%s, °¡ÀÔ½ÂÀÎ¿©ºÎ::%s ]",i, pointer.getId(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager(), pointer.getIsApproved()?"y":"n"));
						return;
					}
				}
				printOneLine("  Ã£À¸½Ã´Â À¯Àú°¡ ¾ø½À´Ï´Ù.  ");
				break;
			}
		}
		else {
			printOneLine("::: À¯Àú Á¶È¸ È­¸éÀÔ´Ï´Ù :::");
			User pointer = (User)a;
			printUserInfo(String.format("[ id::%s, pw::%s, ÇÚµåÆù::%s, ¹®ÀÚ¼ö½Å¿©ºÎ::%S, ´ã´ç¸Å´ÏÀú::%s ]", pointer.getId(),pointer.getPassword(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager()));
		}
	}
	public void display() {
		display(Login.loginUser);
	}
}

class Approve extends View{
	// manager°¡ È¸¿ø°¡ÀÔ ½ÂÀÎ ½Ã Ãâ·ÂÇÒ È­¸é
	public void display() {
		printOneLine("::: °¡ÀÔ½ÂÀÎ È­¸éÀÔ´Ï´Ù :::");
		for(int i=0; i<users.getSize(); i++) {
			User pointer = (User)users.arrAccountPointer[i]; 
			if (!pointer.getIsApproved() && Login.loginUser.getId().equals(pointer.getManager())){
				printUserInfo(String.format("[ À¯Àú¹øÈ£::%d, id::%s, ÇÚµåÆù::%s ]",i,pointer.getId(),pointer.getPhoneNumber()));
			}
		}
		String approve = validateInputYN("  ÀüºÎ ½ÂÀÎÇÏ½Ã°Ú½À´Ï±î?? (y/n)  ");
		if (approve.equals("y")) {
			printOneLine("  ÀüºÎ ½ÂÀÎµÇ¾ú½À´Ï´Ù..  ");
			for(int i=0; i<users.getSize(); i++) {
				User pointer = (User)users.arrAccountPointer[i]; 
				if (!pointer.getIsApproved() && Login.loginUser.getId().equals(pointer.getManager()))
					pointer.setIsApproved(true);
			}
		} else {
			printOneLine("  ½ÂÀÎÀÌ °ÅÀýµÇ¾ú½À´Ï´Ù..  ");
		}
	}
}

class Send extends View{
	// manager°¡ ¹®ÀÚ ¹ß¼Û½Ã Ãâ·ÂÇÒ È­¸é
	public void display() {
		printOneLine("::: ¹®ÀÚ¹ß¼Û È­¸éÀÔ´Ï´Ù :::");
		validateInputMessage("  º¸³»½Ç ³»¿ëÀ» ÀÔ·ÂÇÏ¼¼¿ä.(³¡ ÀÔ·Â½Ã Á¾·á)  ");
		for (int i=0; i<users.getSize(); i++) {
			User pointer = (User)users.arrAccountPointer[i];
			if(pointer.getIsApproved() && pointer.getYN().equals("Çã¿ë") && pointer.getManager().equals(Login.loginUser.getId())) {
				printUserInfo(String.format("%d¹ø, %s¿¡°Ô %s ¹øÈ£·Î ¹®ÀÚ°¡ ¹ß¼ÛµÇ¾ú½À´Ï´Ù.", i, pointer.getId(),pointer.getPhoneNumber()));
			}
		}
	}
}

class ChangeInfo extends View{
	// user°¡ Á¤º¸º¯°æ½Ã Ãâ·ÂÇÒ È­¸é
	String[] changeInfos = {"ÀüÈ­¹øÈ£","¹®ÀÚ¼ö½Å¿©ºÎ"};
	public void display() {
		printOneLine("::: Á¤º¸º¯°æ È­¸éÀÔ´Ï´Ù :::");
		printOneLine(menu.showMenu(changeInfos));
		int input = validateInputInteger("  ¾î¶² Á¤º¸¸¦ º¯°æÇÏ½Ã°Ú½À´Ï±î?  ", 1, 2);
		switch (input) {
		case 1:
			String phoneNumber = validateInputPhoneNumber("  º¯°æÇÒ ÈÞ´ëÆù ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä  ");
			((User)Login.loginUser).setPhoneNumber(phoneNumber);
			break;
		case 2:
			String yn = validateInputYN("  ¹®ÀÚ ¼ö½Å ¿©ºÎ¸¦ ¼±ÅÃÇÏ¼¼¿ä(y/n)  ");
			((User)Login.loginUser).setYN(yn.equals("y"));
			break;
		}
		printOneLine("  Á¤º¸°¡ º¯°æµÇ¾ú½À´Ï´Ù..  ");
		
	}
}

class LogOut extends View{
	public void display() {
		printOneLine(":::  ÀÌŸGÇØÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù  :::");
		Login.loginUser = null;
	}
}

class PrintWelcome extends View{
	// Ã¹È­¸é Ãâ·Â
	
	int startIndex = 24-7;
	String title = "Àç³­ ¹®ÀÚ ¹ß¼Û ½Ã½ºÅÛ";
	// 17(¾Õ) + 15(stringSize) + 18(µÚ) = 50
	boolean isContinue = false;
	public void display() {
		for (int i=0; i<3; i++) {
			if(i==1) {
				printOneLine(title);
			} else printDash();
		}
	}
}

class Bye extends View{
	String title = ":::  ½Ã½ºÅÛÀ» Á¾·áÇÕ´Ï´Ù  :::";
	public void display() {
		for (int i=0; i<3; i++) {
			if(i==1) {
				printOneLine(title);
			} else printDash();
		}
	}
}