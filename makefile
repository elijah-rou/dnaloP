JC = javac
J = java
#SRC = $(wildcard ./src/*/*.java)
PARSE_SRC = $(wildcard ./*.java)
LIB = $(wildcard lib/*.jar)
DIR = $(LIB) $(SRC) 
BIN = bin

default:
	@- make clean
	$(JC) -d $(BIN) -cp $(LIB) $(SRC)
	find $(DIR) -name "*.class" -exec mv -i {} bin \;

Parser:
	@make
	$(J) -cp $(BIN):$(LIB) ParserGUI

clean:
	rm bin/*.class
