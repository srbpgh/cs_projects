CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror -Wshadow
run: xargs.x
	run-tests2

xargs.x: xargs.c
	gcc $(CFLAGS) xargs.c safe-fork.o split.o -g -o xargs.x 
clean:
	rm -f xargs.x
