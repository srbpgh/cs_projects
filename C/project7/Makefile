CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror -Wshadow -g

all: public01.x public02.x public03.x public04.x public05.x public06.x \
public07.x public08.x public09.x public10.x

student: init_test.x append_test.x prepend_test.x size_test.x print_test.x \
find_test.x delete_test.x

run: all student
	public01.x | diff - public01.output
	public02.x | diff - public02.output
	public03.x | diff - public03.output
	public04.x | diff - public04.output
	public05.x | diff - public05.output
	public06.x | diff - public06.output
	public07.x | diff - public07.output
	public08.x | diff - public08.output
	public09.x | diff - public09.output
	public10.x | diff - public10.output

run_student: student
	init_test.x
	append_test.x | diff - append_test.output
	prepend_test.x | diff - prepend_test.output
	size_test.x
	print_test.x | diff - print_test.output
	find_test.x
	delete_test.x | diff - delete_test.output

list.o: list.c list.h
	gcc $(CFLAGS) -c list.c

public01.o: public01.c list.h
	gcc $(CFLAGS) -c public01.c

public02.o: public02.c list.h
	gcc $(CFLAGS) -c public02.c

public03.o: public03.c list.h
	gcc $(CFLAGS) -c public03.c

public04.o: public04.c list.h
	gcc $(CFLAGS) -c public04.c

public05.o: public05.c list.h
	gcc $(CFLAGS) -c public05.c

public06.o: public06.c list.h
	gcc $(CFLAGS) -c public06.c

public07.o: public07.c list.h
	gcc $(CFLAGS) -c public07.c

public08.o: public08.c list.h
	gcc $(CFLAGS) -c public08.c

public09.o: public09.c list.h
	gcc $(CFLAGS) -c public09.c

public10.o: public10.c list.h
	gcc $(CFLAGS) -c public10.c

public01.x: public01.o list.o
	gcc $(CFLAGS) list.o public01.o -o public01.x

public02.x: public02.o list.o
	gcc $(CFLAGS) list.o public02.o -o public02.x

public03.x: public03.o list.o
	gcc $(CFLAGS) list.o public03.o -o public03.x

public04.x: public04.o list.o
	gcc $(CFLAGS) list.o public04.o -o public04.x

public05.x: public05.o list.o
	gcc $(CFLAGS) list.o public05.o -o public05.x

public06.x: public06.o list.o
	gcc $(CFLAGS) list.o public06.o -o public06.x

public07.x: public07.o list.o
	gcc $(CFLAGS) list.o public07.o -o public07.x

public08.x: public08.o list.o
	gcc $(CFLAGS) list.o public08.o -o public08.x

public09.x: public09.o list.o
	gcc $(CFLAGS) list.o public09.o -o public09.x

public10.x: public10.o list.o
	gcc $(CFLAGS) list.o public10.o -o public10.x

init_test.o: init_test.c list.h
	gcc $(CFLAGS) -c init_test.c

append_test.o: append_test.c list.h
	gcc $(CFLAGS) -c append_test.c

prepend_test.o: prepend_test.c list.h
	gcc $(CFLAGS) -c prepend_test.c

size_test.o: size_test.c list.h
	gcc $(CFLAGS) -c size_test.c

print_test.o: print_test.c list.h
	gcc $(CFLAGS) -c print_test.c

find_test.o: find_test.c list.h
	gcc $(CFLAGS) -c find_test.c

delete_test.o: delete_test.c list.h
	gcc $(CFLAGS) -c delete_test.c

init_test.x: init_test.o list.o
	gcc $(CFLAGS) init_test.o list.o -o init_test.x

append_test.x: append_test.o list.o
	gcc $(CFLAGS) append_test.o list.o -o append_test.x

prepend_test.x: prepend_test.o list.o
	gcc $(CFLAGS) prepend_test.o list.o -o prepend_test.x

size_test.x: size_test.o list.o
	gcc $(CFLAGS) size_test.o list.o -o size_test.x

print_test.x: print_test.o list.o
	gcc $(CFLAGS) print_test.o list.o -o print_test.x

find_test.x: find_test.o list.o
	gcc $(CFLAGS) find_test.o list.o -o find_test.x

delete_test.x: delete_test.o list.o
	gcc $(CFLAGS) delete_test.o list.o -o delete_test.x


clean:
	rm -f *.o *.x
