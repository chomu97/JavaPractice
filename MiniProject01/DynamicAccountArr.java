package org.comstudy.homework;

class DynamicAccountArr {
	private int size;
	int capacity;
	int growthFactor = 2;
	int[] arrPointer = null;
	Account[] arrAccountPointer ;
	
	
	public DynamicAccountArr() {
		this.size = 0;
		this.capacity = this.growthFactor * 1;
		this.arrPointer = new int[this.capacity];
		this.arrAccountPointer = new Account[this.capacity];
	}
	
	public void add(Account newUser) {
		if (size < capacity) {
			arrAccountPointer[size] = newUser;
		} else {
			capacity = size * growthFactor;
			Account[] newArrAccountPointer = new Account[capacity];
			for (int i=0; i<size; i++) {
				newArrAccountPointer[i] = arrAccountPointer[i];
			}
			arrAccountPointer = newArrAccountPointer;
			arrAccountPointer[size] = newUser;
		}
		size += 1;
	}
	
	public int getSize() {
		return size;
	}
	/*
	public void printAccount() {
		System.out.printf("Size : %d, Capacity: %d\n",size,capacity);
		for(int i=0; i<this.arrAccountPointer.length; i++) {
			if (arrAccountPointer[i] instanceof User || arrAccountPointer[i] instanceof Manager) {
				System.out.printf(" [ id : %s, passwd : %s ] ",arrAccountPointer[i].getId(),arrAccountPointer[i].getPassword());
			} else {
				System.out.printf(" [ id : null, passwd : null ] ");				
			}
		}
		System.out.println();
		System.out.println("=============");
		
	}
	
	public static void main(String[] args) {
		DynamicAccountArr a = new DynamicAccountArr();
		a.print();
		a.add(1);
		a.print();
		a.add(14);
		a.print();
		User u1 = new User("안찬혁","123");
		User u2 = new User("김정훈","12345");
		User u3 = new User("ㅇㅇ", "abcd");
		a.add(u1);
		a.printAccount();
		a.add(u2);
		a.add(u3);
		a.printAccount();
		/// DynamicArr을 상속받아서 DynamicUserArr 클래스와 DynamicManagerArr 클래스를 만들어준다.
		// DynamicAccountArr 만들면 될듯?? --> 이러면 하나만 만들면 됨!
		
	}*/
}
