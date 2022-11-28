#!/bin/bash
case $1 in
    pa02.c)
	rm -f -- a.out
	gcc pa02.c 
	if [ $? -ne 0 ]; then 
	    echo "Compile of pa02.c failed"
	    echo "Good bye!"
	    exit 1 
	fi
	EXE="./a.out"
	;;
    pa02.cpp)
	rm -f -- a.out
	g++ pa02.cpp
	if [ $? -ne 0 ]; then 
	    echo "Compile of pa02.cpp failed"
	    echo "Good bye!"
	    exit 1 
	fi
	EXE="./a.out"
	;;
    pa02.go)
	rm -f -- pa02
	go build pa02.go
	if [ $? -ne 0 ]; then 
	    echo "Compile of pa02.go failed"
	    echo "Good bye!"
	    exit 1 
	fi
	EXE="./pa02"
	;;
    pa02.java)
	rm -f -- pa02.class
	javac pa02.java
	if [ $? -ne 0 ]; then 
	    echo "Compile of pa02.java failed"
	    echo "Good bye!"
	    exit 1 
	fi
	EXE="java pa02"
	;;
    pa02.py)
	EXE="python pa02.py"
	;;
*)
    echo "Invalid source file name"
    echo "->  should be pa02.c, pa02.cpp, pa02.go, pa02.java or pa02.py"
    exit 1
esac

echo "Compile of pa02 succeded."

echo "-> Case #1 - in10A.txt - 8 bit checksum"
eval $EXE in10A.txt 8 >s10A-Output8.txt
diff -w s10A-Output8.txt s10A-Base8.txt


echo "-> Case #1 - in10A.txt - 16 bit checksum"
eval $EXE in10A.txt 16 >s10A-Output16.txt
diff -w s10A-Output16.txt s10A-Base16.txt


echo "-> Case #1 - in10A.txt - 32 bit checksum"
eval $EXE in10A.txt 32 >s10A-Output32.txt
diff -w s10A-Output32.txt s10A-Base32.txt


echo "-> Case #2 - in17A.txt - 8 bit checksum"
eval $EXE in17A.txt 8 >s17A-Output8.txt
diff -w s17A-Output8.txt s17A-Base8.txt


echo "-> Case #2 - in17A.txt - 16 bit checksum"
eval $EXE in17A.txt 16 >s17A-Output16.txt
diff -w s17A-Output16.txt s17A-Base16.txt


echo "-> Case #2 - in17A.txt - 32 bit checksum"
eval $EXE in17A.txt 32 >s17A-Output32.txt
diff -w s17A-Output32.txt s17A-Base32.txt


echo "-> Case #3 - in18A.txt - 8 bit checksum"
eval $EXE in18A.txt 8 >s18A-Output8.txt
diff -w s18A-Output8.txt s18A-Base8.txt


echo "-> Case #3 - in18A.txt - 16 bit checksum"
eval $EXE in18A.txt 16 >s18A-Output16.txt
diff -w s18A-Output16.txt s18A-Base16.txt


echo "-> Case #3 - in18A.txt - 32 bit checksum"
eval $EXE in18A.txt 32 >s18A-Output32.txt
diff -w s18A-Output32.txt s18A-Base32.txt


echo "-> Case #4 - inRF2.txt - 8 bit checksum"
eval $EXE inRF2.txt 8 >sRF2-Output8.txt
diff -w sRF2-Output8.txt sRF2-Base8.txt


echo "-> Case #4 - inRF2.txt - 16 bit checksum"
eval $EXE inRF2.txt 16 >sRF2-Output16.txt
diff -w sRF2-Output16.txt sRF2-Base16.txt


echo "-> Case #4 - inRF2.txt - 32 bit checksum"
eval $EXE inRF2.txt 32 >sRF2-Output32.txt
diff -w sRF2-Output32.txt sRF2-Base32.txt


echo "-> Case #5 - inWC2.txt - 8 bit checksum"
eval $EXE inWC2.txt 8 >sWC2-Output8.txt
diff -w sWC2-Output8.txt sWC2-Base8.txt


echo "-> Case #5 - inWC2.txt - 16 bit checksum"
eval $EXE inWC2.txt 16 >sWC2-Output16.txt
diff -w sWC2-Output16.txt sWC2-Base16.txt


echo "-> Case #5 - inWC2.txt - 32 bit checksum"
eval $EXE inWC2.txt 32 >sWC2-Output32.txt
diff -w sWC2-Output32.txt sWC2-Base32.txt

echo "Checksum testing completed"

