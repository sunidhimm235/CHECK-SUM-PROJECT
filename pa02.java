import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*=============================================================================
| Assignment: pa02 - Calculating an 8, 16, or 32 bit
| checksum on an ASCII input file
|
| Author: Sunidhi Mistry
| Language: java
|
| To Compile: javac pa02.java
|
| To Execute: java -> java pa02 inputFile.txt 8
| or c++ -> ./pa02 inputFile.txt 8
| or c -> ./pa02 inputFile.txt 8
| or go -> ./pa02 inputFile.txt 8
| or python-> python pa02.py inputFile.txt 8
| where inputFile.txt is an ASCII input file
| and the number 8 could also be 16 or 32
| which are the valid checksum sizes, all
| other values are rejected with an error message
| and program termination
|
| Note: All input files are simple 8 bit ASCII input
|
| Class: CIS3360 - Security in Computing - Fall 2022
| Instructor: McAlpin
| Due Date: per assignment
|
+=============================================================================*/

public class pa02 {
    public static void main(String[] args) throws IOException {

        // convert the above pasted code in C to Java
        // declare the variable to store the name of the file from the command line argument
        String inputfileName;

        // declare the variable to store the checksum bit size
        int checkSumBits;

        // check whether the number of command line arguments are less than 2 then display an error message
        if (args.length < 2) {
            System.out.println("Error! Insufficient arguments are provided at command line.");
        } else { // otherwise, get the arguments from the command line and compute the checksum for the given file.

            // copy the name of the file into inputfileName
            inputfileName = args[0];

            // Read the file and store the contents in a string
            String inpuString = readFile(inputfileName);
            
            System.out.println();
            // Print only 80 letters in one line of input string
            for(int i = 0; i < inpuString.length(); i++) {
                System.out.print(inpuString.charAt(i));

                if(i % 79 == 0 && i > 0) {
                    System.out.println();
                }
            }
            System.out.println();

            // convert the second argument into int and store it in checkSumBits variable
            checkSumBits = Integer.parseInt(args[1]);

            // check whether the value of the checkSumSizes are 8, 16, or 32
            if (checkSumBits != 8 && checkSumBits != 16 && checkSumBits != 32) {
                System.out.println("Valid checksum sizes are 8, 16, or 32");
            }

            System.out.printf("%2s bit checksum is %8s for all %4s chars\n", checkSumBits, checksum(inpuString, checkSumBits), characterCount(inpuString, checkSumBits));

        }

    }

    // Method to read the file and return the contents of the file as a string
    public static String readFile(String fileName) throws IOException {

        // create a file object
        File file = new File(fileName);

        // create a file reader object
        FileReader fileReader = new FileReader(file);

        // create a string buffer to store the contents of the file
        char[] chars = new char[(int) file.length()-1];

        // read the contents of the file and store it in the string buffer
        fileReader.read(chars);

        // convert the string buffer into a string
        String fileContents = new String(chars);

        // close the file reader object
        fileReader.close();
        return fileContents;
    }

    // Method to find the binary representation of the hexadecimal number
    public static String hexToBin(String hex) {

        // create a hashmap to store the hexadecimal values and their corresponding binary values
        HashMap<Character, String> hashMap = new HashMap<Character, String>();
        String bin = "";
        
        // store the hexadecimal values and their corresponding binary values in the hashmap
        hashMap.put('0', "0000"); hashMap.put('1', "0001"); hashMap.put('2', "0010"); hashMap.put('3', "0011");
        hashMap.put('4', "0100"); hashMap.put('5', "0101"); hashMap.put('6', "0110"); hashMap.put('7', "0111");
        hashMap.put('8', "1000"); hashMap.put('9', "1001"); hashMap.put('a', "1010"); hashMap.put('b', "1011");
        hashMap.put('c', "1100"); hashMap.put('d', "1101"); hashMap.put('e', "1110"); hashMap.put('f', "1111");

        // convert the hexadecimal number into binary
        bin += hashMap.get(hex.charAt(0));
        bin += hashMap.get(hex.charAt(1));
        
        return bin;
    }

    // Method to calculate the addition of two binary numbers
    public static String addBin(String s1, String s2, int byt) {
		String out = "";

        // convert the binary numbers into decimal numbers and add them and convert the result into binary
		if(byt == 8 || byt == 16) {
			out = Integer.toBinaryString(Integer.parseInt(s1, 2) + Integer.parseInt(s2, 2));
		} else if(byt == 32) {
			out = Long.toBinaryString(Long.parseLong(s1, 2) + Long.parseLong(s2, 2));
		}

        // check whether the length of the result is more than the checksum bit size
		if(out.length() > byt) { // if yes, then take the last byt bits of the result
			out = out.substring(1);
		}

        // check whether the length of the result is less than the checksum bit size
		while(out.length() < byt) { // if yes, then add 0 at the beginning of the result
			out = "0" + out;
		}

		return out;
	}

    // Method to run the checksum algorithm
    public static String checksum(String inpString, int byt) {
        // Creating string to store result
        String result = "";

        // Switch statement to run the checksum algorithm for 8, 16, or 32 bit checksum
        switch(byt) {
            case 8:
                result = checksum8(inpString, byt);

                if(result.charAt(0) == '0') {
                    // remove 0 from the front
                    result = result.substring(1);
                }

                break;
            case 16:
                result = checksum16(inpString, byt);
                break;
            case 32:
                result = checksum32(inpString, byt);
                break;
        }

        return result;
    }

    // Method to calculate the checksum for 8 bits
    public static String checksum8(String str, int byt) {
        // Declaring variables
        byte[] bytes = str.getBytes();
		String addedBinary = "";
		String A = "00001010";
		String checksum = "";
		String tempChecksum = "";
		String hexInbin[] = new String[bytes.length];

        // Converting the hexadecimal values into binary
        for(int i = 0; i < bytes.length; i++) {
			hexInbin[i] = hexToBin(Integer.toHexString(bytes[i]));
		}

        // Adding the binary values
        addedBinary = hexInbin[0];
        for(int i = 1; i < hexInbin.length; i++) {
            addedBinary = addBin(addedBinary, hexInbin[i], byt);
        }

        System.out.println();

        // Adding the binary values with 00001010
        tempChecksum = addBin(addedBinary, A, byt);

        // Converting the binary value into hexadecimal
        checksum = Integer.toHexString(Integer.parseInt(tempChecksum.substring(0,4), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(4), 2)) ;

        return checksum;
    }

    // Method to calculate the checksum for 16 bits
    public static String checksum16(String str, int byt) {
        // Declaring variables
        byte[] bytes = str.getBytes();
		String addedBinary = "";
		String A = "00001010";
        String X = "01011000";
		String checksum = "";
		String tempChecksum = "";
		String hexInbin[] = new String[bytes.length];

        // Converting the hexadecimal values into binary
        int len = str.length()/2;
        String[] array16 = new String[len];

        for(int i = 0; i < bytes.length; i++) {
			hexInbin[i] = hexToBin(Integer.toHexString(bytes[i]));
		}

        // Creating an array of 16 bits
        int i = 0, j = 0;
        while(i < hexInbin.length-1) {
            array16[j] = hexInbin[i] + hexInbin[i+1];
            j++;
            i+=2;
        }

        // Adding the binary values
        addedBinary = array16[0];
        for(i = 1; i < array16.length; i++) {
            addedBinary = addBin(addedBinary, array16[i], byt);
        }

        // Adding the binary values with 00001010 and 01011000
        if(str.length() % 2 == 0) {
            String str2 = A + X;
            System.out.println("X");
            tempChecksum = addBin(addedBinary, str2, byt);
        } else {
            String str2 = hexInbin[hexInbin.length - 1] + A;
            System.out.println();
            tempChecksum = addBin(addedBinary, str2, byt);
        }
        
        // Converting the binary value into hexadecimal
        checksum = Integer.toHexString(Integer.parseInt(tempChecksum.substring(0,4), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(4, 8), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(8, 12), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(12), 2));

        return checksum;
    }

    // Method to calculate the checksum for 32 bits
    public static String checksum32(String str, int byt) {
        // Declaring variables
        byte[] bytes = str.getBytes();
        String addedBinary = "";
        String A = "00001010";
        String X = "01011000";
        String checksum = "";
        String tempChecksum = "";
        String hexInbin[] = new String[bytes.length];

        // Converting the hexadecimal values into binary
        int len = str.length()/4;
        String[] array32 = new String[len];

        for(int i = 0; i < bytes.length; i++) {
			hexInbin[i] = hexToBin(Integer.toHexString(bytes[i]));
		}

        // Creating an array of 32 bits
        int i = 0, j = 0;
        while(i < hexInbin.length-3) {
            array32[j] = hexInbin[i] + hexInbin[i+1] + hexInbin[i+2] + hexInbin[i+3];
            j++;
            i+=4;
        }

        // Adding the binary values
        addedBinary = array32[0];
        for(i = 1; i < array32.length; i++) {
            addedBinary = addBin(addedBinary, array32[i], byt);
        }

        // Adding the binary values with 00001010 and 01011000
        if(str.length() % 4 == 0) {
            String str2 = A + X + X + X;
            System.out.println("XXX");
            tempChecksum = addBin(addedBinary, str2, byt);
        } else if(str.length() % 4 == 1) {
            String str2 = hexInbin[hexInbin.length - 1] + A + X + X;
            System.out.println("XX");
            tempChecksum = addBin(addedBinary, str2, byt);
        } else if(str.length() % 4 == 2) {
            String str2 = hexInbin[hexInbin.length - 2] + hexInbin[hexInbin.length - 1] + A + X;
            System.out.println("X");
            tempChecksum = addBin(addedBinary, str2, byt);
        } else if(str.length() % 4 == 3) {
            String str2 = hexInbin[hexInbin.length - 3] + hexInbin[hexInbin.length - 2] + hexInbin[hexInbin.length - 1] + A;
            System.out.println();
            tempChecksum = addBin(addedBinary, str2, byt);
        }

        // Converting the binary value into hexadecimal
        checksum = Integer.toHexString(Integer.parseInt(tempChecksum.substring(0,4), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(4, 8), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(8, 12), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(12, 16), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(16, 20), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(20, 24), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(24, 28), 2))+
                Integer.toHexString(Integer.parseInt(tempChecksum.substring(28), 2));

        return checksum;
    
    }

    // Method to calculate the string length after adding the checksum value
    public static Integer characterCount(String str, int byt) {
        // Declaring variables
        int CharCount = 0;

        // Calculating the string length after adding the checksum value
        switch(byt) {
            case 8:
                CharCount = str.length() + 1;
                break;
            case 16:
                if(str.length() % 2 == 0) {
                    CharCount = str.length() + 2;
                } else {
                    CharCount = str.length() + 1;
                }
                break;
            case 32:
                if(str.length() % 4 == 0) {
                    CharCount = str.length() + 4;
                } else if(str.length() % 4 == 1) {
                    CharCount = str.length() + 3;
                } else if(str.length() % 4 == 2) {
                    CharCount = str.length() + 2;
                } else if(str.length() % 4 == 3) {
                    CharCount = str.length() + 1;
                }
                break;
        }

        return CharCount;
    }

}

/*=============================================================================
| I Sunidhi Mistry (su358025) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/