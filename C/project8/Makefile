CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror -Wshadow -g

all: tests

tests: public01.x public02.x public03.x public04.x public05.x public06.x public07.x public08.x public09.x public10.x secret01.x secret02.x secret03.x secret04.x secret05.x secret06.x secret07.x secret08.x secret09.x secret10.x secret11.x secret12.x secret13.x secret14.x secret15.x

run: tests
	public01.x | diff - public01.output
	public02.x | diff - public02.output
	public03.x | diff - public03.output
	public04.x | diff - public04.output
	public05.x | diff - public05.output
	public06.x | diff - public06.output
	public07.x | diff - public07.output
	public08.x | diff - public08.output
	public09.x | diff - public09.output
	public10.x < public10.input | diff - public10.output
	secret01.x | diff - secret01.output
	secret02.x | diff - secret02.output
	secret03.x | diff - secret03.output
	secret04.x | diff - secret04.output
	secret05.x | diff - secret05.output
	secret06.x | diff - secret06.output
	secret07.x | diff - secret07.output
	secret08.x | diff - secret08.output
	secret09.x | diff - secret09.output
	secret10.x < secret10.input | diff - secret10.output
	secret11.x | diff - secret11.output
	secret12.x | diff - secret12.output
	secret13.x | diff - secret13.output
	secret14.x | diff - secret14.output
	secret15.x | diff - secret15.output

unix.h: unix-datastructure.h

public01.x: public01.o unix.o
	gcc unix.o public01.o -o public01.x

public02.x: public02.o unix.o
	gcc unix.o public02.o -o public02.x

public03.x: public03.o unix.o
	gcc unix.o public03.o -o public03.x

public04.x: public04.o unix.o
	gcc unix.o public04.o -o public04.x

public05.x: public05.o unix.o
	gcc unix.o public05.o -o public05.x

public06.x: public06.o unix.o
	gcc unix.o public06.o -o public06.x

public07.x: public07.o unix.o
	gcc unix.o public07.o -o public07.x

public08.x: public08.o unix.o
	gcc unix.o public08.o -o public08.x

public09.x: public09.o unix.o
	gcc unix.o public09.o -o public09.x

public10.x: public10.o unix.o driver.o
	gcc unix.o public10.o driver.o -o public10.x

driver.o: driver.h

unix.o: unix.c unix.h
	gcc -c $(CFLAGS) unix.c

public01.o: public01.c unix.h
	gcc -c $(CFLAGS) public01.c

public02.o: public02.c unix.h
	gcc -c $(CFLAGS) public02.c

public03.o: public03.c unix.h
	gcc -c $(CFLAGS) public03.c

public04.o: public04.c unix.h
	gcc -c $(CFLAGS) public04.c

public05.o: public05.c unix.h
	gcc -c $(CFLAGS) public05.c

public06.o: public06.c unix.h
	gcc -c $(CFLAGS) public06.c

public07.o: public07.c unix.h
	gcc -c $(CFLAGS) public07.c

public08.o: public08.c unix.h
	gcc -c $(CFLAGS) public08.c

public09.o: public09.c unix.h
	gcc -c $(CFLAGS) public09.c

public10.o: public10.c unix.h driver.h
	gcc -c $(CFLAGS) public10.c

secret01.o: secret01.c unix.h
	gcc -c $(CFLAGS) secret01.c

secret02.o: secret02.c unix.h
	gcc -c $(CFLAGS) secret02.c

secret03.o: secret03.c unix.h
	gcc -c $(CFLAGS) secret03.c

secret04.o: secret04.c unix.h
	gcc -c $(CFLAGS) secret04.c

secret05.o: secret05.c unix.h
	gcc -c $(CFLAGS) secret05.c

secret06.o: secret06.c unix.h
	gcc -c $(CFLAGS) secret06.c

secret07.o: secret07.c unix.h
	gcc -c $(CFLAGS) secret07.c

secret08.o: secret08.c unix.h
	gcc -c $(CFLAGS) secret08.c

secret09.o: secret09.c unix.h
	gcc -c $(CFLAGS) secret09.c

secret10.o: secret10.c unix.h driver.h
	gcc -c $(CFLAGS) secret10.c

secret11.o: secret11.c unix.h
	gcc -c $(CFLAGS) secret11.c

secret12.o: secret12.c unix.h
	gcc -c $(CFLAGS) secret12.c

secret13.o: secret13.c unix.h
	gcc -c $(CFLAGS) secret13.c

secret14.o: secret14.c unix.h
	gcc -c $(CFLAGS) secret14.c

secret15.o: secret15.c unix.h
	gcc -c $(CFLAGS) secret15.c

secret01.x: secret01.o unix.o
	gcc unix.o secret01.o -o secret01.x

secret02.x: secret02.o unix.o
	gcc unix.o secret02.o -o secret02.x

secret03.x: secret03.o unix.o
	gcc unix.o secret03.o -o secret03.x

secret04.x: secret04.o unix.o
	gcc unix.o secret04.o -o secret04.x

secret05.x: secret05.o unix.o
	gcc unix.o secret05.o -o secret05.x

secret06.x: secret06.o unix.o
	gcc unix.o secret06.o -o secret06.x

secret07.x: secret07.o unix.o
	gcc unix.o secret07.o -o secret07.x

secret08.x: secret08.o unix.o
	gcc unix.o secret08.o -o secret08.x

secret09.x: secret09.o unix.o
	gcc unix.o secret09.o -o secret09.x

secret10.x: secret10.o unix.o driver.o
	gcc unix.o secret10.o driver.o -o secret10.x

secret11.x: secret11.o unix.o
	gcc unix.o secret11.o -o secret11.x

secret12.x: secret12.o unix.o
	gcc unix.o secret12.o -o secret12.x

secret13.x: secret13.o unix.o
	gcc unix.o secret13.o -o secret13.x

secret14.x: secret14.o unix.o
	gcc unix.o secret14.o -o secret14.x

secret15.x: secret15.o unix.o
	gcc unix.o secret15.o -o secret15.x

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
