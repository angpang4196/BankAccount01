package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.vo.BankVO;

public class BankService {

	List<BankVO> bankList;
	String strFileName;

	public BankService(String strFileName) {
		bankList = new ArrayList();
		this.strFileName = strFileName;
	}

	public void readFile() {

		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(strFileName);
			buffer = new BufferedReader(fr);

			while (true) {
				String strRead = buffer.readLine();
				if (strRead == null)
					break;
				String[] strSp = strRead.split(":");

				BankVO vo = new BankVO();

				vo.setStrId(strSp[0]);
				vo.setIntBalance(Integer.valueOf(strSp[1]));
				vo.setStrLastDate(strSp[2]);

				bankList.add(vo);

			}
			buffer.close();
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void bankInput() {
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.print("계좌번호를 입력 해 주세요 >>>");
			String strWrite = scan.nextLine();
			int intSize = bankList.size();

			BankVO bankvo = null;
			for (BankVO vo : bankList) {
				if (strWrite.equals(vo.getStrId())) {
					System.out.println("있다");
					bankvo = vo;
					break;
				}
			} // for문 end

			if (bankvo == null) {
				System.out.println("없다");
				return;
			}
			int intBalance = bankvo.getIntBalance();
			System.out.print("입금액을 입력하세요. >>>");
			strWrite = scan.nextLine();
			intBalance += Integer.valueOf(strWrite);
			bankvo.setIntBalance(intBalance);

			String strDate = LocalDate.now().toString(); // 현재 시스템의 날짜를 문자열로 가져오기
			bankvo.setStrLastDate(strDate);

			System.out.println("입금이 완료되었습니다.");
			System.out.println();
			System.out.println(bankvo);
			
			break;

		} // while문 end
	}

	public void bankOutput() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("계좌번호를 입력 해 주세요 >>>");
			String strWrite = scan.nextLine();
			int intSize = bankList.size();

			BankVO bankvo = null;
			for (BankVO vo : bankList) {
				if (strWrite.equals(vo.getStrId())) {
					System.out.println("있다");
					bankvo = vo;
					break;
				}
			} // for문 end

			if (bankvo == null) {
				System.out.println("없다");
				return;
			}
			int intBalance = bankvo.getIntBalance();
			System.out.print("출금액을 입력하세요 >>>");
			strWrite = scan.nextLine();
			if (Integer.valueOf(strWrite) < bankvo.getIntBalance()) {
				System.out.println("");
				intBalance -= Integer.valueOf(strWrite);
				bankvo.setIntBalance(intBalance);

				String strDate = LocalDate.now().toString();
				bankvo.setStrLastDate(strDate);

				System.out.println("출금이 완료되었습니다.");
				System.out.println();
				System.out.println(bankvo);
				
				break;
			} else {
				System.out.println("잔액 부족");
				System.out.println(bankvo);
				break;
			}

		}
	}

	public void viewBalance() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("계좌번호를 입력 해 주세요 >>>");
			String strWrite = scan.nextLine();
			int intSize = bankList.size();

			BankVO bankvo = null;
			for (BankVO vo : bankList) {
				if (strWrite.equals(vo.getStrId())) {
					System.out.println();
					System.out.println("잠시만 기달려주세요");
					System.out.println();
					bankvo = vo;
					break;
				}
			} // for문 end

			if (bankvo == null) {
				System.out.println();
				System.out.println("계좌번호가 일치하지않습니다.");
				System.out.println();
				break;
			}
			System.out.println("====================================================");
			System.out.println("계좌번호\t잔액\t최근거래날짜");
			System.out.println("====================================================");
			System.out.println(
					bankvo.getStrId() + "\t\t" + bankvo.getIntBalance() + "\t" + bankvo.getStrLastDate() + "\t");
			System.out.println("----------------------------------------------------");
			break;
		}
	}

	public void saveInfo() {

		String strFileName = "D:/bizwork/workutf/BankAccount01/src/com/biz/bank/BankBalance01.txt";
		FileWriter fw;
		PrintWriter pw;
		try {
			fw = new FileWriter(strFileName);
			pw = new PrintWriter(fw);

			int intSize = bankList.size();
			for (BankVO bankvo : bankList) {
				pw.println(bankvo.getStrId() + ":" + bankvo.getIntBalance() + ":" + bankvo.getStrLastDate());
			}
			
			pw.close();
			fw.close();
			System.out.println("저장완료");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
