# Shaun Birch 115938328 srbpgh
CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror -Wshadow -g

all: tests

tests: public01.x public02.x public03.x public04.x public05.x public06.x public07.x public08.x public09.x public10.x

run: tests
	public01.x < public01.input | diff - public01.output
	public02.x | diff - public02.output
	public03.x | diff - public03.output
	public04.x | diff - public04.output
	public05.x < public05.input | diff - public05.output
	public06.x | diff - public06.output
	public07.x | diff - public07.output
	public08.x | diff - public08.output
	public09.x < public09.input | diff - public09.output
	public10.x < public10.input | diff - public10.output

student.x: student.o unix.o
	gcc unix.o student.o -o student.x

student.o: student.c unix.h
	gcc $(CFLAGS) -c student.c

unix.h: unix-datastructure.h

public01.x: public01.o unix.o driver.o
	gcc unix.o public01.o driver.o -o public01.x

public02.x: public02.o unix.o
	gcc unix.o public02.o -o public02.x

public03.x: public03.o unix.o memory-checking.o
	gcc unix.o public03.o memory-checking.o -o public03.x

public04.x: public04.o unix.o memory-checking.o
	gcc unix.o public04.o memory-checking.o -o public04.x

public05.x: public05.o unix.o driver.o memory-checking.o
	gcc unix.o public05.o driver.o memory-checking.o -o public05.x

public06.x: public06.o unix.o
	gcc unix.o public06.o -o public06.x

public07.x: public07.o unix.o
	gcc unix.o public07.o -o public07.x

public08.x: public08.o unix.o
	gcc unix.o public08.o -o public08.x

public09.x: public09.o unix.o driver.o
	gcc unix.o public09.o driver.o -o public09.x

public10.x: public10.o unix.o driver.o
	gcc unix.o public10.o driver.o -o public10.x

driver.o: driver.h

unix.o: unix.c unix.h
	gcc -c $(CFLAGS) unix.c

public01.o: public01.c unix.h driver.h
	gcc -c $(CFLAGS) public01.c

public02.o: public02.c unix.h
	gcc -c $(CFLAGS) public02.c

public03.o: public03.c unix.h memory-checking.h
	gcc -c $(CFLAGS) public03.c

public04.o: public04.c unix.h memory-checking.h
	gcc -c $(CFLAGS) public04.c

public05.o: public05.c unix.h memory-checking.h driver.h
	gcc -c $(CFLAGS) public05.c

public06.o: public06.c unix.h
	gcc -c $(CFLAGS) public06.c

public07.o: public07.c unix.h
	gcc -c $(CFLAGS) public07.c

public08.o: public08.c unix.h
	gcc -c $(CFLAGS) public08.c

public09.o: public09.c unix.h driver.h
	gcc -c $(CFLAGS) public09.c

public10.o: public10.c unix.h driver.h
	gcc -c $(CFLAGS) public10.c

clean:
	rm -f tests
	rm -f *.x
	rm -f public01.o
	rm -f public02.o
	rm -f public03.o
	rm -f public04.o
	rm -f public05.o
	rm -f public06.o
	rm -f public07.o
	rm -f public08.o
	rm -f public09.o
	rm -f public10.o
	rm -f unix.o
	rm -f student.o
	rm -f student.x
