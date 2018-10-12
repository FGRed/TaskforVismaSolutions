/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskforvisma;

import java.util.ArrayList;
/**
 *
 * @author Niko
 */
public class bank_account_number_class {

					private final String sSimpleBankAccount;

					bank_account_number_class(String sSimpleBankAccount) {
										this.sSimpleBankAccount = sSimpleBankAccount;
					}

					public String fCheckBank(String sSimpleBankAccountNum) {
										//BEGIN BANK SEARCH//
										
										/*Splitting the given input of the user into an array of characters
										*and deducing which bank is in question from the frst char unless the char is 3
										*in which case the deduction happens from the two first chars.*/
										char[] charList = sSimpleBankAccountNum.toCharArray();
										StringBuilder sb = new StringBuilder();
										sb.append(charList[0]);
										String bank = sb.toString();
										switch (bank) {
															case "1":
															case "2":
																				bank = "Nordea(Nordea)";
																				break;
															case "4":
																				bank = "Säästöpankit (Sp) ja paikallisosuuspankit (Pop) sekä Aktia";
																				break;
															case "5":
																				bank = "osuuspankit (Op), OKO and Okopankki";
																				break;
															case "6":
																				bank = "Ålandsbanken ÅAB";
																				break;
															case "7":
																				bank = "Sampo Pankki (Sampo)";
																				break;
															default:
																				if (bank.equals("3")) {
																									sb.append(charList[1]);
																									bank = sb.toString();
																									switch (bank) {
																														case "31":
																																			bank = "Handelsbanken (SHB)";
																																			break;
																														case "33":
																																			bank = "Skandinaviska Enskilda Banken (SEB)";
																																			break;
																														case "34":
																																			bank = "DanskeBank";
																																			break;
																														case "36":
																																			bank = "Tapiola pankki";
																																			break;
																														case "37":
																																			bank = "DnB NOR Bank ASA (DnB NOR)";
																																			break;
																														case "38":
																																			bank = "Swensbank";
																																			break;
																														case "39":
																																			bank = "S-pankki";
																																			break;
																														default:
																																			bank = "No banks found with the initial numbers: : " + bank;
																									}
																				} else {
																									System.out.println("No banks found with the initial numbers: : " + bank);
																				}
																				break;
										}
										//END BANK SEARCH//

										if (bank.contains("No banks found with the initial numbers: " )) {
															System.out.println(bank);
										} else {
															System.out.println(bank + " found.");
										}

										return bank;
					}

					public String fTurnIntoMachineCode(String sSimpleBankAccountNum, String pankki) {
										//BEGIN CONVERT TO MACHINE CODE//
										/*If the bank in question is one mentioned in the switch case, the zeroes
										are added to place 6, otherwise the place is 7
										*/
										String sLongBankAccountNum = "";

										switch (pankki) {
															case "Nordea(Nordea)":
															case "Handelsbanken (SHB)":
															case "DanskeBank":
															case "Tapiola pankki":
															case "DnB NOR Bank ASA (DnB NOR)":
															case "Swensbank":
															case "S-Pankk":
															case "Ålandsbanken ÅAB":
															case "Sampo Pankki (Sampo)":
																				sSimpleBankAccountNum = sSimpleBankAccountNum.replaceAll("-", "");
																				char[] charList2 = sSimpleBankAccountNum.toCharArray();
																				StringBuilder sb = new StringBuilder();

																				for (int i = 0; i < 6; i++) {
																									sb.append(charList2[i]);
																				}
																				int size = sSimpleBankAccountNum.length();
																				while (size < 14) {
																									size++;
																									sb.append("0");

																				}
																				for (int i = 6; i < charList2.length; i++) {
																									sb.append(charList2[i]);
																				}
																				sLongBankAccountNum = sb.toString();
																				break;
															default:
																				sSimpleBankAccountNum = sSimpleBankAccountNum.replaceAll("-", "");
																				char[] charList3 = sSimpleBankAccountNum.toCharArray();
																				StringBuilder sb3 = new StringBuilder();

																				for (int i = 0; i < 7; i++) {
																									sb3.append(charList3[i]);
																				}
																				int size2 = sSimpleBankAccountNum.length();
																				while (size2 < 14) {
																									size2++;
																									sb3.append("0");

																				}
																				for (int i = 7; i < charList3.length; i++) {
																									sb3.append(charList3[i]);
																				}
																				sLongBankAccountNum = sb3.toString();
																				break;

										}

										return sLongBankAccountNum;
										//END CONVERT TO MACHINE CODE
					}

					void checkLastNumt(String sSimpleBankNumber) {
										//BEGIN CALCULATION FOR THE CHECK DIGIT//
										/*The calculation happens as following:
										1. The input is divided into separate integers and added to an array of integers.
										2. Then in a for loop, every other integer is multiplied with 1 and rest with 2 depending on their
										position in the Array. This results in an ArrayList that contains the multiplied results. 
										3. The multiplied results are summed in a for loop that iterates through the ArrayList containing them.
										4. And finally the check digit can be calculated from the sum and the nearest upper ten of the sum by substraction 
										(nearestten-sum = checkdigit) 
										
										*/
										
										int[] intArray = new int[sSimpleBankNumber.length()];
										int i = 0;
										String s = sSimpleBankNumber;
										int strLength = s.length();

										for (i = 0; i < sSimpleBankNumber.length(); i++) {
															if (!Character.isDigit(s.charAt(i))) {
																				System.out.println("Contains an invalid digit");
																				break;
															}
															intArray[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
										}
      
										ArrayList<Integer> multipliers = new ArrayList();
										int multiplier = 0;
										for (int j = 0; j < intArray.length - 1; j++) {
															if (multiplier == 1) {
																				multiplier = 0;
																				multipliers.add(intArray[j] * 1);
															} else {
																				multipliers.add(intArray[j] * 2);
																				multiplier++;
															}

										}

										StringBuilder sb = new StringBuilder();
										for (int j = 0; j < multipliers.size(); j++) {
															sb.append(multipliers.get(j));
										}
										String uusi = sb.toString();
										strLength = uusi.length();
										int[] intArray2 = new int[uusi.length()];
										for (i = 0; i < uusi.length(); i++) {
															intArray2[i] = Integer.parseInt(String.valueOf(uusi.charAt(i)));
										}
										multipliers.clear();
										for (int j = 0; j < intArray2.length; j++) {
															multipliers.add(intArray2[j]);
										}

										int sum = 0;
										int incr = 0;
										for (int j = 0; j < multipliers.size(); j++) {
															sum += multipliers.get(j);
										}
										int nearestten = 0;
										if ((sum % 10) > 0) {

															nearestten = sum + (10 - (sum % 10));

										}
										int check = nearestten - sum;
										System.out.println("Calculated check digit: " + check);
										//END CALCULATION FOR THE CHECK DIGIT
					}
}
