/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskforvisma;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Niko
 */
public class TaskforVisma {

					/**
					 * @param args the command line arguments
					 */
					public static void main(String[] args) {
										Scanner s = new Scanner(System.in);
										String sSimpleBankNumber = "";
										do {
															System.out.println("Input bank number:  ");
															sSimpleBankNumber = s.nextLine();
															sSimpleBankNumber = sSimpleBankNumber.replaceAll("-", "");
															if(sSimpleBankNumber.length() <= 15){
															try {
																				BigInteger iNumTest = new BigInteger(sSimpleBankNumber);
																				bank_account_number_class banc = new bank_account_number_class(sSimpleBankNumber);
																				String MachineCodeBankNumber = banc.fTurnIntoMachineCode(sSimpleBankNumber, banc.fCheckBank(sSimpleBankNumber));
																				System.out.println("Given bank number in machine code: "+MachineCodeBankNumber);
																				banc.checkLastNumt(MachineCodeBankNumber);
																				break;
															} catch (NumberFormatException Ex) {
																				System.out.println("Input contains errors!");
															}}else{
																				System.out.println("The input should be under 14 chars max.");			
															}
										} while (true);
										
										
										

					}

}
