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
		String pattern ="[��-�R��-����-��]";
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
					printOneLine("ERROR :: �ùٸ� ���� ���� ������ �Է��� �ּ���!!");
					continue;
				}
				break;
			} catch(InputMismatchException e) {
				printOneLine("ERROR :: ������ �Է��� �ּ���!!");
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
				printOneLine("ERROR :: �ùٸ� �������� �Է����ּ���!");
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
			if (input.equals("��"))
				break;
			message += printMessage(input);
		}
		printOneLine("::: ���� �����Դϴ� :::");
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
	// manager, user������ ���� �ٸ��� ����ؾ���.
	// manager : ��ȸ, ����, �߼�, �α׾ƿ�
	// user : ��ȸ, ���� ����, �α׾ƿ�
	public static int loginMenuNo = 0;
	public static int managerMenuNo = 0;
	public static int userMenuNo=0;
	private String input = "";
	
	public void display(Account a) {
		if (checkUserType(a)) {
			// manager�̸�.
			input = showMenu(managerViewItems);
			managerMenuNo = validateInputInteger(input, 1, managerViewItems.length);
		} else { // user �̸�.
			input = showMenu(userViewItems);
			userMenuNo = validateInputInteger(input, 1, userViewItems.length);
		}
	}
	
	public void display() {
		if(Login.loginUser != null) {
			display(Login.loginUser);
		}
		// �ƹ��͵� ������ �α��� ȭ��.
		else {
			printOneLine(":::  �ȳ��ϼ���  :::");
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
	// manager, user������ ���� �ٸ��� ����ؾ���.
	int type = 0;
	String id = "";
	String password = "";
	String key = "";
	String phoneNumber = "";
	
	public void display() {
		printOneLine(" ȸ������ ȭ�� �Դϴ�. ");
		type = validateInputInteger("  1::�Ŵ���, 2::����  ",1,2);
		if (type==1) {
			printOneLine("  �Ŵ��� ȸ������  ");
			id = validateInputIDPW("  id�� �Է��ϼ���(���� ��ҹ���, ����)  ");
			while(isExistId(id)) {
				id = validateInputIDPW("  ���Ե� id�� �ֽ��ϴ�.�ٽ� �Է��ϼ���.(���� ��ҹ���, ����)  ");
			}
			password = validateInputIDPW("  �н����带 �Է��ϼ���(���� ��ҹ���, ����)  ");
			key = validateInputIDPW("  ����Ű�� �Է��ϼ���  ");
			Manager newUser = new Manager(id,password);
			if (newUser.validateKey(key)){
				managers.add(newUser);
				printOneLine("  ȸ�������� �Ϸ�Ǿ����ϴ�.  ");
			}
			else {
				printOneLine("  ȸ�����Կ� �����Ͽ����ϴ�. ����Ű�� Ȯ���ϼ���.");
			}
			printDash();
		}
		else {
			if(managers.getSize()==0) {
				printOneLine("  �Ŵ����� �Ѹ� �����ϴ�. �Ŵ����� ���� �������ּ���.  ");
				return;
			}
			printOneLine("  ���� ȸ������  ");
			id = validateInputIDPW("  id�� �Է��ϼ���(���� ��ҹ���, ����)  ");
			while(isExistId(id)) {
				id = validateInputIDPW("  ���Ե� id�� �ֽ��ϴ�. �ٽ� �Է��ϼ���.(���� ��ҹ���, ����)  ");
			}
			password = validateInputIDPW("  �н����带 �Է��ϼ���(���� ��ҹ���, ����)  ");
			phoneNumber = validateInputPhoneNumber("  �ڵ��� ��ȣ�� �Է��ϼ���  ");
			User newUser = new User(id,password);
			newUser.setPhoneNumber(phoneNumber);
			int managerIndex = rand.nextInt(managers.getSize());
			newUser.setManager(managers.arrAccountPointer[managerIndex].getId());
			users.add(newUser);
			printOneLine("  ȸ�������� �Ϸ�Ǿ����ϴ�.  ");
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
	// manager, user������ ���� �ٸ��� ����ؾ���.
	public static boolean isLogin = false;
	int type = 0;
	String id = "";
	String password = "";
	private String key = "";
	public static Account loginUser;
	public void display() {
		if (users.getSize()==0 && managers.getSize()==0) {
			printOneLine("  ȸ���� �Ѹ� �����ϴ�. ȸ�������� ���ּ���.  ");
			return;
		}
		printOneLine("  �α��� ȭ���Դϴ�.  ");
		type = validateInputInteger("  1::�Ŵ���, 2::����  ",1,2);
		if (type==1) {
			if(managers.getSize()==0) {
				printOneLine("  �Ŵ����� �����ϴ�. ȸ�������� ���ּ���.  ");
				return;
			}
			printOneLine("  :: MANAGER ::  ");
			userLogin(managers, type);
		} else {
			if(users.getSize()==0) {
				printOneLine("  ������ �����ϴ�. ȸ�������� ���ּ���.  ");
				return;
			}
			printOneLine("  :: USER ::  ");
			userLogin(users, type);
		}
		
	}
	
	private void userLogin(DynamicAccountArr accounts, int type) {
		id = validateInputIDPW("  id�� �Է��ϼ���(���� ��ҹ���, ����)  ");
		for (int i=0; i<accounts.getSize(); i++) {
			if(accounts.arrAccountPointer[i].getId().equals(id)) {
				key = accounts.arrAccountPointer[i].getPassword();
				loginUser = accounts.arrAccountPointer[i];
			}
		}
		if (key.equals("")) {
			printOneLine("  �Է��Ͻ� ���̵� �����ϴ�.  ");
			isLogin = false;
			return;
		}
		password = validateInputIDPW("  �н����带 �Է��ϼ���(���� ��ҹ���, ����)  ");
		if(key.equals(password)) {
			if (type==1)
				printOneLine(String.format("  �ȳ��ϼ���,  %s �Ŵ�����  ", id));
			else {
				if (((User) loginUser).getIsApproved())
					printOneLine(String.format("  �ȳ��ϼ���,  %s��  ", id));
				else {
					printOneLine("  ��� �Ŵ����� ���� �������� �ʾҽ��ϴ�.  ");
					loginUser = null;
					isLogin = false;
					return;
				}
			}
			isLogin = true;
		}
		else {
			printOneLine("  ��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� �α������ּ���.");
			loginUser = null;
			isLogin = false;
			return;
		}
	}
}

class Print extends View {
	// manager, user������ ���� �ٸ��� ����ؾ���.
	String[] showMenus = {"��ü��ȸ","�� ��縸 ��ȸ", "�˻�"};
	public void display(Account a) {
		if(checkUserType(a)) {
			printOneLine("::: �Ŵ��� ��ȸ ȭ���Դϴ� :::");
			printOneLine(menu.showMenu(showMenus));
			int allShow = validateInputInteger(" � ������ ��ȸ�Ͻðڽ��ϱ�?? ", 1, showMenus.length);
			
			switch(allShow) {
			case 1:
				for(int i=0; i<users.getSize(); i++) {
					User pointer = (User) users.arrAccountPointer[i];
					printUserInfo(String.format("[ ������ȣ::%d, id::%s, �ڵ���::%s, ���ڼ��ſ���::%s, ���Ŵ���::%s, ���Խ��ο���::%s ]",i, pointer.getId(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager(), pointer.getIsApproved()?"y":"n"));
				}
				break;
			case 2:
				for(int i=0; i<users.getSize(); i++) {
					User pointer = (User) users.arrAccountPointer[i];
					if (pointer.getManager().equals(Login.loginUser.getId()))
						printUserInfo(String.format("[ ������ȣ::%d, id::%s, �ڵ���::%s, ���ڼ��ſ���::%s, ���Ŵ���::%s, ���Խ��ο���::%s ]",i, pointer.getId(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager(), pointer.getIsApproved()?"y":"n"));
				}
				break;
			case 3:
				String searchUserName = validateInputIDPW("  �˻��� ���̵� �Է��ϼ���  ");
				for(int i=0; i<users.getSize(); i++) {
					User pointer = (User) users.arrAccountPointer[i];
					if (pointer.getId().equals(searchUserName)) {
						printUserInfo(String.format("[ ������ȣ::%d, id::%s, �ڵ���::%s, ���ڼ��ſ���::%s, ���Ŵ���::%s, ���Խ��ο���::%s ]",i, pointer.getId(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager(), pointer.getIsApproved()?"y":"n"));
						return;
					}
				}
				printOneLine("  ã���ô� ������ �����ϴ�.  ");
				break;
			}
		}
		else {
			printOneLine("::: ���� ��ȸ ȭ���Դϴ� :::");
			User pointer = (User)a;
			printUserInfo(String.format("[ id::%s, pw::%s, �ڵ���::%s, ���ڼ��ſ���::%S, ���Ŵ���::%s ]", pointer.getId(),pointer.getPassword(), pointer.getPhoneNumber(),pointer.getYN(), pointer.getManager()));
		}
	}
	public void display() {
		display(Login.loginUser);
	}
}

class Approve extends View{
	// manager�� ȸ������ ���� �� ����� ȭ��
	public void display() {
		printOneLine("::: ���Խ��� ȭ���Դϴ� :::");
		for(int i=0; i<users.getSize(); i++) {
			User pointer = (User)users.arrAccountPointer[i]; 
			if (!pointer.getIsApproved() && Login.loginUser.getId().equals(pointer.getManager())){
				printUserInfo(String.format("[ ������ȣ::%d, id::%s, �ڵ���::%s ]",i,pointer.getId(),pointer.getPhoneNumber()));
			}
		}
		String approve = validateInputYN("  ���� �����Ͻðڽ��ϱ�?? (y/n)  ");
		if (approve.equals("y")) {
			printOneLine("  ���� ���εǾ����ϴ�..  ");
			for(int i=0; i<users.getSize(); i++) {
				User pointer = (User)users.arrAccountPointer[i]; 
				if (!pointer.getIsApproved() && Login.loginUser.getId().equals(pointer.getManager()))
					pointer.setIsApproved(true);
			}
		} else {
			printOneLine("  ������ �����Ǿ����ϴ�..  ");
		}
	}
}

class Send extends View{
	// manager�� ���� �߼۽� ����� ȭ��
	public void display() {
		printOneLine("::: ���ڹ߼� ȭ���Դϴ� :::");
		validateInputMessage("  ������ ������ �Է��ϼ���.(�� �Է½� ����)  ");
		for (int i=0; i<users.getSize(); i++) {
			User pointer = (User)users.arrAccountPointer[i];
			if(pointer.getIsApproved() && pointer.getYN().equals("���") && pointer.getManager().equals(Login.loginUser.getId())) {
				printUserInfo(String.format("%d��, %s���� %s ��ȣ�� ���ڰ� �߼۵Ǿ����ϴ�.", i, pointer.getId(),pointer.getPhoneNumber()));
			}
		}
	}
}

class ChangeInfo extends View{
	// user�� ��������� ����� ȭ��
	String[] changeInfos = {"��ȭ��ȣ","���ڼ��ſ���"};
	public void display() {
		printOneLine("::: �������� ȭ���Դϴ� :::");
		printOneLine(menu.showMenu(changeInfos));
		int input = validateInputInteger("  � ������ �����Ͻðڽ��ϱ�?  ", 1, 2);
		switch (input) {
		case 1:
			String phoneNumber = validateInputPhoneNumber("  ������ �޴��� ��ȣ�� �Է��ϼ���  ");
			((User)Login.loginUser).setPhoneNumber(phoneNumber);
			break;
		case 2:
			String yn = validateInputYN("  ���� ���� ���θ� �����ϼ���(y/n)  ");
			((User)Login.loginUser).setYN(yn.equals("y"));
			break;
		}
		printOneLine("  ������ ����Ǿ����ϴ�..  ");
		
	}
}

class LogOut extends View{
	public void display() {
		printOneLine(":::  �̟G���ּż� �����մϴ�  :::");
		Login.loginUser = null;
	}
}

class PrintWelcome extends View{
	// ùȭ�� ���
	
	int startIndex = 24-7;
	String title = "�糭 ���� �߼� �ý���";
	// 17(��) + 15(stringSize) + 18(��) = 50
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
	String title = ":::  �ý����� �����մϴ�  :::";
	public void display() {
		for (int i=0; i<3; i++) {
			if(i==1) {
				printOneLine(title);
			} else printDash();
		}
	}
}