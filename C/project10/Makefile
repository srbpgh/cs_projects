
all: paper-length.x

run: paper-length.x
	paper-length.x < document1 | diff - public1.output
	paper-length.x < document2 | diff - public2.output
	paper-length.x < document1
	paper-length.x < document2

paper-length.x: paper-length.o safe-fork.o
	gcc paper-length.o safe-fork.o -o paper-length.x

paper-length.o: paper-length.c safe-fork.h
	gcc -g -Wall -Werror -pedantic -c paper-length.c 

safe-fork.o: safe-fork.h

clean:
	rm -f paper-length.x
	rm -f paper-length.o
