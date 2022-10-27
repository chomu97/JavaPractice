package org.comstudy.practice04;

import java.util.InputMismatchException;
import java.util.Scanner;

class Seat{ // bean
	private String name;
	//private int number; // number도 배열 객체로 만들어서 접근할텐데 굳이 필요할까 싶은 생각
	// String type; // 이거는 해줄지 말지 고민해봐야함.

	public Seat() {
		name = "---";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		// 예약 시 호출해주는 메소드
		this.name = name; 
	}
	public void cancel() {
		// 취소 시 호출해주는 메소드
		this.name = "---";
	}
	
}
class Today{ // Data Access Object
	public static final int GRADES = 3;
	public static final int SEATS = 10;
	public static final String[] GRADENAMES = {"S", "A", "B"}; 
	private Seat[][] seatArr = new Seat[GRADES][SEATS];
	private int remain;
	
	public Today() {
		for (int i=0; i<3; i++) {
			for (int j=0; j<10;j++) {
				seatArr[i][j] = new Seat();
			}
		}
		remain = 30;
	}
	
	public void reserve(int grade, int seatNumber, String name) {
		// systemManager에서 예약 시 호출해주는 메소드
		seatNumber = seatNumber - 1;
		seatArr[grade][seatNumber].setName(name);
		remain -= 1;
	}
	
	public boolean isExist(int grade, int seatNumber, String name) {
		if (seatArr[grade][seatNumber].getName().equals(name))
			return true;
		else
			return false;
	}
	
	public String[][] getNames() {
		// systemManager에서 조회 시 호출해주는 메소드
		String[][] names = new String[GRADES][SEATS];
		for (int i=0; i<3; i++) {
			for (int j=0; j<10;j++) {
				names[i][j] = seatArr[i][j].getName();
			}
		}
		return names;
	}
	
	public int getRemain() {
		int sum = 0;
		for (int i=0; i<3; i++) {
			for(int j=0; j<10; j++) {
				if (isExist(i,j,"---"))
					sum += 1;
			}
		}
		return sum;
	}
	
	public int[] cancel(String name) {
		int[] results = {-1, -1};
		for (int i=0; i<3; i++) {
			for(int j=0; j<10; j++) {
				if (isExist(i,j,name)) {
					seatArr[i][j].cancel();
					results[0] = i;
					results[1] = j;
				}
			}
		}
		return results;
	}
	
}

class SystemManager{ // View + Controller 역할
	public static final Scanner scan = new Scanner(System.in);
	Today[] october = new Today[31];
	public SystemManager(){
		for (int i=0; i<31; i++) {
			october[i] = new Today();
		}
	}
	
	private void finish() {
		// 끝내기 선택 시 호출해주는 메소드
		System.out.println("<<<<<<<<<<<<<<<<< 이용해주셔서 감사합니다 >>>>>>>>>>>>>>>>>");
	}
	
	private void showDay(int day) {
		// 날짜 조회 시 호출해주는 메소드
		System.out.printf("<<<<<<<<<<<<<<<< %d일 조회를 완료하였습니다. >>>>>>>>>>>>>>>>\n",day);
		System.out.println("---------------------- 좌석 현황 ----------------------");
		for (int i=0; i<Today.GRADES; i++) {
			showSeats(day, i);
		}
	}
	
	private void showSeats(int day, int grade) {
		String[][] names = october[day-1].getNames();
		System.out.print(Today.GRADENAMES[grade]+" > ");
		for (int i=0; i<Today.SEATS; i++) {
			System.out.print(names[grade][i]+"  ");
		}
		System.out.println();
	}
	
	private void showCalendar() {
		// 조회 시 먼저 호출해주는 메소드
		System.out.println("---------------------- 예약 현황 ----------------------");
		System.out.println("-----------------------------------------------------");
		System.out.printf("| 일 |\t| 월 |\t| 화 |\t| 수 |\t| 목 |\t| 금 |\t| 토 |\n");
		for (int i=0; i<12; i++) {
			for (int j=0; j<7; j++) {
				int idx = (((i<2)?1:i/2)-1)*7 +((i<2)?(j-6):j+1);
				if (i<2 && j<6 || idx>30) {
					System.out.printf(" --- " + ((idx%7==0)?"\n":"\t"));
				}
				else {
					int remain = october[idx].getRemain();
					if (idx%7==0) {
						if ( i%2 == 0 ) System.out.printf((idx<9)?"| %d일|\n":"|%d일|\n",idx+1);
						else System.out.printf((remain>=10)?"|%d석|\n":"| %d석|\n",remain);
					} else {
						if ( i%2 == 0 ) System.out.printf((idx<9)?"| %d일|\t":"|%d일|\t",idx+1);
						else System.out.printf((remain>=10)?"|%d석|\t":"| %d석|\t",remain);
					}
				}				
			}
		}
	}
	
	private void reserveDay(int day) {
		while (october[day-1].getRemain() == 0) {
			day = validateInputInteger("남은 좌석이 없습니다. 다른 날짜를 선택해주세요.(1~31) >> ", 1, 31);
		}
		showDay(day);
		int seatGrade = validateInputInteger("좌석구분 S(1), A(2), B(3) >>>",1,3);
		showSeats(day, seatGrade-1);
		String userName = validateInputKoreanName("이름을 입력하세요 >> ");
		int seatNumber = validateInputInteger("좌석번호를 입력하세요(1~10) >> ",1,10);
		while (!october[day-1].isExist(seatGrade-1,seatNumber-1 , "---")) {
			seatNumber = validateInputInteger("이미 누가 예약한 좌석입니다. 다른 번호를 선택해주세요.(1~10) >> ", 1, 10);
		}
		october[day-1].reserve(seatGrade-1, seatNumber, userName);
		System.out.printf("<<<<<<<< %s님, %d일 %s %d번 좌석 예약이 완료되었습니다. >>>>>>>>\n",userName,day,Today.GRADENAMES[seatGrade-1], seatNumber);
		
	}
	
	private void cancelDay(int day) {
		while (!(october[day-1].getRemain() < 30)) {
			day = validateInputInteger("취소할 좌석이 없습니다. 다른 날짜를 선택해주세요.(1~31) >> ", 1, 31);
		}
		showDay(day);
		
		String userName = validateInputKoreanName("이름을 입력하세요 >> ");
		int[] results = october[day-1].cancel(userName);
		while(results[0] == -1) {
			userName = validateInputKoreanName("찾는 이름이 없습니다. 다른 이름을 입력하세요 >> ");
			results = october[day-1].cancel(userName);
		}
		System.out.printf("<<<<<<<< %s님, %d일 %s %d번 좌석 취소가 완료되었습니다. >>>>>>>>\n",userName,day,Today.GRADENAMES[results[0]], results[1]+1);
	}
	
	private String validateInputKoreanName(String printString) {
		String pattern = "[가-힣]*";
		String input = "";
		while (true) {
			System.out.print(printString);
			input = scan.next();
			if (input.matches(pattern)) break;
			else {
				System.out.println("ERROR :: 한국어 이름을 입력해주세요!");
			}
		}
		return input;
	}
	
	private int validateInputInteger(String printString,int minIntegerSize, int maxIntegerSize) {
		int input = 0;
		while (true) {
			try{
				System.out.print(printString);
				input = scan.nextInt();
				if (!(input <= maxIntegerSize && input >=minIntegerSize)) {
					System.out.println("ERROR :: 올바른 범위 내의 정수를 입력해 주세요!!");
					continue;
				}
				break;
			} catch(InputMismatchException e) {
				System.out.println("ERROR :: 정수를 입력해 주세요!!");
				scan.next();
			}
		}
		return input;
	}
	
	public void run() {
		// systemManager 실행 메소드
		System.out.println("<<<<<<<<<<<<< 명품콘서트홀 10월 예약 시스템입니다 >>>>>>>>>>>>>");
		showCalendar();
		int day = 0;
		int menu = 0;
		
		while(menu != 4) {
			menu = validateInputInteger("예약:1, 조회:2, 취소:3, 끝내기:4 >> ",1,4);
			int count = 0;
			while (menu == 3) {
				for (int i=0; i<31; i++) {
					if (october[i].getRemain() == 30)
						count++;
				}
				if (count == 31)
					menu = validateInputInteger("현재는 취소가 불가능합니다. 다시 입력해주세요.\n예약:1, 조회:2, 취소:3, 끝내기:4 >> ",1,4);
				else
					break;
			}
			
			switch (menu) {
			case 1:
				showCalendar();
				day = validateInputInteger("며칠을 예약하시겠습니까?(1~31) >>> ",1,31);
				reserveDay(day);
				break;
			case 2:
				showCalendar();
				day = validateInputInteger("며칠을 조회하시겠습니까?(1~31) >>> ",1,31);
				showDay(day);
				break;
			case 3:
				showCalendar();
				day = validateInputInteger("며칠을 취소하시겠습니까?(1~31) >>>",1,31);
				cancelDay(day);
				break;
			case 4:
				finish();
				break;
			}
		}
	}
}
public class Chapter04Ex12 {
 
	public static void main(String[] args) {
		new SystemManager().run();

	}

}
