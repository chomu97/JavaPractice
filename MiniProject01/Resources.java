package org.comstudy.homework;

import java.util.Random;
import java.util.Scanner;

public interface Resources {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	DynamicAccountArr managers = new DynamicAccountArr();
	DynamicAccountArr users = new DynamicAccountArr();
	String[] managerViewItems = {"��ȸ","����","���� �߼�","�α׾ƿ�"};
	String[] userViewItems = {"��ȸ","���� ����","�α׾ƿ�"};
	String[] loginViewItems = {"ȸ������","�α���","����"};
	
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
