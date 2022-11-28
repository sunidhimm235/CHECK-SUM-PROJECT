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


for i in $(ls i?.txt); do
   echo Processing: $i
   eval $EXE $i 8   # comment this line out by placing a # in front of eval...
   eval $EXE $i 16  # comment this line out by placing a # in front of eval...
   eval $EXE $i 32  # comment this line out by placing a # in front of eval...
done
