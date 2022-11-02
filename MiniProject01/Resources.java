package org.comstudy.homework;

import java.util.Random;
import java.util.Scanner;

public interface Resources {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	DynamicAccountArr managers = new DynamicAccountArr();
	DynamicAccountArr users = new DynamicAccountArr();
	String[] managerViewItems = {"조회","승인","문자 발송","로그아웃"};
	String[] userViewItems = {"조회","정보 변경","로그아웃"};
	String[] loginViewItems = {"회원가입","로그인","종료"};
	
	SelectMenu menu = new SelectMenu();
	
	Register register = new Register();
	Login login = new Login();
	Bye bye = new Bye();
	View[] loginViewArr = {register, login, bye};
	
	Print print = new Print();
	Approve approve = new Approve();
	Send send = new Send();
	LogOut logout = new LogOut();
	ChangeInfo changeinfo = new ChangeInfo();
	View[] managerViewArr = {print, approve, send, logout};
	View[] userViewArr= {print, changeinfo, logout};
}
