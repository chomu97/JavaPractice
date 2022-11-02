package org.comstudy.homework;

class SystemManager implements Resources{
	
	public SystemManager() {
		addDummyAccounts();
		run();
	}
	
	private void run() {
		new PrintWelcome().display();
		while (true) {
			menu.display();
			loginViewArr[SelectMenu.loginMenuNo-1].display();
			if (SelectMenu.loginMenuNo == loginViewItems.length)
				break;
			while (Login.isLogin) {
				menu.display(Login.loginUser);
				if(Login.loginUser instanceof Manager) {
					managerViewArr[SelectMenu.managerMenuNo-1].display();
					if (SelectMenu.managerMenuNo == managerViewItems.length)
						break;
				} else {
					userViewArr[SelectMenu.userMenuNo-1].display();
					if (SelectMenu.userMenuNo == userViewItems.length)
						break;
				}
			}
		}
		
	}
	
	private void addDummyAccounts() {
		for(int i=0; i<100; i++) {
			if(i<5) {
				Manager m = new Manager("DummyManager"+i,"1234");
				managers.add(m);
			}
			User u = new User("DummyUser"+i,"1234");
			int managerIndex = rand.nextInt(managers.getSize());
			u.setManager(managers.arrAccountPointer[managerIndex].getId());
			int randPhone = rand.nextInt(9999);
			u.setPhoneNumber(String.format("010-%04d-%04d",randPhone,randPhone));
			boolean randBoolean = rand.nextBoolean();
			u.setIsApproved(randBoolean);
			randBoolean = rand.nextBoolean();
			u.setYN(randBoolean);
			users.add(u);
		}
	}
}

public class MiniProject01 {

	public static void main(String[] args) {
		/*
		입력, 출력, 검색이 가능하도록
		주소록
		or
		고객 관리 시스템
		번호 고객명 연락처 이메일 직업
		조건은 클래스와 메소드를 최대한 많이 만들어 보세요.
		*/
		new SystemManager();
	}

}
