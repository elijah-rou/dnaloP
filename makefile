JC = javac
J = java
SRC = $(wildcard ./src/*/*.java)
LIB = $(wildcard lib/*.jar)
DIR = $(LIB) $(SRC) 
BIN = bin

# USE
# make
default:
	@- make clean
	$(JC) -d $(BIN) -cp $(DIR)
	find $(DIR) -name "*.class" -exec mv -i {} bin \;

# USE:
# make parser arg=*filename*
parser:
	@make
	$(J) -cp $(BIN):$(LIB) ParserGUI $(arg)

clean:
	rm bin/*.class
