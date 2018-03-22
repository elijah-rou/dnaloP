JC = javac
J = java
SRC = $(wildcard ./src/*/*.java)
LIB = $(wildcard lib/*.jar)
DIR = $(LIB) $(SRC) 
BIN = bin

default:
	@- make clean
	$(JC) -d $(BIN) -cp $(LIB) $(SRC)
	find $(DIR) -name "*.class" -exec mv -i {} bin \;

parser:
	@make
	$(J) -cp $(BIN):$(LIB) ParserGUI $(arg)

clean:
	rm bin/*.class
